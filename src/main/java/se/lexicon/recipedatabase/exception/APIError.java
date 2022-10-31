package se.lexicon.recipedatabase.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class APIError {
    private Integer statusCode;
    private String statusText;
    private String message;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime timestamp;

    public APIError() {
        this.timestamp= LocalDateTime.now();
    }

    public APIError(Integer statusCode, String statusText, String message) {
        this();
        this.statusCode = statusCode;
        this.statusText = statusText;
        this.message = message;
    }

    public APIError(Integer statusCode, String statusText) {
        this();
        this.statusCode = statusCode;
        this.statusText = statusText;
    }
}
