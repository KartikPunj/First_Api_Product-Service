package com.scaler.firstapi.exceptions;

public class ProductNotExistsException extends Exception{
    public ProductNotExistsException(String message)     {
        super(message);
    }
}
