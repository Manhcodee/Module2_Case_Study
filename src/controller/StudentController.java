package controller;

import model.CustomException;
import model.Student;
import model.StudentManager;
import model.StudentValidator;
import storage.FileManager;
import storage.StudentDAO;

import java.util.List;

public class StudentController {
    private StudentManager manager ;

    public StudentController() {
        manager = new StudentManager();
    }

    // them sinh vien moi
    public void addStudent(Student student) throws CustomException {
        if (!StudentValidator.validateEmail(student.getEmail())) {
            throw new CustomException("Email không hợp lệ!");
        }
        if (!StudentValidator.validateGpa(student.getGpa())) {
            throw new CustomException("Điểm GPA không hợp lệ!");
        }
        if (!StudentValidator.validateId(student.getId())) {
            throw new CustomException("ID sinh viên không hợp lệ!");
        }
        manager.addStudent(student);
    }

    // luu sinh vien vao file
    public void saveStudentsToFile() {
        try {
            StudentDAO.saveStudents(manager.getAllStudents());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //doc danh sach sinh vien tu file
    public List<Student> loadStudentsFromFile() {
        try {
            return StudentDAO.loadStudents();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // ghi danh sach sinh vien vao file
    public void saveStudentsToExcel(String fileName) {
        try {
            FileManager.writeToExcel(manager.getAllStudents(), fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
