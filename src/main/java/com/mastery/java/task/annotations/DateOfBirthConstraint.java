package com.mastery.java.task.annotations;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Target(value = FIELD)
// where do u want to be able to add the annotation (class, field, method, etc)
@Retention(RetentionPolicy.RUNTIME) //will it validate during runtime or compilation?
@Documented  //чтобы моя аннотация записалась в джаваДок (документация данного проекта)
@Constraint(validatedBy = DateOfBirthValidator.class) // tells spring which class holds the logic for the validation

public @interface DateOfBirthConstraint {

    String message() default "{Employee's age must be between 18 and 70}";

    //represents group of constraints
    public Class<?>[] groups() default {};

    //represents additional information about annotation
    public Class<? extends Payload>[] payload() default {};
}
