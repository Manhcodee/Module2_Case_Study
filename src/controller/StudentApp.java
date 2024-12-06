package controller;

import java.util.Scanner;

public class StudentApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentController controller = new StudentController();

        while (true) {
            System.out.println("\nChọn chức năng:");
            System.out.println("1. Thêm sinh viên");
            System.out.println("2. Sửa thông tin sinh viên");
            System.out.println("3. Sửa điểm môn học");
            System.out.println("4. Hiển thị thông tin sinh viên");
            System.out.println("5. Thoát");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Clear buffer

            switch (choice) {
                case 1:
                    System.out.print("Nhập ID: ");
                    String id = scanner.nextLine();
                    System.out.print("Nhập Mã sinh viên: ");
                    String code = scanner.nextLine();
                    System.out.print("Nhập Tên: ");
                    String name = scanner.nextLine();
                    System.out.print("Nhập Email: ");
                    String email = scanner.nextLine();
                    controller.addStudent(id, code, name, email);
                    break;
                case 2:
                    System.out.print("Nhập ID sinh viên muốn sửa: ");
                    id = scanner.nextLine();
                    System.out.print("Nhập Mã sinh viên mới: ");
                    code = scanner.nextLine();
                    System.out.print("Nhập Tên mới: ");
                    name = scanner.nextLine();
                    System.out.print("Nhập Email mới: ");
                    email = scanner.nextLine();
                    controller.updateStudentInfo(id, code, name, email);
                    break;
                case 3:
                    System.out.print("Nhập ID sinh viên: ");
                    id = scanner.nextLine();
                    System.out.print("Nhập Môn học: ");
                    String subject = scanner.nextLine();
                    System.out.print("Nhập điểm: ");
                    double score = scanner.nextDouble();
                    controller.updateScore(id, subject, score);
                    break;
                case 4:
                    System.out.print("Nhập ID sinh viên: ");
                    id = scanner.nextLine();
                    controller.displayStudent(id);
                    break;
                case 5:
                    System.out.println("Thoát khỏi chương trình.");
                    return;
            }
        }
    }
}
