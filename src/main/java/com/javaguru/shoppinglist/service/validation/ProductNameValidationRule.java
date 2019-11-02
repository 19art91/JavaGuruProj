package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.repository.ProductInMemoryRepository;

public class ProductNameValidationRule implements ProductValidationRule {

    private final static int MIN_NAME = 3;
    private final static int MAX_NAME = 32;
    private ProductInMemoryRepository repository;

    public ProductNameValidationRule(ProductInMemoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public void validate(Product product) {
        checkNotNull(product);
        if (product.getName() == null) {
            throw new ProductValidationException("Product name must not be null");
        }

        if (product.getName().equals("")) {
            throw new ProductValidationException("Product name must not be empty");
        }

        if (product.getName().length() < MIN_NAME || product.getName().length() > MAX_NAME) {
            throw new ProductValidationException("Product name must be in range " + MIN_NAME + " - " + MAX_NAME);
        }

        if (repository.findProductByName(product.getName()).isPresent()) {
            throw new ProductValidationException("Duplicate product name");
        }
    }

    public static int getMinName() {
        return MIN_NAME;
    }

    public static int getMaxName() {
        return MAX_NAME;
    }
}
