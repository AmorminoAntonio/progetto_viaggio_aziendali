package com.example.progettoSettimanaleSpringWebData.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class ApiError extends RuntimeException {
    private String message;
    private HttpStatus status;
}
