package hust.soict.hedspi.aims.exception;

public class PlayerException extends Exception {

    // Default constructor
    public PlayerException() {
        super();
    }

    // Constructor with an error message
    public PlayerException(String message) {
        super(message);
    }

    // Constructor with an error message and a root cause
    public PlayerException(String message, Throwable cause) {
        super(message, cause);
    }

    // Constructor with a root cause
    public PlayerException(Throwable cause) {
        super(cause);
    }
}
