package com.chasegn;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
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
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Map<String, EnrollmentRecord>> submap : insuranceCompanyRecords.entrySet()) {

            for (Map.Entry<String, EnrollmentRecord> entry : submap.getValue().entrySet()) {
                sb.append(entry.getValue().toString());
                sb.append("\n");
            }

            try {
                Files.write(
                        Paths.get("src/main/resources/output/" + submap.getKey() + ".txt"),
                        sb.toString().getBytes(StandardCharsets.UTF_8));
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            sb.setLength(0);
        }
    }


    // Comparator<EnrollmentRecord> nameComparator = Comparator.comparing(EnrollmentRecord::getLname);
    // Collections.sort(<list of user records per company>, nameComparator);

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
