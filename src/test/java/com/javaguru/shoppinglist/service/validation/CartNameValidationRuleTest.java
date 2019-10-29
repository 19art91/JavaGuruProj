package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.ShoppingCart;
import com.javaguru.shoppinglist.repository.CartInMemoryRepository;
import jdk.nashorn.internal.runtime.regexp.joni.Option;
import jdk.nashorn.internal.runtime.regexp.joni.encoding.ObjPtr;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CartNameValidationRuleTest {

    @Mock
    CartInMemoryRepository repository;

    @Spy
    @InjectMocks
    private CartNameValidationRule victim;


    private ShoppingCart cart = shoppingCart();

    @Test
    public void shouldThrowException() {
        cart.setName("");
        assertThatThrownBy(() -> victim.validate(cart, repository))
                .isInstanceOf(CartValidationException.class)
                .hasMessage("Cart name must not be empty");

        cart.setName("TEST_CART");
        Optional<ShoppingCart> result = Optional.of(cart);
        when(repository.read("TEST_CART")).thenReturn(result);

        assertThatThrownBy(() -> victim.validate(cart, repository))
                .isInstanceOf(CartValidationException.class)
                .hasMessage("Duplicate cart name");
    }

    private ShoppingCart shoppingCart(){
        ShoppingCart cart = new ShoppingCart();
        cart.setProductList(new ArrayList<>());
        cart.setName("TEST_CART");
        return cart;
    }
}