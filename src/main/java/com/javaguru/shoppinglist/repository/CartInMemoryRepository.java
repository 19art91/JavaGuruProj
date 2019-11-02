package com.javaguru.shoppinglist.repository;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.domain.ShoppingCart;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class CartInMemoryRepository {
    private Long cartIdSequence = 0L;
    private Map<Long, ShoppingCart> cartRepository = new HashMap<>();

    public ShoppingCart insert(ShoppingCart cart) {
        cart.setId(cartIdSequence++);
        cartRepository.put(cart.getId(), cart);
        return cart;
    }

    public void update(Long id, Product product) {
        cartRepository.get(id).addProductToList(product);
    }

    public Optional<ShoppingCart> read(Long id) {
        return Optional.ofNullable(cartRepository.get(id));
    }

    public Optional<ShoppingCart> readByName(String name) {
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
