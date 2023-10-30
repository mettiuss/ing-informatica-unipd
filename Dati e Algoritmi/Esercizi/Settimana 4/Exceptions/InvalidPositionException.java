package Exceptions;

public class InvalidPositionException extends Exception {
    public InvalidPositionException(String error) {
        super(error);
    }

    public InvalidPositionException() {
        super();
    }
}
