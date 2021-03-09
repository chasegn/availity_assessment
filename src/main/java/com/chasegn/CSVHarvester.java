package com.chasegn;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Stream;

public class CSVHarvester {
    Map<String, Map<String, EnrollmentRecord>> insuranceCompanyRecords;

    public CSVHarvester() {
        insuranceCompanyRecords = new HashMap<>();
    }

    public void readCSV(Stream<String> filestream) {
        filestream.forEach(s -> {
            String[] split = s.split("\\s*,\\s*");
            EnrollmentRecord record = new EnrollmentRecord(split[0], split[1], split[2], Integer.parseInt(split[3]), split[4]);

            insuranceCompanyRecords
                    .computeIfAbsent(record.getInsuranceCompany(), company -> new HashMap<>())                      // if company hasn't been encountered, add new map to hold those records
                    .merge(record.getUserId(), record, (v1, v2) -> v1.getVersion() > v2.getVersion() ? v1 : v2);    // determine which record to keep based on higher version number
        });
    }

    public void writeFiles() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd-HHmmdd");
        StringBuilder sb = new StringBuilder();
        String path = "src/main/resources/output/";
        String filename;

        for (Map.Entry<String, Map<String, EnrollmentRecord>> submap : insuranceCompanyRecords.entrySet()) {
            // Iterate across collections of records.
            // For each insurance company grouping...
            for (Map.Entry<String, EnrollmentRecord> entry : submap.getValue().entrySet()) {
                // Write out the value of collected records, one per line
                sb.append(entry.getValue().toString());
                sb.append("\n");
            }

            filename = submap.getKey() + "-" + sdf.format(System.currentTimeMillis()) + ".txt";

            try {
                Files.write(
                        Paths.get(path + filename),
                        sb.toString().getBytes(StandardCharsets.UTF_8));
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            sb.setLength(0);
        }
    }

    public static void main(String[] args) {
        CSVHarvester harvester = new CSVHarvester();

        try (Stream<String> stream = Files.lines(Paths.get("src/main/resources/records.csv"))) {
            harvester.readCSV(stream);
        } catch (Exception e) {
            e.printStackTrace();
        }

        harvester.writeFiles();

    }
}
