package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.repository.ProductInMemoryRepository;
import com.javaguru.shoppinglist.service.validation.ProductValidationService;

import java.math.BigDecimal;
import java.util.NoSuchElementException;

public class ProductService {

    private final static BigDecimal MIN_PRICE = new BigDecimal(20);

    private ProductInMemoryRepository repository = new ProductInMemoryRepository();
    private ProductValidationService validationService = new ProductValidationService();


    public Long createProduct(Product product) {
        if (product.getPrice().compareTo(MIN_PRICE) < 0) {
            product.setDiscount(new BigDecimal(0));
        }
        validationService.validate(product, repository);
        Product createdProduct = repository.insert(product);
        return createdProduct.getId();
    }

    public Product findProductById(Long id) {
        return repository.findProductById(id).orElseThrow(() -> new NoSuchElementException("Product not found, id: " + id));
    }

}
