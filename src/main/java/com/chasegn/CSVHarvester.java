package com.chasegn;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CSVHarvester {
    Map<String, List<EnrollmentRecord>> insuranceCompanyRecords;

    public CSVHarvester() {
        insuranceCompanyRecords = new HashMap<>();
    }

    public void readCSV() {

    }

    public void writeFiles() {

    }


    // Comparator<EnrollmentRecord> nameComparator = Comparator.comparing(EnrollmentRecord::getLname);
    // Collections.sort(<list of user records per company>, nameComparator);

    public static void main(String[] args) {
        CSVHarvester harvester = new CSVHarvester();


    }
}
