package by.krutikov.controller.requests;

import lombok.Data;

@Data
public class MediaFindOffsetLimitRequest {
    private String offset;
    private String limit;
}
