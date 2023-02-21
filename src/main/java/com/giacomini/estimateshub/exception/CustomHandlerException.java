package com.giacomini.estimateshub.exception;

import com.giacomini.estimateshub.dto.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomHandlerException {

    @ExceptionHandler({EstimationNotFoundException.class, EstimatorNotFoundException.class, ProductNotFoundException.class})
    public ResponseEntity<ErrorDTO> handleErrorNotFound(EntityNotFoundException e){
        ErrorDTO error = ErrorDTO.builder()
                .error(HttpStatus.NOT_FOUND.value())
                .message(e.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler({ProductAlreadyExistsException.class, EstimatorAlreadyExistsException.class})
    public ResponseEntity<ErrorDTO> handleErrorAlreadyExists(EntityAlreadyExistsException e){
        ErrorDTO error = ErrorDTO.builder()
                .error(HttpStatus.CONFLICT.value())
                .message(e.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }
}
