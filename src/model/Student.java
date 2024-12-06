package model;

public class Student {
    private String id;
    private String code;
    private String name;
    private String email;
    private double gpa;

    public Student(String id, String code, String name, String email, double gpa) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.email = email;
        this.gpa = gpa;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    public String getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public double getGpa() {
        return gpa;
    }

    public String toCSV() {
        return id + "," + code + "," + name + "," + email + "," + gpa;
    }

}
