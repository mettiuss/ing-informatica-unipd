package Exceptions;

public class EmptyListException extends Exception {
    public EmptyListException(String error) {
        super(error);
    }

    public EmptyListException() {
        super();
    }
}
