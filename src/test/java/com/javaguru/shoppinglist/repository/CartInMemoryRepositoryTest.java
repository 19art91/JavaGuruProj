package com.javaguru.shoppinglist.repository;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.domain.ShoppingCart;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class CartInMemoryRepositoryTest {

    private CartInMemoryRepository victim = new CartInMemoryRepository();
    ShoppingCart cart = cart();

    @Test
    public void shouldInsert() {
        ShoppingCart result = victim.insert(cart);
        assertThat(result).isEqualTo(expectedCart());
    }

//    @Test
//    public void shouldUpdate() {
//        cart.setId(0L);
//        ShoppingCart result = victim.insert(cart());
//
//        victim.update(cart.getId(), product());
//        victim.update(cart.getId(), product2());
//
//        assertThat(result.getProductList()).isEqualTo(expectedCart2().getProductList());
//    }

    @Test
    public void read() {
    }

    @Test
    public void delete() {
    }

    private ShoppingCart cart() {
        ShoppingCart cart = new ShoppingCart();
        cart.setName("TEST_CART");
        return cart;
    }

    private ShoppingCart expectedCart() {
        ShoppingCart cart = new ShoppingCart();
        cart.setName("TEST_CART");
        cart.setId(0L);
        return cart;
    }

//    private ShoppingCart expectedCart2() {
//        ShoppingCart cart = new ShoppingCart();
//        cart.addProductToList(product());
//        cart.addProductToList(product2());
//        cart.setName("TEST_CART");
//        return cart;
//    }

    private Product product() {
        Product product = new Product();
        product.setName("PROD_NAME");
        product.setDescription("PROD_DESCRIPTION");
        product.setDiscount(new BigDecimal(20));
        product.setId(2000L);
        product.setCategory("PROD_CATEGORY");
        product.setPrice(new BigDecimal(142));
        return product;
    }

    private Product product2() {
        Product product = new Product();
        product.setName("PROD_NAME_2");
        product.setDescription("PROD_DESCRIPTION");
        product.setDiscount(new BigDecimal(20));
        product.setId(2001L);
        product.setCategory("PROD_CATEGORY");
        product.setPrice(new BigDecimal(142));
        return product;
    }
}