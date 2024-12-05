package controller;

import java.io.IOException;
import java.util.Scanner;

public class StudentApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentController controller = new StudentController();

        try {
            controller.loadStudentsFromCSV();  // Đọc dữ liệu từ CSV khi ứng dụng khởi động
        } catch (IOException e) {
            System.out.println("Lỗi khi đọc dữ liệu từ file.");
        }

        while (true) {
            System.out.println("\nChọn chức năng:");
            System.out.println("1. Thêm sinh viên");
            System.out.println("2. Sửa sinh viên");
            System.out.println("3. Xóa sinh viên");
            System.out.println("4. Hiển thị sinh viên");
            System.out.println("5. Lưu dữ liệu vào file CSV");
            System.out.println("6. Thoát");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Clear buffer

            switch (choice) {
                case 1: // Thêm sinh viên
                    System.out.print("Nhập ID: ");
                    String id = scanner.nextLine();
                    System.out.print("Nhập mã sinh viên: ");
                    String code = scanner.nextLine();
                    System.out.print("Nhập tên sinh viên: ");
                    String name = scanner.nextLine();
                    System.out.print("Nhập email: ");
                    String email = scanner.nextLine();
                    System.out.print("Nhập điểm trung bình GPA: ");
                    double gpa = scanner.nextDouble();
                    controller.addStudent(id, code, name, email, gpa);
                    break;

                case 2: // Sửa sinh viên
                    System.out.print("Nhập ID sinh viên cần sửa: ");
                    String updateId = scanner.nextLine();
                    System.out.print("Nhập tên mới: ");
                    String newName = scanner.nextLine();
                    System.out.print("Nhập email mới: ");
                    String newEmail = scanner.nextLine();
                    System.out.print("Nhập điểm GPA mới: ");
                    double newGpa = scanner.nextDouble();
                    controller.updateStudent(updateId, newName, newEmail, newGpa);
                    break;

                case 3: // Xóa sinh viên
                    System.out.print("Nhập ID sinh viên cần xóa: ");
                    String deleteId = scanner.nextLine();
                    controller.deleteStudent(deleteId);
                    break;

                case 4: // Hiển thị sinh viên
                    controller.displayStudents();
                    break;

                case 5: // Lưu dữ liệu vào file CSV
                    try {
                        controller.saveStudentsToCSV();
                    } catch (IOException e) {
                        System.out.println("Lỗi khi lưu dữ liệu.");
                    }
                    break;

                case 6: // Thoát
                    System.out.println("Cảm ơn bạn đã sử dụng chương trình!");
                    return;

                default:
                    System.out.println("Chọn không hợp lệ. Vui lòng thử lại.");
                    break;
            }
        }
    }
}
