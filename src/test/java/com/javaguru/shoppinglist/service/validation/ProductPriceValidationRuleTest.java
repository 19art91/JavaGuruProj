package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.repository.ProductInMemoryRepository;
import com.javaguru.shoppinglist.service.validation.ProductPriceValidationRule;
import com.javaguru.shoppinglist.service.validation.ProductValidationException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

import javax.swing.*;
import java.math.BigDecimal;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class ProductPriceValidationRuleTest {

    @Mock
    private ProductInMemoryRepository repository;

    @Spy
    @InjectMocks
    private ProductPriceValidationRule victim;

    private Product product = product();

    @Test
    public void shouldThrowException() {
        product.setPrice(new BigDecimal(-2));

        assertThatThrownBy(() -> victim.validate(product, repository))
                .isInstanceOf(ProductValidationException.class)
                .hasMessage("Incorrect price. Should be greater than " + ProductPriceValidationRule.getMinPrice());
    }

    @Test
    public void shouldValidate() {
        product = product();
        victim.validate(product, repository);

        verify(victim).checkNotNull(product);
    }

    private Product product() {
        Product product = new Product();
        product.setName("PROD_NAME");
        product.setDescription("PROD_DESCRIPTION");
        product.setDiscount(new BigDecimal(20));
        product.setId(2000L);
        product.setCategory("PROD_CATEGORY");
        product.setPrice(new BigDecimal(14));
        return product;
    }
}