package model;

import java.util.ArrayList;
import java.util.List;

public class StudentFactory {

    /**
     * Tạo một sinh viên mới với các thông tin đầu vào.
     *
     * @param studentId ID sinh viên
     * @param name Tên sinh viên
     * @param email Email sinh viên
     * @param gender Giới tính (MALE, FEMALE, OTHER)
     * @param address Địa chỉ sinh viên
     * @param scores Danh sách điểm các môn học
     * @return Đối tượng Student
     */
    public static Student createStudent(String studentId, String name, String email, Gender gender, Address address, List<Score> scores) {
        return new Student(studentId, name, email, gender, address, scores);
    }

    /**
     * Tạo danh sách các điểm mặc định cho tất cả các môn học.
     * Điểm mặc định là 0.
     *
     * @return Danh sách điểm
     */
    public static List<Score> createDefaultScores() {
        List<Score> scores = new ArrayList<>();
        for (Subject subject : Subject.values()) {
            scores.add(new Score(subject, 0.0)); // Điểm mặc định là 0.0
        }
        return scores;
    }
}
