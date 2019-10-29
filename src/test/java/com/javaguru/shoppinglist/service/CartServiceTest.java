package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.domain.ShoppingCart;
import com.javaguru.shoppinglist.repository.CartInMemoryRepository;
import com.javaguru.shoppinglist.service.validation.CartValidationService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CartServiceTest {

    @Mock
    private CartInMemoryRepository repository;

    @Mock
    private CartValidationService validationService;

    @InjectMocks
    private CartService victim;

    @Captor
    private ArgumentCaptor<ShoppingCart> captor;

    private Product product = product();

    @Test
    public void shouldCreateCart() {
        ShoppingCart cart = shoppingCart();
        when(repository.insert(cart)).thenReturn(cart);

        victim.createCart(cart);

        verify(validationService).validate(captor.capture(), any(CartInMemoryRepository.class));
        ShoppingCart captorResult = captor.getValue();

        Assert.assertEquals(shoppingCart(), captorResult);

    }

    @Test
    public void addProductToCart() {
        ShoppingCart cart = shoppingCart();

        victim.addProductToCart(cart, product);
        victim.addProductToCart(cart, product);

        verify(repository, times(2)).update(cart.getName(), product);
    }

    @Test
    public void findCartByName() {
        ShoppingCart cart = shoppingCart();
        when(repository.read("TEST_CART")).thenReturn(cart);
        ShoppingCart result = victim.findCartByName("TEST_CART");

        Assert.assertEquals(shoppingCart(), result);

        when(repository.read("BAD_CART")).thenReturn(null);
        ShoppingCart badResult = victim.findCartByName("BAD_CART");

        Assert.assertEquals(badResult, null);
    }

    @Test
    public void deleteCart() {
        ShoppingCart cart = shoppingCart();
        victim.deleteCart("TEST_CART");

        verify(repository).delete(cart.getName());

    }

    @Test
    public void calculateCartTotalPrice() {
        ShoppingCart cart = shoppingCart();
        List<Product> productList= new ArrayList<>();
        productList.add(product());
        productList.add(product());
        productList.add(product());
        cart.setProductList(productList);
        when(repository.read(cart.getName())).thenReturn(cart);

        BigDecimal actualResult = victim.calculateCartTotalPrice("TEST_CART");
        BigDecimal expectedResult = new BigDecimal(14*3);
        Assert.assertEquals(expectedResult, actualResult);
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

    private ShoppingCart shoppingCart(){
        ShoppingCart cart = new ShoppingCart();
        List<Product> productList = new ArrayList<>();
        cart.setName("TEST_CART");
        cart.setProductList(productList);
        cart.addProductToList(product());
        return cart;
    }
}