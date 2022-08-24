package by.krutikov.exception;

public class UserRepositoryException extends RuntimeException { //todo extends exception

    public UserRepositoryException() {
        super();
    }

    public UserRepositoryException(String message) {
        super(message);
    }

    public UserRepositoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserRepositoryException(Throwable cause) {
        super(cause);
    }

    protected UserRepositoryException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
