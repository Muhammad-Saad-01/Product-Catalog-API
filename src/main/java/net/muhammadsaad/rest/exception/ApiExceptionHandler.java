package net.muhammadsaad.rest.exception;

import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleGenericException(Exception ex) {
        return new ResponseEntity<>("An error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = { ChangeSetPersister.NotFoundException.class})
    public ResponseEntity<Object> handleNotFoundException(ChangeSetPersister.NotFoundException ex) {
        return new ResponseEntity<>("Not found: " + ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {ResourceNotFoundException.class})
    public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return new ResponseEntity<>("Resource not found: " + ex.getMessage(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(value = {ResourceConflictException.class})
    public ResponseEntity<Object> handleResourceAlreadyExistException(ResourceConflictException ex) {
        return new ResponseEntity<>("Resource conflict: " + ex.getMessage(), HttpStatus.CONFLICT);
    }
    @ExceptionHandler(value = {InvalidRequestException.class})
    public ResponseEntity<Object> handleInvalidRequestException(InvalidRequestException ex) {
        return new ResponseEntity<>("Invalid request: " + ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}