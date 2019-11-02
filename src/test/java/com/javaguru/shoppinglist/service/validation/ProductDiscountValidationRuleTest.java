package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.repository.ProductInMemoryRepository;
import com.javaguru.shoppinglist.service.validation.ProductDiscountValidationRule;
import com.javaguru.shoppinglist.service.validation.ProductValidationException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ProductDiscountValidationRuleTest {

    @Spy
    @InjectMocks
    private ProductDiscountValidationRule victim;

    private Product product = product();

    @Test
    public void shouldValidateWithException() {
        product.setDiscount(-50.0);

        assertThatThrownBy(() -> victim.validate(product))
                .isInstanceOf(ProductValidationException.class)
                .hasMessage("Incorrect number. Should be in range " + ProductDiscountValidationRule.getMinDiscount() + " - " +
                        ProductDiscountValidationRule.getMaxDiscount());

        verify(victim).checkNotNull(product);
    }

    private Product product() {
        Product product = new Product();
        product.setName("PROD_NAME");
        product.setDescription("PROD_DESCRIPTION");
        product.setDiscount(20);
        product.setId(2000L);
        product.setCategory("PROD_CATEGORY");
        product.setPrice(new BigDecimal(14));
        return product;
    }
}