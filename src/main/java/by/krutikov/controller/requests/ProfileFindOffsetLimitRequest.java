package by.krutikov.controller.requests;

import lombok.Data;

@Data
public class ProfileFindOffsetLimitRequest {
    private String offset;
    private String limit;
}
