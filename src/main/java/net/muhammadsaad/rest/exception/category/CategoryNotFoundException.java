package net.muhammadsaad.rest.exception.category;


import net.muhammadsaad.rest.exception.ResourceNotFoundException;

public class CategoryNotFoundException extends ResourceNotFoundException {
    public CategoryNotFoundException(String message) {
        super(message);
    }

    public CategoryNotFoundException() {
        super("Category not found");
    }
}