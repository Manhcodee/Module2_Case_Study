package model;

import java.io.Serializable;

public class Student {
    private String id;
    private String code;
    private String name;
    private String email;
    private double gpa;  // Điểm trung bình

    public Student(String id, String code, String name, String email, double gpa) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.email = email;
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

    // Cập nhật phương thức toCSV để bao gồm email và điểm trung bình
    public String toCSV() {
        return id + "," + code + "," + name + "," + email + "," + gpa;
    }
}
