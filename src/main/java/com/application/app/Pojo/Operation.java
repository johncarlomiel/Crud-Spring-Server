package com.application.app.Pojo;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Operation {
    private String message;
    private Boolean isSuccess;
}
