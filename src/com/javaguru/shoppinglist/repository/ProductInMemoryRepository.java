package com.javaguru.shoppinglist.repository;

import com.javaguru.shoppinglist.domain.Product;
import javafx.concurrent.Task;

import java.util.HashMap;
import java.util.Map;

public class ProductInMemoryRepository {

    private Long productIdSequence = 0L;
    private Map<Long, Product> productRepository = new HashMap<>();

    public Product insert(Product product) {
        product.setId(productIdSequence++);
        productRepository.put(product.getId(), product);
        return product;
    }

    public Product findProductById(Long id) {
        return productRepository.get(id);
    }

    public Product findProductByName(String name) {
        for (Product p : productRepository.values()) {
            if (p.getName().equals(name)) {
                return p;
            }
        }
        return null;
    }
}
