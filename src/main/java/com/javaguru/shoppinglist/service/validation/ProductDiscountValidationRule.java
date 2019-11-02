package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.repository.ProductInMemoryRepository;
import org.springframework.stereotype.Component;

@Component
public class ProductDiscountValidationRule implements ProductValidationRule {
    private final static double MIN_DISCOUNT = 0;
    private final static double MAX_DISCOUNT = 100;

    @Override
    public void validate(Product product) {
        checkNotNull(product);
        if(product.getDiscount() < MIN_DISCOUNT || product.getDiscount() > MAX_DISCOUNT){
            throw new ProductValidationException("Incorrect number. Should be in range " + MIN_DISCOUNT + " - " +
                    MAX_DISCOUNT);
        }
    }

    public static double getMinDiscount() {
        return MIN_DISCOUNT;
    }

    public static double getMaxDiscount() {
        return MAX_DISCOUNT;
    }
}
