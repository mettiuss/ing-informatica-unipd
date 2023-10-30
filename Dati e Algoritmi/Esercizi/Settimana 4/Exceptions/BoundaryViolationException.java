package Exceptions;

public class BoundaryViolationException extends Exception {
    public BoundaryViolationException(String error) {
        super(error);
    }

    public BoundaryViolationException() {
        super();
    }
}
