package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.repository.ProductInMemoryRepository;
import com.javaguru.shoppinglist.service.ProductService;
import com.javaguru.shoppinglist.service.validation.ProductValidationService;
import javafx.beans.property.ObjectProperty;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    private static final long BAD_ID = 10L;

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    @Mock
    private ProductInMemoryRepository repository;

    @Mock
    private ProductValidationService validationService;

    @InjectMocks
    private ProductService victim;

    @Captor
    private ArgumentCaptor<Product> productCaptor;

    @Test
    public void shouldCreateProduct() {
        Product product = expectedProduct();
        when(repository.insert(product)).thenReturn(product);

        victim.createProduct(product);

        verify(validationService).validate(productCaptor.capture(), any(ProductInMemoryRepository.class));
        Product captorResult = productCaptor.getValue();

        Assert.assertEquals(captorResult, product);
        Assert.assertEquals(captorResult.getId(), product.getId());
    }

    @Test
    public void shouldFindProductById() {
        when(repository.findProductById(2000L)).thenReturn(Optional.of(expectedProduct()));
        Product result = victim.findProductById(2000L);

        Assert.assertEquals(result, expectedProduct());

        when(repository.findProductById(BAD_ID)).thenReturn(Optional.empty());
        expectedException.expect(NoSuchElementException.class);
        expectedException.expectMessage("Product not found, id: " + BAD_ID);
        victim.findProductById(BAD_ID);
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

    private Product expectedProduct(){
        Product product = new Product();
        product.setName("PROD_NAME");
        product.setDescription("PROD_DESCRIPTION");
        product.setDiscount(0);
        product.setId(2000L);
        product.setCategory("PROD_CATEGORY");
        product.setPrice(new BigDecimal(14));
        return product;
    }

}