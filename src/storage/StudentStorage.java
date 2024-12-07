package storage;

import model.Student;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StudentStorage {
    private static final String FILE_PATH = "students.csv";

    public static void saveStudents(List<Student> students) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Student student : students) {
                writer.write(student.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Student> loadStudents() {
        List<Student> students = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Logic for parsing students can be added here
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return students;
    }
}
