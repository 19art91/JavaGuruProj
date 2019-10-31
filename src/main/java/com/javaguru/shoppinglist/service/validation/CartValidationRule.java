package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.ShoppingCart;
import com.javaguru.shoppinglist.repository.CartInMemoryRepository;

public interface CartValidationRule {

    void validate(ShoppingCart cart);

    default void checkNotNull(ShoppingCart cart){
        if(cart == null){
            throw new CartValidationException("Cart must not be null");
        }
    }
}
