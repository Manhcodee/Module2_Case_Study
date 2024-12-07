package controller;

import model.*;
import storage.StudentStorage;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentApp {
    private static final String EMAIL_REGEX = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
    private static StudentStorage storage = StudentStorage.getInstance();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // đọc file
        storage.loadStudents();

        while (true) {
            System.out.println("1. Thêm sinh viên");
            System.out.println("2. Sửa sinh viên");
            System.out.println("3. Xóa sinh viên");
            System.out.println("4. Hiển thị danh sách sinh viên");
            System.out.println("5. Lưu danh sách sinh viên");
            System.out.println("0. Thoát");
            System.out.print("Chọn chức năng: ");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    updateStudent();
                    break;
                case 3:
                    deleteStudent();
                    break;
                case 4:
                    displayStudents();
                    break;
                case 5:
                    saveStudents();
                    break;
                case 0:
                    System.exit(0);
                default:
                    System.out.println("Chức năng không hợp lệ!");
            }
        }
    }

    private static void addStudent() {
        String studentId;
        while (true) {
            System.out.print("Nhập mã sinh viên: ");
            studentId = scanner.nextLine();

            boolean idExists = false;
            for (Student student : storage.getStudents()) {
                if (student.getStudentId().equals(studentId)) {
                    idExists = true;
                    break;
                }
            }

            if (idExists) {
                System.out.println("ID này đã tồn tại, vui lòng nhập ID khác.");
            } else {
                break;
            }
        }

        System.out.print("Nhập tên sinh viên: ");
        String name = scanner.nextLine();

        System.out.print("Nhập email: ");
        String email;
        while (true) {
            email = scanner.nextLine();
            if (email.matches(EMAIL_REGEX)) {
                break;
            } else {
                System.out.println("Email không hợp lệ, vui lòng nhập lại!");
            }
        }

        System.out.print("Nhập giới tính (MALE/FEMALE/OTHER): ");
        Gender gender;
        while (true) {
            try {
                gender = Gender.valueOf(scanner.nextLine().toUpperCase());
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Giới tính không hợp lệ, vui lòng nhập lại (MALE/FEMALE/OTHER)!");
            }
        }

        System.out.print("Nhập địa chỉ (City, District, Street): ");
        Address address;
        while (true) {
            String addressInput = scanner.nextLine();
            String[] addressParts = addressInput.split(",\\s*");
            if (addressParts.length == 3) {
                address = new Address(addressParts[0], addressParts[1], addressParts[2]);
                break;
            } else {
                System.out.println("Địa chỉ không hợp lệ. Vui lòng nhập theo định dạng (City, District, Street):");
            }
        }

        List<Score> scores = new ArrayList<>();
        for (Subject subject : Subject.values()) {
            System.out.print("Nhập điểm môn " + subject.getDisplayName() + ": ");
            double score;
            while (true) {
                try {
                    score = Double.parseDouble(scanner.nextLine());
                    if (score >= 0 && score <= 10) {
                        break;
                    } else {
                        System.out.println("Điểm phải nằm trong khoảng 0-10, vui lòng nhập lại!");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Giá trị không hợp lệ. Vui lòng nhập lại!");
                }
            }
            scores.add(new Score(subject, score));
        }

        Student student = new Student(studentId, name, email, gender, address, scores);
        storage.getStudents().add(student);

        System.out.println("Thêm sinh viên thành công!");
    }


    private static void updateStudent() {
        System.out.print("Nhập mã sinh viên cần sửa: ");
        String studentId = scanner.nextLine();

        List<Student> students = storage.getStudents();
        Student student = null;

        // Duyệt qua danh sách để tìm sinh viên
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getStudentId().equals(studentId)) {
                student = students.get(i);
                break;
            }
        }

        if (student == null) {
            System.out.println("Không tìm thấy sinh viên với mã ID này.");
            return;
        }

        System.out.println("1. Sửa thông tin cơ bản (Tên, Địa chỉ, Email)");
        System.out.println("2. Sửa điểm các môn học");
        System.out.print("Chọn chức năng: ");
        int choice = Integer.parseInt(scanner.nextLine());

        switch (choice) {
            case 1:
                updateBasicInfo(student);
                break;
            case 2:
                updateScores(student);
                break;
            default:
                System.out.println("Chức năng không hợp lệ!");
        }
    }

    private static void updateBasicInfo(Student student) {
        System.out.print("Nhập tên mới: ");
        student.setName(scanner.nextLine());

        System.out.print("Nhập email mới: ");
        String email;
        while (true) {
            email = scanner.nextLine();
            if (email.matches(EMAIL_REGEX)) {
                break;
            } else {
                System.out.println("Email không hợp lệ, vui lòng nhập lại!");
            }
        }
        student.setEmail(email);

        System.out.print("Nhập địa chỉ mới (City, District, Street): ");
        String[] addressParts = scanner.nextLine().split(",");
        student.setAddress(new Address(addressParts[0], addressParts[1], addressParts[2]));

        System.out.println("Sửa thông tin cơ bản thành công!");
    }

    private static void updateScores(Student student) {
        for (Score score : student.getScores()) {
            System.out.print("Nhập điểm môn " + score.getSubject() + " (Điểm hiện tại: " + score.getScore() + "): ");
            double newScore = Double.parseDouble(scanner.nextLine());
            score.setScore(newScore);
        }
        System.out.println("Cập nhật điểm thành công!");
    }

    private static void deleteStudent() {
        System.out.print("Nhập mã sinh viên cần xóa: ");
        String studentId = scanner.nextLine();

        List<Student> students = storage.getStudents();
        boolean found = false;

        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getStudentId().equals(studentId)) {
                students.remove(i);
                found = true;
                System.out.println("Xóa sinh viên thành công!");
                break;
            }
        }
        if (!found) {
            System.out.println("Không tìm thấy sinh viên với mã ID này.");
        }
    }

    private static void displayStudents() {
        System.out.printf("%-10s %-20s %-20s %-10s %-30s %-70s %-10s %-20s\n",
                "ID", "Name", "Email", "Gender", "Address", "Scores", "Avg Score", "Rank");

        for (Student student : storage.getStudents()) {
            // Tạo chuỗi chứa danh sách điểm các môn học
            StringBuilder scoresStr = new StringBuilder();
            for (Score score : student.getScores()) {
                scoresStr.append(score.getSubject()).append(", ").append(score.getScore()).append(", ");
            }

            // Loại bỏ dấu phẩy cuối cùng trong chuỗi điểm
            if (scoresStr.length() > 0) {
                scoresStr.setLength(scoresStr.length() - 2);
            }

            // In thông tin sinh viên
            System.out.printf("%-10s %-20s %-20s %-10s %-30s %-70s %-10.2f %-10s\n",
                    student.getStudentId(),
                    student.getName(),
                    student.getEmail(),
                    student.getGender(),
                    student.getAddress(),
                    scoresStr.toString(),
                    student.calculateAverageScore(),
                    student.getRank());
        }
    }

    private static void saveStudents() {
        storage.saveStudents();
        System.out.println("Lưu danh sách sinh viên thành công!");
    }
}
