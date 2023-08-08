package net.muhammadsaad.rest.exception.brand;


import net.muhammadsaad.rest.exception.ResourceConflictException;

public class BrandAlreadyExistException extends ResourceConflictException {
    public BrandAlreadyExistException(String message) {
        super(message);
    }

    public BrandAlreadyExistException() {
        super("Brand already exist!");
    }
}
