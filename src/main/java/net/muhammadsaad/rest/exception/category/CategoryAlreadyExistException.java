package net.muhammadsaad.rest.exception.category;


import net.muhammadsaad.rest.exception.ResourceConflictException;

public class CategoryAlreadyExistException extends ResourceConflictException {
    public CategoryAlreadyExistException(String message) {
        super(message);
    }

    public CategoryAlreadyExistException() {
        super("Category already exist!");
    }
}
