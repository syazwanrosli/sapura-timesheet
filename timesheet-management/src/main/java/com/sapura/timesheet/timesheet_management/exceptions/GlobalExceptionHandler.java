package com.sapura.timesheet.timesheet_management.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    // //Handle specific exception: TimesheetNotFoundException
    // @ExceptionHandler(TimesheetNotFoundException.class)
    // public  ResponseEntity<String> handleTimesheetNotFoundException(TimesheetNotFoundException ex, WebRequest request) {
    //     return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    // }

    // //Handle UserNotFoundException
    // @ExceptionHandler(UserNotFoundException.class)
    // public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException ex, WebRequest request) {
    //     return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    // }

    // Handle all uncaught exceptions(generic)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGlobalException(Exception ex, WebRequest request) {
        return new ResponseEntity<>("An expected error occured: " + ex.getMessage(),  HttpStatus.INTERNAL_SERVER_ERROR);
    }



}
