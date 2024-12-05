package model;

public class StudentFactory {
    public static Student createStudent(String id, String code, String name, String email, double gpa) {
        return new Student(id, code, name, email, gpa);
    }
}
