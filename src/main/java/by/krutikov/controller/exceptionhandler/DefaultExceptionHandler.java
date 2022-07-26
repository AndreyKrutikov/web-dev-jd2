package by.krutikov.controller.exceptionhandler;

import by.krutikov.exception.NoSuchEntityException;
import by.krutikov.util.UUIDGenerator;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;

@ControllerAdvice
public class DefaultExceptionHandler {

    @ExceptionHandler({NoSuchEntityException.class, EmptyResultDataAccessException.class})
    public ResponseEntity<Object> handleEntityNotFountException(Exception e) {

        ErrorContainer error = ErrorContainer
                .builder()
                .exceptionId(UUIDGenerator.generateUUID())
                .errorCode(2)
                .errorMessage(e.getMessage())
                .e(e.getClass().toString()) //todo check it
                .build();

        return new ResponseEntity<>(Collections.singletonMap("not found", error), HttpStatus.NOT_FOUND);
    }
}
