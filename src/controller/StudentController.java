package controller;

import model.Student;
import model.Grade;
import model.StudentManager;
import java.util.*;
import java.util.regex.*;

public class StudentController {
    private StudentManager studentManager;

    public StudentController() {
        studentManager = StudentManager.getInstance();
    }

    public void addStudent(String id, String code, String name, String email) {
        if (!isValidEmail(email)) {
            throw new IllegalArgumentException("Email không hợp lệ.");
        }
        Student student = new Student(id, code, name, email);
        studentManager.addStudent(student);
        System.out.println("Sinh viên đã được thêm thành công!");
    }

    public void updateStudentInfo(String id, String newCode, String newName, String newEmail) {
        Student student = studentManager.getStudentById(id);
        if (student != null) {
            student.setCode(newCode);
            student.setName(newName);
            student.setEmail(newEmail);
            System.out.println("Thông tin sinh viên đã được cập nhật.");
        } else {
            System.out.println("Không tìm thấy sinh viên với ID: " + id);
        }
    }

    public void updateScore(String id, String subject, double score) {
        Student student = studentManager.getStudentById(id);
        if (student != null) {
            Grade grade = new Grade(subject, score);
            student.addGrade(grade);
            System.out.println("Điểm môn " + subject + " đã được cập nhật.");
        } else {
            System.out.println("Không tìm thấy sinh viên với ID: " + id);
        }
    }

    public void displayStudent(String id) {
        Student student = studentManager.getStudentById(id);
        if (student != null) {
            student.displayStudentInfo();
        } else {
            System.out.println("Không tìm thấy sinh viên với ID: " + id);
        }
    }

    private boolean isValidEmail(String email) {
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
