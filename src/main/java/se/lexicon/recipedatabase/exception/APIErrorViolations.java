package se.lexicon.recipedatabase.exception;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class APIErrorViolations extends APIError{
   private List<Violation> violations;

    public APIErrorViolations(Integer statusCode, String statusText, String message, List<Violation> violations) {
        super(statusCode, statusText, message);
        this.violations = violations; //
    }
}
