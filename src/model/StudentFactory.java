package model;

import java.util.ArrayList;
import java.util.List;

public class StudentFactory {
    public static Student createStudent(String studentId, String name, String email, String gender, Address address, List<Score> scores) {
        return new Student(studentId, name, email, gender, address, scores);
    }

    public static List<Score> createDefaultScores() {
        String[] subjects = {"Toán", "Văn", "Anh", "Hóa", "Sinh", "Sử", "Địa"};
        List<Score> scores = new ArrayList<>();
        for (String subject : subjects) {
            scores.add(new Score(subject, 0.0));
        }
        return scores;
    }
}
