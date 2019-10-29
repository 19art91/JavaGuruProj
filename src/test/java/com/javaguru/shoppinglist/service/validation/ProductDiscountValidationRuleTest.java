package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.repository.ProductInMemoryRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ProductDiscountValidationRuleTest {

    @Mock
    ProductInMemoryRepository repository;

    @Spy
    @InjectMocks
    private ProductDiscountValidationRule victim;

    private Product product = product();

    @Test
    public void shouldValidateWithException() {
        boolean thrown = false;
        product.setDiscount(-50.0);

        try{
            victim.validate(product, repository);
        } catch (ProductValidationException e){
            if(e.getMessage().equals("Incorrect number. Should be in range " + ProductDiscountValidationRule.getMinDiscount() + " - " +
                    ProductDiscountValidationRule.getMaxDiscount())){
                thrown = true;
            }
        }
        assertTrue(thrown);

    }

    @Test
    public void shouldValidate(){
        product = product();
        product.setPrice(ProductDiscountValidationRule.getMinPrice().subtract(new BigDecimal(1)));
        victim.validate(product, repository);

        assertEquals(0, Double.compare(product.getDiscount(), 0));

        product.setPrice(new BigDecimal(25));
        victim.validate(product, repository);

        assertEquals(new BigDecimal(25), product.getPrice());

        verify(victim, times(2)).checkNotNull(product);
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