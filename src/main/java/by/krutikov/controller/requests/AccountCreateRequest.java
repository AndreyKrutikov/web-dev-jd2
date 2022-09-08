package by.krutikov.controller.requests;

import lombok.Data;

@Data
public class AccountCreateRequest {
    private String login;
    private String password;
    private String email;
}
