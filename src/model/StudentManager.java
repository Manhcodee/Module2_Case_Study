package model;

import java.util.ArrayList;
import java.util.List;

public class StudentManager {
    private List<Student> students = new ArrayList<>();
    private static StudentManager instance;

    private StudentManager() {}

    public static StudentManager getInstance() {
        if (instance == null) {
            instance = new StudentManager();
        }
        return instance;
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public Student getStudentById(String id) {
        for (Student student : students) {
            if (student.getId().equals(id)) {
                return student;
            }
        }
        return null;
    }

    // Sửa thông tin sinh viên theo ID
    public boolean updateStudent(String id, String name, String email, double gpa) {
        Student student = getStudentById(id);
        if (student != null) {
            student = new Student(id, student.getCode(), name, email, gpa);
            return true;
        }
        return false;
    }

    public void deleteStudent(String id) {
        students.removeIf(student -> student.getId().equals(id));
    }

    public List<Student> getAllStudents() {
        return students;
    }
}
