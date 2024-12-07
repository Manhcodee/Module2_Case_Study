package model;

import java.util.List;

public class Student {
    private String studentId;
    private String name;
    private Gender gender;
    private Address address;
    private List<Score> scores;

    public Student(String studentId, String name, Gender gender, Address address, List<Score> scores) {
        this.studentId = studentId;
        this.name = name;
        this.gender = gender;
        this.address = address;
        this.scores = scores;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public Gender getGender() {
        return gender;
    }

    public Address getAddress() {
        return address;
    }

    public List<Score> getScores() {
        return scores;
    }

    public double calculateAverageScore() {
        double total = 0;
        for (Score score : scores) {
            total += score.getScore();
        }
        return scores.isEmpty() ? 0 : total / scores.size();
    }

    @Override
    public String toString() {
        return studentId + "," + name + "," + gender + "," + address + "," + calculateAverageScore();
    }
}
