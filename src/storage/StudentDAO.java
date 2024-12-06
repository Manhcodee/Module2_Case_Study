package storage;

import model.Student;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    private static final String FILE_PATH = "data/students.csv";

    // Đọc sinh viên từ file CSV
    public static List<Student> readStudentsFromCSV() throws IOException {
        List<Student> students = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH));
        String line;

        // Đọc từng dòng trong file CSV
        while ((line = reader.readLine()) != null) {
            String[] data = line.split(",");

            // Bỏ qua dòng tiêu đề hoặc kiểm tra xem dữ liệu có hợp lệ không
            if (data.length != 5) {
                continue; // Bỏ qua các dòng không hợp lệ
            }

            String id = data[0];
            String code = data[1];
            String name = data[2];
            String email = data[3];
            double gpa = 0.0;

            // Kiểm tra và chuyển đổi GPA từ chuỗi thành double
            try {
                gpa = Double.parseDouble(data[4]);
            } catch (NumberFormatException e) {
                continue; // Bỏ qua sinh viên nếu GPA không hợp lệ
            }

            // Tạo đối tượng Student và thêm vào danh sách
            students.add(new Student(id, code, name, email, gpa));
        }

        reader.close();
        return students;
    }

    // Lưu sinh viên vào file CSV
    public static void saveStudentsToCSV(List<Student> students) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH));
        writer.write("ID,Code,Name,Email,GPA\n");

        for (Student student : students) {
            writer.write(student.getId() + "," + student.getCode() + "," + student.getName() + "," + student.getEmail() + "," + student.getGpa() + "\n");
        }

        writer.close();
    }
}
