package vttp.batch5.paf.day22workshop.exception;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import vttp.batch5.paf.day22workshop.model.exception.APIError;
import vttp.batch5.paf.day22workshop.model.exception.ResourceNotFoundException;

import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<APIError> handleException(Exception ex, HttpServletRequest request, HttpServletResponse response) {
        APIError apiError = new APIError(response.getStatus(), ex.getMessage(), new Date());
        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<APIError> handleIllegalArgumentException(IllegalArgumentException ex, HttpServletRequest request, HttpServletResponse response) {
        APIError apiError = new APIError(response.getStatus(), ex.getMessage(), new Date());
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<APIError> handleResourceNotFoundException(ResourceNotFoundException ex, HttpServletRequest request, HttpServletResponse response) {
        APIError apiError = new APIError(404, ex.getMessage(), new Date());
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<APIError> handleDataAccessException(DataAccessException ex, HttpServletRequest request, HttpServletResponse response) {
        APIError apiError = new APIError(400, "Record not found", new Date());
        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
