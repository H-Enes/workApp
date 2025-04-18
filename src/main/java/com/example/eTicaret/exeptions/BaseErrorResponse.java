package com.example.eTicaret.exeptions;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BaseErrorResponse {
    private String message;
    private int status;
    private LocalDateTime timestamp;
    private String path;
}
