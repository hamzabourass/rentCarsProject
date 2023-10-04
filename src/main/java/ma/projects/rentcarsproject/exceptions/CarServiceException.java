package ma.projects.rentcarsproject.exceptions;

public class CarServiceException extends RuntimeException {
    public CarServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}

