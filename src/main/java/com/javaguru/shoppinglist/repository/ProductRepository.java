package com.javaguru.shoppinglist.repository;

import com.javaguru.shoppinglist.domain.Product;

import java.util.Optional;

public interface ProductRepository {

    public Product insert(Product product);

    public Optional<Product> findProductById(Long id);

    public Optional<Product> findProductByName(String name);

}
