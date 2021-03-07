package com.chasegn;

public class EnrollmentRecord implements Comparable<EnrollmentRecord>{
    private String userId;
    private String fname;
    private String lname;
    private int version;
    private String insuranceCompany;

    public EnrollmentRecord(String userId, String fname, String lname, int version, String insuranceCompany) {
        this.userId = userId;
        this.fname = fname;
        this.lname = lname;
        this.version = version;
        this.insuranceCompany = insuranceCompany;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getInsuranceCompany() {
        return insuranceCompany;
    }

    public void setInsuranceCompany(String insuranceCompany) {
        this.insuranceCompany = insuranceCompany;
    }

    @Override
    public String toString() {
        return "EnrollmentRecord{" +
                "userId='" + userId + '\'' +
                ", fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", version=" + version +
                ", insuranceCompany='" + insuranceCompany + '\'' +
                '}';
    }

    @Override
    public int compareTo(EnrollmentRecord that) {
        return Integer.compare(this.version, that.version);
    }
}
