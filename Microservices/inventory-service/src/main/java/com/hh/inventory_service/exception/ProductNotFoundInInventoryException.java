package com.hh.inventory_service.exception;

public class ProductNotFoundInInventoryException extends RuntimeException{
    public ProductNotFoundInInventoryException(String message) {
        super(message);
    }

    public ProductNotFoundInInventoryException(String message, Throwable cause) {
        super(message, cause);
    }
}
