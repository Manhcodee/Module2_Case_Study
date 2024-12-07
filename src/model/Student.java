package model;

import java.util.List;

public class Student {
    private String studentId;
    private String name;
    private String email;
    private Gender gender;
    private Address address;
    private List<Score> scores;
    private RankStrategy rankStrategy;

    public Student(String studentId, String name, String email, Gender gender, Address address, List<Score> scores) {
        this.studentId = studentId;
        this.name = name;
        this.email = email;
        this.gender = gender;
        this.address = address;
        this.scores = scores;
        this.rankStrategy = new DefaultRankStrategy();
    }

    public String getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Gender getGender() {
        return gender;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
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

    public String getRank() {
        return rankStrategy.getRank(calculateAverageScore());
    }

    @Override
    public String toString() {
        StringBuilder scoresStr = new StringBuilder();
        for (Score score : scores) {
            scoresStr.append(score.toString()).append("; ");
        }
        return String.format("%s, %s, %s, %s, %s, %s, %.2f, %s",
                studentId, name, email, gender, address, scoresStr.toString(), calculateAverageScore(), getRank());
    }

    public String toCSV() {
        StringBuilder csvBuilder = new StringBuilder();
        csvBuilder.append(studentId).append(", ");
        csvBuilder.append(name).append(", ");
        csvBuilder.append(email).append(", ");
        csvBuilder.append(gender).append(", ");
        csvBuilder.append(address.toString()).append(", ");

        // Thêm điểm từng môn học
        for (Score score : scores) {
            csvBuilder.append(score.getSubject()).append(", ").append(score.getScore()).append(", ");
        }

        // Thêm điểm trung bình và xếp loại
        csvBuilder.append(calculateAverageScore()).append(", ");
        csvBuilder.append(getRank());

        return csvBuilder.toString();
    }
}
