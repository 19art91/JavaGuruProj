package com.javaguru.shoppinglist.service.validation;

import com.javaguru.shoppinglist.domain.ShoppingCart;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class CartValidationRuleTest {

    @Spy
    private CartValidationRule victim;

    @Test
    public void shouldThrowValidationException() {
        assertThatThrownBy(() -> victim.checkNotNull(null))
                .isInstanceOf(CartValidationException.class)
                .hasMessage("Cart must not be null");
    }

    @Test
    public void checkNotNull() {
        ShoppingCart cart = new ShoppingCart();
        victim.checkNotNull(cart);
    }
}