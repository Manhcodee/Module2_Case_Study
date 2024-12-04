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
}
