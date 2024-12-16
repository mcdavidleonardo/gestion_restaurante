package services;

public class ServiceJdbcException extends RuntimeException{

    public ServiceJdbcException(String message) {
        super(message);
    }

    public ServiceJdbcException(String mensaje, Throwable cause) {
        super(cause);
    }
}
