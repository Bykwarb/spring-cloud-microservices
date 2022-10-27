package com.example.licenseservice.model.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
public class ResponseWrapper {
    private Object data;
    private Object metadata;
    private List<ErrorMessage> errors;
}
