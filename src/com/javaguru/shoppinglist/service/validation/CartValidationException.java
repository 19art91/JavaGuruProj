package com.javaguru.shoppinglist.service.validation;

public class CartValidationException extends RuntimeException{

    public CartValidationException(String message) {
        super(message);
    }
}
