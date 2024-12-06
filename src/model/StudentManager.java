package model;

import java.util.ArrayList;
import java.util.List;

public class StudentManager {
    private static StudentManager instance;
    private List<Student> students;

    private StudentManager() {
        students = new ArrayList<>();
    }

    // Lấy instance của StudentManager (Singleton)
    public static StudentManager getInstance() {
        if (instance == null) {
            instance = new StudentManager();
        }
        return instance;
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public boolean updateStudent(String id, String name, String email, double gpa) {
        for (Student student : students) {
            if (student.getId().equals(id)) {
                student.setName(name);
                student.setEmail(email);
                student.setGpa(gpa);
                return true;
            }
        }
        return false;
    }

    public void deleteStudent(String id) {
        students.removeIf(student -> student.getId().equals(id));
    }

    public List<Student> getAllStudents() {
        return students;
    }

    public Student getStudentById(String id) {
        for (Student student : students) {
            if (student.getId().equals(id)) {
                return student;
            }
        }
        return null;
    }
}
