package hu.uni.eku.camping.service.exceptions;

public class CampingSlotAlreadyReservedException extends Exception {
    public CampingSlotAlreadyReservedException() {
    }

    public CampingSlotAlreadyReservedException(String message) {
        super(message);
    }

    public CampingSlotAlreadyReservedException(String message, Throwable cause) {
        super(message, cause);
    }

    public CampingSlotAlreadyReservedException(Throwable cause) {
        super(cause);
    }

    public CampingSlotAlreadyReservedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
