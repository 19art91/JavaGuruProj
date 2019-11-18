package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.repository.ProductInMemoryRepository;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ProductDiscountValidationRule implements ProductValidationRule {
    public final static BigDecimal MIN_DISCOUNT = new BigDecimal(0);
    public final static BigDecimal MAX_DISCOUNT = new BigDecimal(100);

    @Override
    public void validate(Product product) {
        checkNotNull(product);
        if (product.getDiscount().compareTo(MIN_DISCOUNT) < 0 || product.getDiscount().compareTo(MAX_DISCOUNT) > 0) {
            throw new ProductValidationException("Incorrect number. Should be in range " + MIN_DISCOUNT + " - " +
                    MAX_DISCOUNT);
        }
    }
}
