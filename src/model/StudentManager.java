package model;

import java.util.ArrayList;
import java.util.List;

public class StudentManager {
    private List<Student> students = new ArrayList<>();

    // them student
    public void addStudent(Student student) {
        students.add(student);
    }

    // xoa student
    public void removeStudent(String studentId) {
        students.removeIf(student -> student.getId().equals(studentId));
    }

    // lay thong tin sinh vien
    public Student getStudent(String studentId) {
        return students.stream().filter(student -> student.getId().equals(studentId)).findFirst().orElse(null);
    }

    // liet ke tat ca cac sinh vien
    public List<Student> getAllStudent() {
        return students;
    }
}
