package model;

import dao.StudentDAO;
import java.util.*;

public class StudentManager {
    private static StudentManager instance;
    private StudentDAO studentDAO;
    private List<Student> students;

    private StudentManager() {
        studentDAO = new StudentDAO();
        students = studentDAO.getAllStudents();
    }

    public static StudentManager getInstance() {
        if (instance == null) {
            instance = new StudentManager();
        }
        return instance;
    }

    public void addStudent(Student student) {
        students.add(student);
        studentDAO.saveStudent(student);
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

    public void deleteStudent(String id) {
        Student student = getStudentById(id);
        if (student != null) {
            students.remove(student);
            studentDAO.deleteStudent(id);
        }
    }
}
