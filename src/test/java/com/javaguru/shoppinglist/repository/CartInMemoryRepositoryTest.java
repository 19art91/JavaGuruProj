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
        assertThat(result).isEqualTo(cart());
    }

    @Test
    public void shouldUpdate() {
        ShoppingCart result = victim.insert(cart());

        victim.update(result.getName(), product());
        victim.update(result.getName(), product2());

        assertThat(result.getProductList()).isEqualTo(expectedCart().getProductList());
    }

    @Test
    public void read() {
    }

    @Test
    public void delete() {
    }

    private ShoppingCart cart(){
        ShoppingCart cart = new ShoppingCart();
        cart.setName("TEST_CART");
        return cart;
    }

    private ShoppingCart expectedCart(){
        ShoppingCart cart = new ShoppingCart();
        List<Product> prodList = new ArrayList<>();
        prodList.add(product());
        prodList.add(product2());
        cart.setProductList(prodList);
        cart.setName("TEST_CART");
        return cart;
    }

    private Product product(){
        Product product = new Product();
        product.setName("PROD_NAME");
        product.setDescription("PROD_DESCRIPTION");
        product.setDiscount(20);
        product.setId(2000L);
        product.setCategory("PROD_CATEGORY");
        product.setPrice(new BigDecimal(142));
        return product;
    }

    private Product product2(){
        Product product = new Product();
        product.setName("PROD_NAME_2");
        product.setDescription("PROD_DESCRIPTION");
        product.setDiscount(20);
        product.setId(2001L);
        product.setCategory("PROD_CATEGORY");
        product.setPrice(new BigDecimal(142));
        return product;
    }
}