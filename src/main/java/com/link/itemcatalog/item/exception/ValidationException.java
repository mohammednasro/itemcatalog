package com.link.itemcatalog.item.exception;

/**
 * Exception thrown when a validation error occurs.
 */
public class ValidationException extends RuntimeException {

    public ValidationException(String message) {
        super(message);
    }
}
