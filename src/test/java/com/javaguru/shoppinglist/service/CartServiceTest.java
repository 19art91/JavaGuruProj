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
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class CartServiceTest {

    private static final long BAD_ID = 111L;

    @Mock
    private CartInMemoryRepository repository;

    @Mock
    private CartValidationService validationService;

    @InjectMocks
    private CartService victim;

    @Captor
    private ArgumentCaptor<ShoppingCart> captor;

    @Captor ArgumentCaptor<Long> idCaptor;

    private Product product = product();

    @Test
    public void shouldCreateCart() {
        ShoppingCart cart = shoppingCart();
        when(repository.insert(cart)).thenReturn(cart);

        victim.createCart(cart);

        verify(validationService).validate(captor.capture(), any(CartInMemoryRepository.class));
        ShoppingCart captorResult = captor.getValue();

        Assert.assertEquals(captorResult, shoppingCart());
    }

    @Test
    public void addProductToCart() {
        ShoppingCart cart = shoppingCart();

        victim.addProductToCart(cart, product);
        victim.addProductToCart(cart, product);

        verify(repository, times(2)).update(cart.getId(), product);
    }

    @Test
    public void findCartById() {
        ShoppingCart cart = shoppingCart();
        when(repository.read(cart.getId())).thenReturn(Optional.of(cart));
        ShoppingCart result = victim.findCartById(cart.getId());

        Assert.assertEquals(cart, result);

        when(repository.read(BAD_ID)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> victim.findCartById(BAD_ID))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage("Cart not found, id: " + BAD_ID);
    }

    @Test
    public void deleteCart() {
        ShoppingCart cart = shoppingCart();
        victim.deleteCart(cart.getId());

        verify(repository).delete(idCaptor.capture());

        Long id = idCaptor.getValue();
        Assert.assertEquals(cart.getId(), id);

    }

    @Test
    public void calculateCartTotalPrice() {
        ShoppingCart cart = shoppingCart();
        List<Product> productList = new ArrayList<>();
        cart.addProductToList(product());
        cart.addProductToList(product());
        cart.addProductToList(product());

        BigDecimal actualResult = victim.calculateCartTotalPrice(cart);
        BigDecimal expectedResult = new BigDecimal(90);
        Assert.assertEquals(expectedResult, actualResult);
    }

    private Product product() {
        Product product = new Product();
        product.setName("PROD_NAME");
        product.setDescription("PROD_DESCRIPTION");
        product.setDiscount(20);
        product.setId(2000L);
        product.setCategory("PROD_CATEGORY");
        product.setPrice(new BigDecimal(30));
        return product;
    }

    private ShoppingCart shoppingCart() {
        ShoppingCart cart = new ShoppingCart();
        cart.setName("TEST_CART");
        cart.setId(50L);
        return cart;
    }
}