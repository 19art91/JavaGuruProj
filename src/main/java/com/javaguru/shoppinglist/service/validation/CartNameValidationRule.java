package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.ShoppingCart;
import com.javaguru.shoppinglist.repository.CartInMemoryRepository;

public class CartNameValidationRule implements CartValidationRule {

    @Override
    public void validate(ShoppingCart cart, CartInMemoryRepository repository) {
        checkNotNull(cart);
        if(cart.getName().equals("")){
            throw new ProductValidationException("Cart name must not be empty");
        } else if (repository.read(cart.getName()) != null){
            throw new ProductValidationException("Duplicate cart name");
        }
    }
}
