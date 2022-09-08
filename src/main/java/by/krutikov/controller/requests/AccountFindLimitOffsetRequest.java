package by.krutikov.controller.requests;

import lombok.Data;

@Data
public class AccountFindLimitOffsetRequest {
    private String limit;
    private String offset;
}
