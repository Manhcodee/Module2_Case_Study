package controller;

import model.*;
import storage.StudentStorage;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentApp {
    private static List<Student> students = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
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
        System.out.print("Nhập mã sinh viên: ");
        String studentId = scanner.nextLine();

        System.out.print("Nhập tên sinh viên: ");
        String name = scanner.nextLine();

        System.out.print("Nhập giới tính (MALE/FEMALE/OTHER): ");
        Gender gender = Gender.valueOf(scanner.nextLine().toUpperCase());

        System.out.print("Nhập địa chỉ (City, District, Street): ");
        String[] addressParts = scanner.nextLine().split(",");
        Address address = new Address(addressParts[0], addressParts[1], addressParts[2]);

        List<Score> scores = new ArrayList<>();
        for (Subject subject : Subject.values()) {
            System.out.print("Nhập điểm môn " + subject + ": ");
            double score = Double.parseDouble(scanner.nextLine());
            scores.add(new Score(subject, score));
        }

        students.add(new Student(studentId, name, gender, address, scores));
        System.out.println("Thêm sinh viên thành công!");
    }

    private static void updateStudent() {
        System.out.print("Nhập mã sinh viên cần sửa: ");
        String studentId = scanner.nextLine();
        for (Student student : students) {
            if (student.getStudentId().equals(studentId)) {
                System.out.print("Nhập tên mới: ");
                student.getName();
                break;
            }
        }
    }

    private static void deleteStudent() {
        System.out.print("Nhập mã sinh viên cần xóa: ");
        String studentId = scanner.nextLine();
        students.removeIf(student -> student.getStudentId().equals(studentId));
        System.out.println("Xóa sinh viên thành công!");
    }

    private static void displayStudents() {
        for (Student student : students) {
            System.out.println(student);
        }
    }

    private static void saveStudents() {
        StudentStorage.saveStudents(students);
        System.out.println("Lưu danh sách sinh viên thành công!");
    }
}
