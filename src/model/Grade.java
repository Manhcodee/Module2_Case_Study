package model;

public class Grade {
    private String subject;
    private double score;

    public Grade(String subject, double score) {
        this.subject = subject;
        this.score = score;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public boolean isValid() {
        return score >= 0 && score <= 10;
    }

    public String getGradeLevel() {
        if (score >= 8) {
            return "A";
        } else if (score >= 6.5) {
            return "B";
        } else if (score >= 5) {
            return "C";
        } else {
            return "D";
        }
    }

    @Override
    public String toString() {
        return "Môn học: " + subject + ", Điểm: " + score + ", Xếp loại: " + getGradeLevel();
    }
}
