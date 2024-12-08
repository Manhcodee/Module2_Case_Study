package dao;

import model.Student;

import java.util.List;

public interface StudentDAO {
    List<Student> getAllStudents(); // Lấy danh sách tất cả sinh viên
    void addStudent(Student student); // Thêm sinh viên
    void updateStudent(String studentId, Student updatedStudent); // Cập nhật sinh viên
    void deleteStudent(String studentId); // Xóa sinh viên
    Student getStudentById(String studentId); // Lấy sinh viên theo ID
    void saveStudents(); // Lưu dữ liệu sinh viên ra file
}
