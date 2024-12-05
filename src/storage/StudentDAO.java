package storage;

import model.Student;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//luu tru va truy xuat tu file nhi phan

public class StudentDAO {
    // Lưu danh sách sinh viên vào file CSV
    // Lưu danh sách sinh viên vào file CSV
    public static void saveStudentsToCSV(List<Student> students, String filePath) throws IOException {
        try (FileWriter writer = new FileWriter(filePath)) {
            // Ghi tiêu đề
            writer.write("id,code,name,email,gpa\n");

            // Ghi dữ liệu sinh viên
            for (Student student : students) {
                writer.write(student.toCSV() + "\n");
            }
        }
    }
}
