package com.link.itemcatalog.item.exception;

/**
 * Exception thrown when a business conflict occurs.
 */
public class ConflictException extends RuntimeException {

    public ConflictException(String message) {
        super(message);
    }
}
