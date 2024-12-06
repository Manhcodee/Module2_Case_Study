package model;

import java.util.*;

public class Student {
    private String id;
    private String code;
    private String name;
    private String email;
    private List<Grade> grades;

    public Student(String id, String code, String name, String email) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.email = email;
        this.grades = new ArrayList<>();
    }

    public void addGrade(Grade grade) {
        grades.add(grade);
    }

    public double calculateGPA() {
        double totalScore = 0;
        int subjectCount = grades.size();

        for (Grade grade : grades) {
            totalScore += grade.getScore();
        }

        return subjectCount > 0 ? totalScore / subjectCount : 0;
    }

    public void displayStudentInfo() {
        System.out.println("ID: " + id + ", Mã sinh viên: " + code + ", Tên: " + name + ", Email: " + email);
        for (Grade grade : grades) {
            System.out.println(grade);
        }
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

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
