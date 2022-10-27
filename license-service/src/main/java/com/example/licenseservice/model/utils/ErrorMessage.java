package com.example.licenseservice.model.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ErrorMessage {
    private String message;
    private String code;
    private String detail;


}
