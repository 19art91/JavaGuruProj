package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.domain.ShoppingCart;
import com.javaguru.shoppinglist.repository.CartInMemoryRepository;
import com.javaguru.shoppinglist.service.validation.CartValidationService;

import java.math.BigDecimal;
import java.util.NoSuchElementException;

public class CartService {

    private CartInMemoryRepository repository = new CartInMemoryRepository();
    private CartValidationService validationService = new CartValidationService();

    public ShoppingCart createCart(ShoppingCart cart) {
        validationService.validate(cart, repository);
        ShoppingCart createdCart = repository.insert(cart);
        return createdCart;
    }

    public void addProductToCart(ShoppingCart cart, Product product) {
        repository.update(cart.getId(), product);
    }

    public ShoppingCart findCartById(Long id) {
        return repository.read(id).orElseThrow(() -> new NoSuchElementException("Cart not found, id: " + id));
    }

    public void deleteCart(Long id) {
        repository.delete(id);
    }

    //it is supposed that entered price is final and discount was already deducted. Need more requirements on this.
    public BigDecimal calculateCartTotalPrice(ShoppingCart cart) {
        BigDecimal total = new BigDecimal(0);

        for (Product p : cart.getProductList()) {
            total = total.add(p.getPrice());
        }
        return total;
    }
}
