package model;

public class Score {
    private Subject subject;
    private double score;

    public Score(Subject subject, double score) {
        this.subject = subject;
        this.score = score;
    }

    public Subject getSubject() {
        return subject;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return subject + ": " + score;
    }
}
