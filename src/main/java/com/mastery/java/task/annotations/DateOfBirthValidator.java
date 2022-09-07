package com.mastery.java.task.annotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DateOfBirthValidator implements ConstraintValidator<DateOfBirthConstraint, LocalDate> {

    public boolean isValid(LocalDate dateOfBirth, ConstraintValidatorContext context) {

        long age = ChronoUnit.YEARS.between(dateOfBirth, LocalDate.now());
        return age > 17 && age < 71;
    }
}
