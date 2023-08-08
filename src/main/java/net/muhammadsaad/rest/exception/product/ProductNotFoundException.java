package net.muhammadsaad.rest.exception.product;


import net.muhammadsaad.rest.exception.ResourceNotFoundException;

public class ProductNotFoundException extends ResourceNotFoundException {
    public ProductNotFoundException(String message) {
        super(message);
    }

    public ProductNotFoundException() {
        super("Product not found");
    }
}