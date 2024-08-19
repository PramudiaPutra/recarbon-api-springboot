package api.recarbon.auth.exception;

import api.recarbon.auth.payload.UserResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice(annotations = RestController.class)
public class AuthExceptionHandler {
    @ExceptionHandler({UserNotFoundException.class, UserExistException.class})
    public final ResponseEntity<UserResponse> handleException(Exception ex, WebRequest request) {
        HttpHeaders headers = new HttpHeaders();

        if (ex instanceof UserNotFoundException exception) {
            HttpStatus status = HttpStatus.NOT_FOUND;
            return handleUserNotFoundException(exception, headers, status, request);

        } else if (ex instanceof UserExistException exception) {
            HttpStatus status = HttpStatus.CONFLICT;
            return handleUserExistException(exception, headers, status, request);

        } else {
            HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
            return exceptionHandlerResponse(ex,  null, headers, status, request);
        }
    }

    private ResponseEntity<UserResponse> handleUserNotFoundException(UserNotFoundException exception, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return exceptionHandlerResponse(exception, null, headers, status, request);
    }

    private ResponseEntity<UserResponse> handleUserExistException(UserExistException exception, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return exceptionHandlerResponse(exception, null, headers, status, request);
    }

    private ResponseEntity<UserResponse> exceptionHandlerResponse(Exception ex, Object o, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity<>(new UserResponse(status, ex.getMessage(), o), status);
    }

}
