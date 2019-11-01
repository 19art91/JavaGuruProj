package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.ShoppingCart;
import com.javaguru.shoppinglist.repository.CartInMemoryRepository;

import java.util.HashSet;
import java.util.Set;

public class CartValidationService {
    private Set<CartValidationRule> validationRules = new HashSet<>();

    public CartValidationService() {
        validationRules.add(new CartNameValidationRule());
    }

    public void validate(ShoppingCart cart, CartInMemoryRepository repository) {
        validationRules.forEach(p -> p.validate(cart, repository));
    }
}
