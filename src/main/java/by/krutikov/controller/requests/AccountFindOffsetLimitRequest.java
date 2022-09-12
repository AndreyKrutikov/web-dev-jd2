package by.krutikov.controller.requests;

import lombok.Data;

@Data
public class AccountFindOffsetLimitRequest {
    private String limit;
    private String offset;
}
