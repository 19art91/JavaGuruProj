package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.domain.ShoppingCart;
import com.javaguru.shoppinglist.repository.CartInMemoryRepository;
import com.sun.org.glassfish.external.statistics.impl.AverageRangeStatisticImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class CartValidationServiceTest {

    @Mock
    private CartNameValidationRule cartNameValidationRule;

    @Mock
    private CartInMemoryRepository repository;

    @Captor
    ArgumentCaptor<ShoppingCart> captor;

    @InjectMocks
    CartValidationService victim;

    private ShoppingCart cart = shoppingCart();

    @Before
    public void setUp() {
        Set<CartValidationRule> rules = new HashSet<>();
        rules.add(cartNameValidationRule);

        victim = new CartValidationService(rules);
    }

    @Test
    public void shouldValidate() {
        victim.validate(cart, repository);

        verify(cartNameValidationRule).validate(captor.capture(), any(CartInMemoryRepository.class));

        Assert.assertEquals(captor.getValue(), shoppingCart());
    }

    private ShoppingCart shoppingCart(){
        ShoppingCart cart = new ShoppingCart();
        cart.setProductList(new ArrayList<>());
        cart.setName("TEST_CART");
        return cart;
    }
}