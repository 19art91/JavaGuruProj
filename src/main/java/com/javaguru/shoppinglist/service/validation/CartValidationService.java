package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.ShoppingCart;
import com.javaguru.shoppinglist.repository.CartInMemoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class CartValidationService {
    private Set<CartValidationRule> validationRules;

    @Autowired
    public CartValidationService(Set<CartValidationRule> validationRules) {
        this.validationRules = validationRules;
    }

    public void validate(ShoppingCart cart){
        validationRules.forEach(c -> c.validate(cart));
    }
}
