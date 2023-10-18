package upc.edu.pe.softroute.feedbackservice.exceptions;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ErrorMessage {
    private final Integer status;
    private final String exception;
    private final String message;
    private final String path;

    public ErrorMessage(Exception exception, String path, Integer status) {
        this.exception = exception.getClass().getSimpleName();
        this.message = exception.getMessage();
        this.path = path;
        this.status = status;
    }
}
