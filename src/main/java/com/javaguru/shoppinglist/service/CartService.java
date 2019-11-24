package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.domain.ShoppingCart;
import com.javaguru.shoppinglist.repository.CartInMemoryRepository;
import com.javaguru.shoppinglist.repository.CartRepository;
import com.javaguru.shoppinglist.service.validation.CartValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.NoSuchElementException;

@Component
public class CartService {

    private CartRepository repository;
    private CartValidationService validationService;

    @Autowired
    public CartService(CartRepository repository, CartValidationService validationService) {
        this.repository = repository;
        this.validationService = validationService;
    }

    public ShoppingCart createCart(ShoppingCart cart) {
        validationService.validate(cart);
        ShoppingCart createdCart = repository.insert(cart);
        return createdCart;
    }

    public void addProductToCart(ShoppingCart cart, Product product) {
        repository.update(cart, product);
    }

    public ShoppingCart findCartById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NoSuchElementException("Cart not found, id: " + id));
    }

    public void deleteCart(Long id) {
        repository.delete(id);
    }

    //it is supposed that entered price is final and discount was already deducted. Need more requirements on this.
    public BigDecimal calculateCartTotalPrice(ShoppingCart cart) {
        BigDecimal total = new BigDecimal(0);

        for (Product p : cart.getProducts()) {
            total = total.add(p.getPrice());
        }
        return total;
    }
}
