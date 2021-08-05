package com.example.demo.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.example.demo.vadidator.DateNotOverNowValidator;

@Documented
@Constraint(validatedBy = DateNotOverNowValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD }) // Class-level constraints
@Retention(RetentionPolicy.RUNTIME)
public @interface DateNotOverNow {

    String message() default "datetime must be less now";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}