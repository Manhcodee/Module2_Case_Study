package model;

import java.util.regex.Pattern;

public class StudentValidator {
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(.+)$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    //kiem tra email hop le
    public static boolean validateEmail(String email) {
        return EMAIL_PATTERN.matcher(email).matches();
    }

    // kiem tra diem GPA hop le

    public static boolean validateGpa(double gpa) {
        return gpa >= 0 && gpa <= 4.0;
    }

    // kiem tra ID sinh vien (chu va so)
    public static boolean validateId(String id) {
        return id != null && id.isEmpty() && id.matches("[A-Za-z0-9]+");
    }
}
