package com.javaguru.shoppinglist.repository;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.domain.ShoppingCart;

import java.util.*;

public class CartInMemoryRepository {
    private Map<String, ShoppingCart> cartRepository = new HashMap<>();

    public ShoppingCart insert(ShoppingCart cart) {
        cartRepository.put(cart.getName(), cart);
        return cart;
    }

    public void update(String name, Product product) {
        cartRepository.get(name).addProductToList(product);
    }

    public Optional<ShoppingCart> read(String name){
        return Optional.ofNullable(cartRepository.get(name));
    }

    public void delete(String name){
        cartRepository.remove(name);
    }
}
