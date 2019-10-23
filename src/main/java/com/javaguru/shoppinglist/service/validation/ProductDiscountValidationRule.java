package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.repository.ProductInMemoryRepository;

import java.math.BigDecimal;

public class ProductDiscountValidationRule implements ProductValidationRule {
    private final static double MIN_DISCOUNT = 0;
    private final static double MAX_DISCOUNT = 100;
    private final static BigDecimal MIN_PRICE = new BigDecimal(20);


    @Override
    public void validate(Product product, ProductInMemoryRepository repository) {
        checkNotNull(product);
        if(product.getDiscount() < MIN_DISCOUNT || product.getDiscount() > MAX_DISCOUNT){
            throw new ProductValidationException("Incorrect number. Should be in range " + MIN_DISCOUNT + " - " +
                    MAX_DISCOUNT);
        } else if (product.getPrice().compareTo(MIN_PRICE) < 0){
            product.setDiscount(0);
        }
    }

    public static double getMinDiscount() {
        return MIN_DISCOUNT;
    }

    public static double getMaxDiscount() {
        return MAX_DISCOUNT;
    }

    public static BigDecimal getMinPrice() {
        return MIN_PRICE;
    }
}
