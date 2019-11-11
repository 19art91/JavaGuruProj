package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductNameValidationRule implements ProductValidationRule {

    public final static int MIN_NAME = 3;
    public final static int MAX_NAME = 32;
    private ProductRepository repository;

    @Autowired
    public ProductNameValidationRule(ProductRepository repository) {
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
}
