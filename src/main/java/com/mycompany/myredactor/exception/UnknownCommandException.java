package com.mycompany.myredactor.exception;

/**
 * Exception is thrown when parser fails to recognize command
 */
public class UnknownCommandException extends RuntimeException {
    
    public UnknownCommandException() {
        super();
    }
    
    public UnknownCommandException(String message) {
        super(String.format("Command \"%s\" unknown", message));   
    }
}
