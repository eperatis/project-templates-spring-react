package hu.uni.eku.camping.service.exceptions;

public class StartDateBeforeTodayException extends Exception {
    public StartDateBeforeTodayException() {
    }

    public StartDateBeforeTodayException(String message) {
        super(message);
    }

    public StartDateBeforeTodayException(String message, Throwable cause) {
        super(message, cause);
    }

    public StartDateBeforeTodayException(Throwable cause) {
        super(cause);
    }

    public StartDateBeforeTodayException(String message, Throwable cause, boolean enableSuppression,
                                         boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
