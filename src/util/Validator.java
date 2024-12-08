package util;

import java.util.regex.Pattern;

public class Validator {
    private static final String EMAIL_REGEX = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";

    public static boolean isValidEmail(String email) {
        return Pattern.matches(EMAIL_REGEX, email);
    }

    public static boolean isValidStudentId(String studentId) {
        return studentId != null && studentId.matches("^[a-zA-Z0-9]+$");
    }
}
