package model;

import java.util.List;

public class Student {
    private String studentId;

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setScores(List<Score> scores) {
        this.scores = scores;
    }

    private String name;
    private String email;
    private String gender;
    private Address address;
    private List<Score> scores;

    public Student(String studentId, String name, String email, String gender, Address address, List<Score> scores) {
        this.studentId = studentId;
        this.name = name;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
    }

    public Address getAddress() {
        return address;
    }

    public List<Score> getScores() {
        return scores;
    }

    public double calculateAverageScore() {
        return scores.stream().mapToDouble(Score::getScore).average().orElse(0.0);
    }

    public String getRank() {
        double avg = calculateAverageScore();
        if (avg >= 9.0) return "Xuất sắc";
        if (avg >= 8.0) return "Giỏi";
        if (avg >= 6.5) return "Khá";
        if (avg >= 5.0) return "Trung bình";
        return "Yếu";
    }

    @Override
    public String toString() {
        StringBuilder scoresStr = new StringBuilder();
        for (Score score : scores) {
            scoresStr.append(score.toString()).append(", ");
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

        for (Score score : scores) {
            csvBuilder.append(score.getSubject()).append(", ").append(score.getScore()).append(", ");
        }

        csvBuilder.append(calculateAverageScore()).append(", ");
        csvBuilder.append(getRank());

        return csvBuilder.toString();
    }
}
