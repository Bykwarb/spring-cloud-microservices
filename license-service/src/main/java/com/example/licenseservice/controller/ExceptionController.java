package com.example.licenseservice.controller;

import com.example.licenseservice.model.utils.ErrorMessage;
import com.example.licenseservice.model.utils.ResponseWrapper;
import com.example.licenseservice.model.utils.RestErrorList;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;

@ControllerAdvice
@EnableWebMvc
public class ExceptionController {
    @ExceptionHandler(value = {Exception.class})
    public @ResponseBody ResponseEntity<ResponseWrapper> handleException(HttpServletRequest request,
                                                                         ResponseWrapper wrapper){
        return ResponseEntity.ok(wrapper);
    }

    @ExceptionHandler(value = {RuntimeException.class})
    public ResponseEntity<ResponseWrapper> handleIOException(HttpServletRequest request,
                                                             RuntimeException e){

        RestErrorList errorList = new RestErrorList(
                HttpStatus.NOT_ACCEPTABLE,
                new ErrorMessage(e.getMessage(), e.getMessage(), ""));

        ResponseWrapper responseWrapper = new ResponseWrapper(
                null,
                Collections.singletonMap("status", HttpStatus.NOT_ACCEPTABLE),
                errorList);

        return ResponseEntity.ok(responseWrapper);
    }
}
