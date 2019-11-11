package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.repository.ProductInMemoryRepository;
import com.javaguru.shoppinglist.service.validation.*;
import com.sun.jmx.mbeanserver.Repository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ProductValidationServiceTest {

    @Mock
    private ProductNameValidationRule productNameValidationRule;
    @Mock
    private ProductPriceValidationRule productPriceValidationRule;
    @Mock
    private ProductDiscountValidationRule productDiscountValidationRule;
    @Mock
    private ProductInMemoryRepository productInMemoryRepository;

    @Captor
    private ArgumentCaptor<Product> productCaptor;

    @InjectMocks
    private ProductValidationService victim;

    private Product product = product();

    @Before
    public void setUp() {
        Set<ProductValidationRule> rules = new HashSet<>();
        rules.add(productNameValidationRule);
        rules.add(productDiscountValidationRule);
        rules.add(productPriceValidationRule);

        victim = new ProductValidationService(rules);
    }

    @Test
    public void shouldValidate() {
        victim.validate(product);
        victim.validate(product);

        verify(productNameValidationRule, times(2)).validate(productCaptor.capture());
        verify(productPriceValidationRule, atMost(2)).validate(productCaptor.capture());
        verify(productDiscountValidationRule, atLeast(2)).validate(productCaptor.capture());

        List<Product> resultList = productCaptor.getAllValues();
        assertThat(resultList).containsOnly(product);
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