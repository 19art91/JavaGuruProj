package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.ShoppingCart;
import com.javaguru.shoppinglist.repository.CartInMemoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CartNameValidationRule implements CartValidationRule {
    private CartInMemoryRepository repository;

    @Autowired
    public CartNameValidationRule(CartInMemoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public void validate(ShoppingCart cart) {
        checkNotNull(cart);

        if (cart.getName() == null) {
            throw new CartValidationException("Cart name must not be null");
        }

        if(cart.getName().equals("")){
            throw new CartValidationException("Cart name must not be empty");
        }

        if (repository.findByName(cart.getName()).isPresent()) {
            throw new CartValidationException("Duplicate cart name");
        }
    }
}
