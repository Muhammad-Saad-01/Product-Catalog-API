package net.muhammadsaad.rest.exception.brand;


import net.muhammadsaad.rest.exception.ResourceNotFoundException;

public class BrandNotFoundException extends ResourceNotFoundException {
    public BrandNotFoundException(String message) {
        super(message);
    }

    public BrandNotFoundException() {
        super("Brand not found");
    }
}