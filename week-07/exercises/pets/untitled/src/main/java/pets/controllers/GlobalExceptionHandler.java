package pets.controllers;


import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;

@ControllerAdvice
public class GlobalExceptionHandler {

    //DataAccessException - File IO

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<ErrorResponse> handleException(DataAccessException ex) {
        return new ResponseEntity<ErrorResponse>(new ErrorResponse("We have encountered an issue w our database."),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleException(IllegalArgumentException ex) {
        return new ResponseEntity<ErrorResponse>(new ErrorResponse(ex.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(IllegalArgumentException ex, HttpServletResponse response) {
        if (response.getStatus() >= 400 && response.getStatus() < 500) {
            return new ResponseEntity<ErrorResponse>(new ErrorResponse(ex.getMessage()),
                    HttpStatus.valueOf(response.getStatus()));
        }
        return new ResponseEntity<ErrorResponse>(new ErrorResponse("Something went wrong. Please debug."), HttpStatus.INTERNAL_SERVER_ERROR);

    }
}