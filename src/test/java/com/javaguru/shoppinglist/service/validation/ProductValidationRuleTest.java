package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.service.validation.ProductValidationException;
import com.javaguru.shoppinglist.service.validation.ProductValidationRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.*;
import static org.mockito.Mockito.doThrow;

@RunWith(MockitoJUnitRunner.class)
public class ProductValidationRuleTest {

    @Spy
    private ProductValidationRule victim;

    @Test
    public void shouldThrowValidationException() {

        assertThatThrownBy(() -> victim.checkNotNull(null))
                .isInstanceOf(ProductValidationException.class);
    }

    @Test
    public void checkNotNull() {
        Product product = new Product();
        victim.checkNotNull(product);
    }
}