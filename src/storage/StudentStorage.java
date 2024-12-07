package storage;

import model.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StudentStorage {
    private static StudentStorage instance;
    private static final String FILE_PATH = "data/students.csv";
    private List<Student> students;

    private StudentStorage() {
        this.students = new ArrayList<>();
    }

    public static StudentStorage getInstance() {
        if (instance == null) {
            instance = new StudentStorage();
        }
        return instance;
    }

    public List<Student> getStudents() {
        return students;
    }

    // Ghi dữ liệu vào file CSV
    public void saveStudents() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Student student : students) {
                writer.write(student.toCSV());
                writer.newLine(); // Xuống dòng sau mỗi sinh viên
            }
            System.out.println("Lưu danh sách sinh viên thành công!");
        } catch (IOException e) {
            System.out.println("Đã xảy ra lỗi khi lưu danh sách sinh viên.");
            e.printStackTrace();
        }
    }

    // Đọc dữ liệu từ file CSV
    public void loadStudents() {
        students.clear(); // Xóa danh sách hiện tại để tải lại từ file
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",\\s*");
                if (parts.length < 6) continue;

                // Đọc thông tin cơ bản
                String studentId = parts[0];
                String name = parts[1];
                String email = parts[2];
                Gender gender = Gender.valueOf(parts[3]);
                Address address = new Address(parts[4], parts[5], parts[6]);

                // Đọc điểm các môn học
                List<Score> scores = new ArrayList<>();
                int scoreStartIndex = 7;
                for (int i = scoreStartIndex; i < parts.length - 2; i += 2) {
                    try {
                        Subject subject = Subject.fromString(parts[i].trim());
                        double score = Double.parseDouble(parts[i + 1].trim());
                        scores.add(new Score(subject, score));
                    } catch (IllegalArgumentException e) {
                        System.out.println("Bỏ qua môn học không hợp lệ: " + parts[i]);
                    }
                }

                // Đọc điểm trung bình và xếp loại (nếu cần)
                double averageScore = Double.parseDouble(parts[parts.length - 2]);
                String rank = parts[parts.length - 1];

                // Tạo đối tượng Student
                Student student = new Student(studentId, name, email, gender, address, scores);
                students.add(student);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Không tìm thấy file dữ liệu, bắt đầu với danh sách trống.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
