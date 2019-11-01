package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.ShoppingCart;
import com.javaguru.shoppinglist.repository.CartInMemoryRepository;

public class CartNameValidationRule implements CartValidationRule {

    @Override
    public void validate(ShoppingCart cart, CartInMemoryRepository repository) {
        checkNotNull(cart);
        if (cart.getName().equals("")) {
            throw new CartValidationException("Cart name must not be empty");
        }

        if (cart.getName() == null) {
            throw new CartValidationException("Cart name must not be null");
        }

        if (repository.readByName(cart.getName()).isPresent()) {
            throw new CartValidationException("Duplicate cart name");
        }
    }
}