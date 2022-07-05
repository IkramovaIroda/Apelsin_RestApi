package com.project.apelsin.dto;

import jdk.jfr.DataAmount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {
    private String message;
    private boolean success;
    private Object obj;

    public ApiResponse(String message, boolean success) {
        this.message = message;
        this.success = success;
    }
}
