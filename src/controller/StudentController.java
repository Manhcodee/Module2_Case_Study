package controller;

import model.Student;
import model.StudentManager;
import storage.StudentDAO;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class StudentController {

    private StudentManager studentManager;

    public StudentController() {
        studentManager = StudentManager.getInstance();
    }

    // Thêm sinh viên vào hệ thống
    public void addStudent(String id, String code, String name, String email, double gpa) {
        Student student = new Student(id, code, name, email, gpa);
        studentManager.addStudent(student);
        System.out.println("Sinh viên đã được thêm thành công!");
    }

    // Sửa sinh viên theo ID
    public void updateStudent(String id, String name, String email, double gpa) {
        boolean isUpdated = studentManager.updateStudent(id, name, email, gpa);
        if (isUpdated) {
            System.out.println("Thông tin sinh viên đã được sửa thành công!");
        } else {
            System.out.println("Không tìm thấy sinh viên với ID: " + id);
        }
    }

    // Xóa sinh viên theo ID
    public void deleteStudent(String id) {
        Student student = studentManager.getStudentById(id);
        if (student != null) {
            studentManager.deleteStudent(id);
            System.out.println("Sinh viên đã được xóa thành công!");
        } else {
            System.out.println("Không tìm thấy sinh viên với ID: " + id);
        }
    }

    // Lưu danh sách sinh viên vào file CSV
    public void saveStudentsToCSV() throws IOException {
        StudentDAO.saveStudentsToCSV(studentManager.getAllStudents());
        System.out.println("Dữ liệu đã được lưu vào file CSV.");
    }

    // Đọc sinh viên từ file CSV
    public void loadStudentsFromCSV() throws IOException {
        List<Student> students = StudentDAO.readStudentsFromCSV();
        for (Student student : students) {
            studentManager.addStudent(student);
        }
    }

    // Hiển thị tất cả sinh viên
    public void displayStudents() {
        List<Student> students = studentManager.getAllStudents();
        if (students.isEmpty()) {
            System.out.println("Danh sách sinh viên trống.");
        } else {
            for (Student student : students) {
                System.out.println(student.getId() + " | " + student.getName() + " | " + student.getEmail() + " | " + student.getGpa());
            }
        }
    }
}
