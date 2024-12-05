package controller;

import model.*;
import storage.StudentDAO;

import java.io.IOException;

public class StudentController {

    private StudentManager studentManager;

    public StudentController() {
        studentManager = StudentManager.getInstance();  // Singleton
    }

    // Thêm sinh viên vào hệ thống
    public void addStudent(String id, String code, String name, String email, double gpa) {
        // Kiểm tra email và điểm trung bình hợp lệ
        if (StudentValidator.validateEmail(email) && StudentValidator.validateGPA(gpa)) {
            Student student = StudentFactory.createStudent(id, code, name, email, gpa); // Factory
            studentManager.addStudent(student);
        } else {
            System.out.println("Thông tin sinh viên không hợp lệ.");
        }
    }

    // Lưu danh sách sinh viên vào file CSV
    public void saveStudentsToCSV(String filePath) throws IOException {
        StudentDAO.saveStudentsToCSV(studentManager.getAllStudents(), filePath); // DAO
    }
}
