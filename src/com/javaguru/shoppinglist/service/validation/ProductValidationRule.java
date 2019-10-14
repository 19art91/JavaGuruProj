package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.repository.ProductInMemoryRepository;

public interface ProductValidationRule {

    void validate(Product product);

    default void checkNotNull(Product product){
        if(product == null){
            throw new ProductValidationException("Product must not be null");
        }
    }
}