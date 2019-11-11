package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.repository.ProductInMemoryRepository;

import java.math.BigDecimal;

public class ProductPriceValidationRule implements ProductValidationRule {

    public final static BigDecimal MIN_PRICE = new BigDecimal(0);

    @Override
    public void validate(Product product) {
        checkNotNull(product);
        if (product.getPrice().compareTo(MIN_PRICE) <= 0) {
            throw new ProductValidationException("Incorrect price. Should be greater than " + MIN_PRICE);
        }
    }
}
