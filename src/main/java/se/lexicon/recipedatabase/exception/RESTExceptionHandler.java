package se.lexicon.recipedatabase.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
public class RESTExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> illegalArgumentException(IllegalArgumentException ex){
        APIError apiError= new APIError(BAD_REQUEST.value(), BAD_REQUEST.name(),ex.getMessage());
        return ResponseEntity.status(BAD_REQUEST).body(apiError);
    }
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> resourceNotFoundException(ResourceNotFoundException ex){
        return ResponseEntity.status(400).body(new APIError(400, "BAD_REQUEST", ex.getMessage()));
    }
    @ExceptionHandler(ResourceDuplicateException.class)
    public ResponseEntity<APIError> resourceDuplicateException(ResourceDuplicateException ex){
        return ResponseEntity.status(400).body(new APIError(400, "BAD_REQUEST", ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> globalException(Exception ex){
        System.out.println("Global Exception -------------------");
        System.out.println((ex.getMessage()));
        ex.printStackTrace();
        System.out.println("------------------------------------");
        return ResponseEntity.status(500).body(new APIError(500, "INTERNAL_SERVER_ERROR"));
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request){
        List<Violation> violations= new ArrayList<>(); // 1 or more errors are bound in this list
        for(FieldError error: ex.getFieldErrors()){
            violations.add(new Violation(error.getField(), error.getDefaultMessage()));
        }

        APIErrorViolations apiErrorViolations= new APIErrorViolations(
                BAD_REQUEST.value(), BAD_REQUEST.name(),
                "One or several Field was not correctly validated",
                violations
        );
        return ResponseEntity.badRequest().body(apiErrorViolations);
    }


}
