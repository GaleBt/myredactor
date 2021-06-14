package com.mycompany.myredactor.exception;

/**
 * Exception is thrown when an error happens during the work with the file
 */
public class RepositoryException extends RuntimeException {
    
    public RepositoryException() {
        super();
    }
    
    public RepositoryException(String message) {
        super(message);   
    }
    
    public RepositoryException(Throwable t) {
        super(t);   
    }
    
    public RepositoryException(String message, Throwable t) {
        super(message, t);   
    }
}
