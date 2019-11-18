package com.javaguru.shoppinglist.repository;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.domain.ShoppingCart;

import java.util.Optional;

public interface CartRepository {

    public ShoppingCart insert(ShoppingCart cart);

//    public void update(Long id, Product product);

    public Optional<ShoppingCart> findById(Long id);

    public Optional<ShoppingCart> findByName(String name);

    public void delete(Long id);
}
