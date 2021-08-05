package com.example.demo.vadidator;

import java.time.LocalDateTime;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.example.demo.annotation.DateNotOverNow;

public class DateNotOverNowValidator implements ConstraintValidator<DateNotOverNow, LocalDateTime> {

    @Override
    public boolean isValid(LocalDateTime dateTime, ConstraintValidatorContext context) {
        return dateTime != null && dateTime.isBefore(LocalDateTime.now());
    }

}
