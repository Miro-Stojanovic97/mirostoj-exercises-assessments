package pets.controllers;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

    //DataAccessException - File I/O database
    //IllegalArgumentException // internal server errors
    //Exception

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<ErrorResponse> handleException(DataAccessException ex) {
            // log the exception
            return new ResponseEntity<ErrorResponse>( new ErrorResponse("We have encountered an issue with our database. We apologize"),
                                                         HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleException(IllegalArgumentException ex) {
        // log the exception
        return new ResponseEntity<ErrorResponse>( new ErrorResponse(ex.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(IllegalArgumentException ex, HttpServletResponse response) {
        // log the exception
        if(response.getStatus() >= 400 && response.getStatus() < 500)
        {
            return new ResponseEntity<ErrorResponse>( new ErrorResponse(ex.getMessage()),
                    HttpStatus.valueOf(response.getStatus()));
        }
        return new ResponseEntity<ErrorResponse>(new ErrorResponse("Something went wrong. Please debug"), HttpStatus.INTERNAL_SERVER_ERROR);

    }

}
