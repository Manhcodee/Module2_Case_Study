package storage;

import model.Student;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//luu tru va truy xuat tu file nhi phan

public class StudentDAO {
    private static final String FILE_PATH = "students.dat";

    //luu danh sanh sinh vien vao file nhi phan
    public static void saveStudents(List<Student> students) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            out.writeObject(students);
        }
    }

    // doc danh sach sinh vien tu file nhi phan
    public static List<Student> loadStudents() throws IOException, ClassNotFoundException {
        File file = new File(FILE_PATH);
        if (!file.exists()) return new ArrayList<>();
        try (ObjectInputStream in  = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
                return (List<Student>) in.readObject();
        }
    }
}
