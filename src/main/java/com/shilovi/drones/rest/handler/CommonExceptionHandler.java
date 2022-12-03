package com.shilovi.drones.rest.handler;

import com.shilovi.drones.exception.NotFoundException;
import com.shilovi.drones.model.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice(annotations = RestController.class)
@Slf4j
public class CommonExceptionHandler {

    @ExceptionHandler({
            MissingServletRequestParameterException.class,
            MethodArgumentTypeMismatchException.class,
            IllegalArgumentException.class,
            UnsupportedOperationException.class,
            MethodArgumentNotValidException.class
    })
    ResponseEntity<ErrorResponse> badRequestException(Exception e) {
        logger.error("Bad request! Message : {}", e.getMessage(), e);
        return ResponseEntity.status(BAD_REQUEST)
                .body(ErrorResponse
                        .builder()
                        .error(e.getMessage())
                        .build());
    }

    @ExceptionHandler(NotFoundException.class)
    ResponseEntity<ErrorResponse> notFoundException(Exception e) {
        logger.error("Not found exception! Message : {}", e.getMessage(), e);
        return ResponseEntity.status(NOT_FOUND)
                .body(ErrorResponse
                        .builder()
                        .error(e.getMessage())
                        .build());
    }


    @ExceptionHandler(Exception.class)
    ResponseEntity<Void> internalServerError(Exception e) {
        logger.error("Internal server error! Message : {}", e.getMessage(), e);
        return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
    }

}