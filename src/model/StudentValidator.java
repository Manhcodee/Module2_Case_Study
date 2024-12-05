package model;

import java.util.regex.Pattern;

public class StudentValidator {

    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(.+)$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    // Kiểm tra tính hợp lệ của email
    public static boolean validateEmail(String email) {
        return EMAIL_PATTERN.matcher(email).matches();
    }

    // Kiểm tra tính hợp lệ của id
    public static boolean validateId(String id) {
        return id != null && !id.isEmpty() && id.matches("[A-Za-z0-9]+");
    }

    // Kiểm tra tính hợp lệ của điểm trung bình GPA
    public static boolean validateGPA(double gpa) {
        return gpa >= 0.0 && gpa <= 10.0;
    }
}
