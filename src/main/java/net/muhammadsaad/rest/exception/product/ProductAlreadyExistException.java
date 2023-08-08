package net.muhammadsaad.rest.exception.product;


import net.muhammadsaad.rest.exception.ResourceConflictException;

public class ProductAlreadyExistException extends ResourceConflictException {
    public ProductAlreadyExistException(String message) {
        super(message);
    }

    public ProductAlreadyExistException() {
        super("Product already exist!");
    }
}
