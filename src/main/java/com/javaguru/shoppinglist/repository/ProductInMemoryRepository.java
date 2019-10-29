package com.javaguru.shoppinglist.repository;

import com.javaguru.shoppinglist.domain.Product;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ProductInMemoryRepository {

    private Long productIdSequence = 0L;
    private Map<Long, Product> productRepository = new HashMap<>();

    public Product insert(Product product) {
        product.setId(productIdSequence++);
        productRepository.put(product.getId(), product);
        return product;
    }

    public Optional<Product> findProductById(Long id) {
        return Optional.ofNullable(productRepository.get(id));
    }

    public Optional<Product> findProductByName(String name) {
        Product product = null;
        for (Product p : productRepository.values()) {
            if (p.getName().equals(name)) {
                product = p;
            }
        }
        return Optional.ofNullable(product);
    }
}
