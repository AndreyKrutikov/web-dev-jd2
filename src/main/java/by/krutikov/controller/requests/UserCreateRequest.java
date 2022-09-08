package by.krutikov.controller.requests;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class UserCreateRequest {
    private String name;
    private String surname;
    private Timestamp dateOfBirth;
    private Byte rating;
}
