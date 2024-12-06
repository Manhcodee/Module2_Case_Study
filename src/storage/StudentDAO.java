package dao;

import model.Student;
import java.util.*;
import java.io.*;
import java.util.regex.*;

public class StudentDAO {
    private static final String FILE_PATH = "data/students.csv";

    public void saveStudent(Student student) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(student.getId() + "," + student.getCode() + "," + student.getName() + "," + student.getEmail());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 4) {
                    students.add(new Student(data[0], data[1], data[2], data[3]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return students;
    }

    public Student getStudentById(String id) {
        for (Student student : getAllStudents()) {
            if (student.getId().equals(id)) {
                return student;
            }
        }
        return null;
    }

    public void deleteStudent(String id) {
        List<Student> students = getAllStudents();
        students.removeIf(student -> student.getId().equals(id));
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Student student : students) {
                writer.write(student.getId() + "," + student.getCode() + "," + student.getName() + "," + student.getEmail());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
