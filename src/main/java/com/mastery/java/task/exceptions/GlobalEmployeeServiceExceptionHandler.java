package com.mastery.java.task.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


//@ControllerAdvice is a specialization of the @Component annotation which allows to handle exceptions  (контроллер для ошибок)
// across the whole application in one global handling component.
// It can be viewed as an interceptor of exceptions thrown by methods annotated with @RequestMapping and similar.
@RestControllerAdvice
// this is a combination of @ControllerAdvice + @ResponseBody  // without this we would need to add the @ExceptionHandler to all our Controller classes
public class GlobalEmployeeServiceExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalEmployeeServiceExceptionHandler.class);


    @ExceptionHandler(EmployeeServiceNotFoundException.class)
    //Annotation for handling exceptions in specific handler classes and/or handler methods
    @ResponseStatus(HttpStatus.NOT_FOUND)
    // the status could be OK(200) although the data corresponds to exception signal (404 – Not Found for example). @ResponseStatus help set the HTTP status code for the response
    public String throwResourceNotFoundException(EmployeeServiceNotFoundException resourceNotFoundException) {
        logger.error("EmployeeServiceNotFoundException Thrown ", resourceNotFoundException);
        return resourceNotFoundException.getMessage();

    }

    //this is the handler for all other errors
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public String catchGlobalExceptions(Exception globalException) {
        logger.error("Global Exception thrown ", globalException);
        return globalException.getMessage();
    }


}
//@ResponseStatus
//Marks a method or exception class with the status code and reason that should be returned.
//The status code is applied to the HTTP response when the handler method is invoked and overrides status information set by other means