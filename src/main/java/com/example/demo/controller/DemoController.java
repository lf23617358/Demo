package com.example.demo.controller;

import javax.validation.Valid;

import com.example.demo.dto.DemoDTO;
import com.example.demo.service.DemoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "demo")
public class DemoController {
    @Autowired
    private DemoService demoService;

    @PostMapping
    public String insertData(@Valid @RequestBody DemoDTO demoDTO) {
        demoService.insert(demoDTO);
        return "OK";
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handle(MethodArgumentNotValidException exception) {
        FieldError fieldError = exception.getBindingResult().getFieldError();
        return new ResponseEntity<String>(fieldError.getDefaultMessage(), null, HttpStatus.BAD_REQUEST);
    }

}