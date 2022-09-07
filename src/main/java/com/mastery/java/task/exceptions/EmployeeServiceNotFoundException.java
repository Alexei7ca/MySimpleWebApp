package com.mastery.java.task.exceptions;

//RuntimeException is the superclass of those exceptions that can be thrown during the normal operation of the Java Virtual Machine.
// RuntimeException and its subclasses are unchecked exceptions.
public class EmployeeServiceNotFoundException extends RuntimeException {
    public EmployeeServiceNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}



