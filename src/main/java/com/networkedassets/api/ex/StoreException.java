package com.networkedassets.api.ex;

public class StoreException extends RuntimeException {
    public StoreException(String message) {
        super(message);
    }

    public StoreException(String message, Throwable t) {
        super(message, t);
    }
}
