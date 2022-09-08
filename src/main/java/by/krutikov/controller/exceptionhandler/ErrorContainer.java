package by.krutikov.controller.exceptionhandler;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class ErrorContainer {
    private String exceptionId;

    private String errorMessage;

    private int errorCode;

    private String e;
}
