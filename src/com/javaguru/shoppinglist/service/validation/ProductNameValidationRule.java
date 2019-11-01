package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.repository.ProductInMemoryRepository;

public class ProductNameValidationRule implements ProductValidationRule {

    private final static int MIN_NAME = 3;
    private final static int MAX_NAME = 32;

    @Override
    public void validate(Product product, ProductInMemoryRepository repository) {
        checkNotNull(product);
        if (product.getName() == null) {
            throw new ProductValidationException("Product name must not be null");
        }

        if (product.getName().length() < MIN_NAME || product.getName().length() > MAX_NAME) {
            throw new ProductValidationException("Product name must be in range " + MIN_NAME + " - " + MAX_NAME);
        }

        if (repository.findProductByName(product.getName()).isPresent()) {
            throw new ProductValidationException("Duplicate product name");
        }
    }
}
