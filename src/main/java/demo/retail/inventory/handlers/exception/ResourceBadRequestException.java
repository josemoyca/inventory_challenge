package demo.retail.inventory.handlers.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ResourceBadRequestException extends RuntimeException {
    private static final long serialVersionUID = -4012754020476533151L;

    public ResourceBadRequestException() {
        super();
    }

    public ResourceBadRequestException(String message) {
        super(message);
    }

    public ResourceBadRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResourceBadRequestException(Throwable cause) {
        super(cause);
    }

    public static String buildExceptionMessage(String name) {
        final String messageTemplate = "Parameter can't be null %s";

        return String.format(messageTemplate, name);
    }
}