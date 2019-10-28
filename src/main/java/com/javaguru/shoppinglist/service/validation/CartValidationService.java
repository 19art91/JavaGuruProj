package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.domain.ShoppingCart;
import com.javaguru.shoppinglist.repository.CartInMemoryRepository;
import com.javaguru.shoppinglist.repository.ProductInMemoryRepository;

import java.util.HashSet;
import java.util.Set;

public class CartValidationService {
    private Set<CartValidationRule> validationRules;

    public CartValidationService(Set<CartValidationRule> validationRules) {
        this.validationRules = validationRules;
    }

    public void validate(ShoppingCart cart, CartInMemoryRepository repository){
        validationRules.forEach(p -> p.validate(cart, repository));
    }
}
