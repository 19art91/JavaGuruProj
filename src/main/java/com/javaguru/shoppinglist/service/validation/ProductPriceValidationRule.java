package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.repository.ProductInMemoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ProductPriceValidationRule implements ProductValidationRule {

    private final static BigDecimal MIN_PRICE = new BigDecimal(0);
    private ProductInMemoryRepository repository;

    @Autowired
    public ProductPriceValidationRule(ProductInMemoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public void validate(Product product) {
        checkNotNull(product);
        if (product.getPrice().compareTo(MIN_PRICE) <= 0) {
            throw new ProductValidationException("Incorrect price. Should be greater than " + MIN_PRICE);
        }
    }

    public static BigDecimal getMinPrice() {
        return MIN_PRICE;
    }
}
