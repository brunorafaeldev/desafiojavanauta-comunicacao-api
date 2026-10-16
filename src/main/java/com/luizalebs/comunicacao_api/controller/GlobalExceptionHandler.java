package com.luizalebs.comunicacao_api.controller;


import com.luizalebs.comunicacao_api.business.dto.ErrorResponseDTO;
import com.luizalebs.comunicacao_api.infraestructure.exceptions.ConflictException;
import com.luizalebs.comunicacao_api.infraestructure.exceptions.IllegalArgumentException;
import com.luizalebs.comunicacao_api.infraestructure.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handlerResourceNotFoundException (ResourceNotFoundException ex,
                                                                              HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(buildError(HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                request.getRequestURI(),
                "Not found"

                ));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponseDTO> handlerIllegalArgumentException (IllegalArgumentException ex,
                                                                              HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(buildError(HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                request.getRequestURI(),
                "Bad Request"

        ));
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ErrorResponseDTO> handlerConclictExcepton (ConflictException ex,
                                                                     HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(buildError(HttpStatus.CONFLICT.value(),
                ex.getMessage(),
                request.getRequestURI(),
                "Conflict"

        ));
    }



    private ErrorResponseDTO buildError(int status, String messagem, String path, String error) {
        return ErrorResponseDTO.builder()
                .timestamp(LocalDateTime.now())
                .messagem(messagem)
                .path(path)
                .error(error)
                .status(status)
                .build();
    }


}
