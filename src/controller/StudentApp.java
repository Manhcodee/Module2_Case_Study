package controller;

import java.io.IOException;

public class StudentApp {
    public static void main(String[] args) {
        try {
            StudentController controller = new StudentController();

            // Thêm một số sinh viên vào hệ thống với email và điểm trung bình
            controller.addStudent("1", "US", "United States", "us@example.com", 8.5);
            controller.addStudent("2", "VN", "Viet Nam", "vn@example.com", 7.0);
            controller.addStudent("3", "AU", "Australia", "au@example.com", 9.0);

            // Lưu sinh viên vào file CSV
            String csvFile = "data/students.csv";
            controller.saveStudentsToCSV(csvFile);
            System.out.println("Dữ liệu đã được ghi vào file CSV.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
