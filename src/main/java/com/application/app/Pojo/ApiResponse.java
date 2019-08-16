package com.application.app.Pojo;


import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ApiResponse {
    private String status;
    private String message;
    private Integer code;
}
