package storage;

import model.Address;
import model.Score;
import model.Student;

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

    // Lưu danh sách sinh viên ra file CSV
    public void saveStudents() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Student student : students) {
                writer.write(student.toCSV());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Đọc dữ liệu từ file CSV và tải danh sách
    public void loadStudents() {
        students.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",\\s*");
                if (parts.length < 6) continue;

                String studentId = parts[0];
                String name = parts[1];
                String email = parts[2];
                String gender = parts[3];
                Address address = new Address(parts[4], parts[5], parts[6]);

                List<Score> scores = new ArrayList<>();
                for (int i = 7; i < parts.length - 2; i += 2) {
                    String subject = parts[i];
                    double score = Double.parseDouble(parts[i + 1]);
                    scores.add(new Score(subject, score));
                }

                Student student = new Student(studentId, name, email, gender, address, scores);
                students.add(student);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Không tìm thấy file dữ liệu. Bắt đầu với danh sách trống.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
