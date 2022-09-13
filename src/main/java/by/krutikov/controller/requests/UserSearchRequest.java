package by.krutikov.controller.requests;

import lombok.Data;

@Data
@Deprecated
public class UserSearchRequest {
    private String name;
    private String surname;
}
