package controller;

import dao.StudentDAO;
import dao.StudentDAOImpl;
import model.*;
import storage.StudentStorage;
import util.Validator;

import java.util.List;
import java.util.Scanner;

public class StudentApp {

    private static final Scanner scanner = new Scanner(System.in);
    private static final StudentDAO studentDAO = new StudentDAOImpl();

    public static void main(String[] args) {
        System.out.println("Danh sách sinh viên");
        List<Student> students = studentDAO.getAllStudents();
        if (students.isEmpty()) {
            System.out.println("Danh sách sinh viên trống.");
        } else {
            displayStudents();
        }

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

    //hiển thị danh sách
    private static void displayStudents() {
        List<Student> students = studentDAO.getAllStudents();
        System.out.printf("%-10s %-20s %-20s %-10s %-30s %-80s %-10s %-10s\n",
                "ID", "Name", "Email", "Gender", "Address", "Scores", "Avg Score", "Rank");

        for (Student student : students) {
            StringBuilder scoresStr = new StringBuilder();
            for (Score score : student.getScores()) {
                scoresStr.append(score.getSubject()).append(": ").append(score.getScore()).append(", ");
            }
            if (scoresStr.length() > 0) {
                scoresStr.setLength(scoresStr.length() - 2);
            }
            System.out.printf("%-10s %-20s %-20s %-10s %-30s %-80s %-10.2f %-10s\n",
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

    //thêm student
    private static void addStudent() {
        String studentId;
        while (true) {
            System.out.print("Nhập mã sinh viên: ");
            studentId = scanner.nextLine();

            if (!Validator.isValidStudentId(studentId)) {
                System.out.println("ID sinh viên không hợp lệ! Chỉ được chứa chữ cái và số.");
                continue;
            }

            if (studentDAO.getStudentById(studentId) != null) {
                System.out.println("ID này đã tồn tại, vui lòng nhập ID khác.");
                continue;
            }

            break;
        }

        System.out.print("Nhập tên sinh viên: ");
        String name = scanner.nextLine();

        //nhập email
        String email;
        while (true) {
            System.out.print("Nhập email: ");
            email = scanner.nextLine();
            if (!Validator.isValidEmail(email)) {
                System.out.println("Email không hợp lệ, vui lòng nhập lại!");
            } else {
                break;
            }
        }

        //nhập giới tính
        System.out.print("Nhập giới tính (MALE/FEMALE/OTHER): ");
        String gender;
        while (true) {
            gender = scanner.nextLine().toUpperCase();
            if (gender.equals("MALE") || gender.equals("FEMALE") || gender.equals("OTHER")) {
                break;
            } else {
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

        List<Score> scores = StudentFactory.createDefaultScores();
        for (Score score : scores) {
            System.out.print("Nhập điểm môn " + score.getSubject() + ": ");
            double scoreValue;
            while (true) {
                try {
                    scoreValue = Double.parseDouble(scanner.nextLine());
                    if (scoreValue >= 0 && scoreValue <= 10) {
                        break;
                    } else {
                        System.out.println("Điểm phải nằm trong khoảng 0-10, vui lòng nhập lại!");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Giá trị không hợp lệ. Vui lòng nhập lại!");
                }
            }
            score.setScore(scoreValue);
        }

        Student student = StudentFactory.createStudent(studentId, name, email, gender, address, scores);
        studentDAO.addStudent(student);

        System.out.println("Thêm sinh viên thành công!");
    }

    private static void updateStudent() {
        System.out.print("Nhập mã sinh viên cần sửa: ");
        String studentId = scanner.nextLine();

        // Tìm sinh viên theo ID
        Student student = studentDAO.getStudentById(studentId);
        if (student == null) {
            System.out.println("Không tìm thấy sinh viên với mã ID này.");
            return;
        }

        System.out.println("Chọn thông tin cần sửa:");
        System.out.println("1. Sửa thông tin cơ bản (Tên, Email, Giới tính, Địa chỉ)");
        System.out.println("2. Sửa điểm các môn học");
        System.out.print("Lựa chọn: ");
        int choice = Integer.parseInt(scanner.nextLine());

        switch (choice) {
            case 1:
                updateBasicInfo(student);
                break;
            case 2:
                updateScores(student);
                break;
            default:
                System.out.println("Lựa chọn không hợp lệ!");
        }

        // Cập nhật sinh viên trong DAO
        studentDAO.updateStudent(studentId, student);
        System.out.println("Sửa thông tin sinh viên thành công!");
    }

    // Sửa thông tin cơ bản
    private static void updateBasicInfo(Student student) {
        System.out.print("Nhập tên mới (hiện tại: " + student.getName() + "): ");
        String name = scanner.nextLine();
        if (!name.isEmpty()) {
            student.setName(name);
        }

        String email;
        while (true) {
            System.out.print("Nhập email mới (hiện tại: " + student.getEmail() + "): ");
            email = scanner.nextLine();
            if (email.isEmpty()) {
                break;
            }
            if (Validator.isValidEmail(email)) {
                student.setEmail(email);
                break;
            } else {
                System.out.println("Email không hợp lệ, vui lòng nhập lại!");
            }
        }

        System.out.print("Nhập giới tính mới (hiện tại: " + student.getGender() + "): ");
        String gender = scanner.nextLine();
        if (!gender.isEmpty()) {
            if (gender.equalsIgnoreCase("MALE") || gender.equalsIgnoreCase("FEMALE") || gender.equalsIgnoreCase("OTHER")) {
                student.setGender(gender);
            } else {
                System.out.println("Giới tính không hợp lệ! Sử dụng giá trị hiện tại.");
            }
        }

        System.out.print("Nhập địa chỉ mới (City, District, Street - hiện tại: " + student.getAddress() + "): ");
        String addressInput = scanner.nextLine();
        if (!addressInput.isEmpty()) {
            String[] addressParts = addressInput.split(",\\s*");
            if (addressParts.length == 3) {
                student.setAddress(new Address(addressParts[0], addressParts[1], addressParts[2]));
            } else {
                System.out.println("Địa chỉ không hợp lệ! Sử dụng giá trị hiện tại.");
            }
        }
    }

    // Sửa điểm các môn học
    private static void updateScores(Student student) {
        List<Score> scores = student.getScores();
        for (Score score : scores) {
            System.out.print("Nhập điểm mới cho môn " + score.getSubject() + " (hiện tại: " + score.getScore() + "): ");
            String input = scanner.nextLine();
            if (!input.isEmpty()) {
                try {
                    double newScore = Double.parseDouble(input);
                    if (newScore >= 0 && newScore <= 10) {
                        score.setScore(newScore);
                    } else {
                        System.out.println("Điểm phải nằm trong khoảng 0-10! Giữ nguyên điểm hiện tại.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Giá trị không hợp lệ! Giữ nguyên điểm hiện tại.");
                }
            }
        }
    }

    private static void deleteStudent() {
        System.out.print("Nhập mã sinh viên cần xóa: ");
        String studentId = scanner.nextLine();

        Student student = studentDAO.getStudentById(studentId);
        if (student == null) {
            System.out.println("Không tìm thấy sinh viên với mã ID này.");
            return;
        }

        studentDAO.deleteStudent(studentId);
        System.out.println("Xóa sinh viên thành công!");
    }

    private static void saveStudents() {
        studentDAO.saveStudents();
        System.out.println("Lưu danh sách sinh viên thành công!");
    }
}
