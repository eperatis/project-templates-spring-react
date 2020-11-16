package hu.uni.eku.camping.service.exceptions;

public class StartDateBeforeEndDateException extends Exception {
    public StartDateBeforeEndDateException() {
    }

    public StartDateBeforeEndDateException(String message) {
        super(message);
    }

    public StartDateBeforeEndDateException(String message, Throwable cause) {
        super(message, cause);
    }

    public StartDateBeforeEndDateException(Throwable cause) {
        super(cause);
    }

    public StartDateBeforeEndDateException(String message, Throwable cause, boolean enableSuppression,
                                           boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
