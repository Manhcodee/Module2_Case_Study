package controller;

import model.*;
import storage.StudentDAO;

import java.io.IOException;
import java.util.List;

public class StudentController {

    private StudentManager studentManager;

    public StudentController() {
        studentManager = StudentManager.getInstance();  // Singleton
    }

    // Thêm sinh viên vào hệ thống
    public void addStudent(String id, String code, String name, String email, double gpa) {
        if (StudentValidator.validateEmail(email) && StudentValidator.validateGPA(gpa)) {
            Student student = StudentFactory.createStudent(id, code, name, email, gpa); // Factory
            studentManager.addStudent(student);
        } else {
            System.out.println("Thông tin sinh viên không hợp lệ.");
        }
    }

    // Sửa sinh viên theo ID
    public void updateStudent(String id, String name, String email, double gpa) {
        Student student = studentManager.getStudentById(id);
        if (student != null) {
            if (StudentValidator.validateEmail(email) && StudentValidator.validateGPA(gpa)) {
                studentManager.updateStudent(id, name, email, gpa);
                System.out.println("Thông tin sinh viên đã được cập nhật.");
            } else {
                System.out.println("Dữ liệu không hợp lệ.");
            }
        } else {
            System.out.println("Sinh viên không tồn tại.");
        }
    }

    // Xóa sinh viên theo ID
    public void deleteStudent(String id) {
        Student student = studentManager.getStudentById(id);
        if (student != null) {
            studentManager.deleteStudent(id);
            System.out.println("Sinh viên đã được xóa.");
        } else {
            System.out.println("Sinh viên không tồn tại.");
        }
    }

    // Lưu danh sách sinh viên vào file CSV
    public void saveStudentsToCSV(String filePath) throws IOException {
        StudentDAO.saveStudentsToCSV(studentManager.getAllStudents(), filePath); // DAO
    }

    // Hiển thị tất cả sinh viên
    public void displayStudents() {
        List<Student> students = studentManager.getAllStudents();
        if (students.isEmpty()) {
            System.out.println("Không có sinh viên nào.");
        } else {
            for (Student student : students) {
                System.out.println(student.getId() + " | " + student.getName() + " | " + student.getEmail() + " | " + student.getGpa());
            }
        }
    }
}
