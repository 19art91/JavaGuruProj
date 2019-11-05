package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.repository.ProductInMemoryRepository;
import com.javaguru.shoppinglist.service.validation.ProductNameValidationRule;
import com.javaguru.shoppinglist.service.validation.ProductValidationException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import javax.swing.text.html.Option;
import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ProductNameValidationRuleTest {

    @Mock
    ProductInMemoryRepository repository;

    @Spy
    @InjectMocks
    private ProductNameValidationRule victim;

    private Product product = product();

    @Test
    public void shouldThrowException() {
        product.setName(null);
        assertThatThrownBy(() -> victim.validate(product, repository))
                .isInstanceOf(ProductValidationException.class)
                .hasMessage("Product name must not be null");

        product.setName("");
        assertThatThrownBy(() -> victim.validate(product, repository))
                .isInstanceOf(ProductValidationException.class)
                .hasMessage("Product name must not be empty");

        product.setName("A");
        assertThatThrownBy(() -> victim.validate(product, repository))
                .isInstanceOf(ProductValidationException.class)
                .hasMessage("Product name must be in range " + ProductNameValidationRule.getMinName() + " - " + ProductNameValidationRule.getMaxName());

        product.setName("PROD_NAME");
        Optional<Product> prod = Optional.of(product);
        when(repository.findProductByName(product.getName())).thenReturn(prod);
        assertThatThrownBy(() -> victim.validate(product, repository))
                .isInstanceOf(ProductValidationException.class)
                .hasMessage("Duplicate product name");
    }

    @Test
    public void shouldValidate() {
        when(repository.findProductByName(product.getName())).thenReturn(Optional.empty());
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