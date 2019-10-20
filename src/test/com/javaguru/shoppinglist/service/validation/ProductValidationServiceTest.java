package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.repository.ProductInMemoryRepository;
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

import static org.mockito.Mockito.verify;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

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
    @Captor
    private ArgumentCaptor<ProductInMemoryRepository> repositoryCaptor;

    @InjectMocks
    private ProductValidationService victim;

    private Product product = product();

    @Before
    public void setUp() {
        Set<ProductValidationRule> rules = new HashSet<>();
        rules.add(productNameValidationRule);
        rules.add(productDiscountValidationRule);
        rules.add(productPriceValidationRule);

        victim = new ProductValidationService();
        victim.setValidationRules(rules);
    }

    @Test
    public void shouldValidate(){
        victim.validate(product, productInMemoryRepository);

        verify(productNameValidationRule).validate(productCaptor.capture(), repositoryCaptor.capture());
        verify(productPriceValidationRule).validate(productCaptor.capture(), repositoryCaptor.capture());
        verify(productDiscountValidationRule).validate(productCaptor.capture(), repositoryCaptor.capture());

        List<Product> resultList = productCaptor.getAllValues();
        assertThat(resultList).containsOnly(product);
    }

    private Product product(){
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