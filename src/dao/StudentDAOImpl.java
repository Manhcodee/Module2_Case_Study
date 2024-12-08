package dao;

import model.Student;
import storage.StudentStorage;

import java.util.List;

public class StudentDAOImpl implements StudentDAO {
    private final StudentStorage storage = StudentStorage.getInstance();

    public StudentDAOImpl() {
        storage.loadStudents(); // Tải dữ liệu từ file CSV khi khởi tạo DAO
    }

    @Override
    public List<Student> getAllStudents() {
        return storage.getStudents();
    }

    @Override
    public void addStudent(Student student) {
        storage.getStudents().add(student);
    }

    @Override
    public void updateStudent(String studentId, Student updatedStudent) {
        List<Student> students = storage.getStudents();
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getStudentId().equals(studentId)) {
                students.set(i, updatedStudent);
                return;
            }
        }
    }

    @Override
    public void deleteStudent(String studentId) {
        List<Student> students = storage.getStudents();
        students.removeIf(student -> student.getStudentId().equals(studentId));
    }

    @Override
    public Student getStudentById(String studentId) {
        for (Student student : storage.getStudents()) {
            if (student.getStudentId().equals(studentId)) {
                return student;
            }
        }
        return null;
    }

    @Override
    public void saveStudents() {
        storage.saveStudents();
    }
}
