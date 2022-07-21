package by.krutikov;

import java.util.List;

public class StudentInit {
    public static final List<Student> getPreparedStudents = List.of(
            new Student(1, "vasya", "minsk", 18, "1234567", Faculty.ABC),
            new Student(2, "petya", "minsk", 19, "1234568", Faculty.DEF),
            new Student(3, "masha", "moscow", 20, "1234569", Faculty.ABC),
            new Student(4, "vova", "minsk", 23, "1234512",Faculty.DEF),
            new Student(5, "olya", "minsk", 24, "1234547",Faculty.ABC),
            new Student(6, "dima", "minsk", 25, "1234577",Faculty.DEF)
    );
}
