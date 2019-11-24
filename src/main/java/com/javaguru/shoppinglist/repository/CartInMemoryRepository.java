package com.javaguru.shoppinglist.repository;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.domain.ShoppingCart;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@Profile("test")
public class CartInMemoryRepository implements CartRepository {
    private Long cartIdSequence = 0L;
    private Map<Long, ShoppingCart> cartRepository = new HashMap<>();

    public ShoppingCart insert(ShoppingCart cart) {
        cart.setId(cartIdSequence++);
        cartRepository.put(cart.getId(), cart);
        return cart;
    }

    public void update(ShoppingCart cart, Product product) {
        cartRepository.get(cart.getId()).getProducts().add(product);
    }

    public Optional<ShoppingCart> findById(Long id) {
        return Optional.ofNullable(cartRepository.get(id));
    }

    public Optional<ShoppingCart> findByName(String name) {
        ShoppingCart cart = null;
        for (ShoppingCart s : cartRepository.values()) {
            if (s.getName().equals(name)) {
                cart = s;
            }
        }
        return Optional.ofNullable(cart);
    }

    public void delete(Long id) {
        cartRepository.remove(id);
    }

}
