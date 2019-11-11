package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.repository.DefaultProductRepository;
import com.javaguru.shoppinglist.service.validation.ProductValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.NoSuchElementException;

@Component
public class ProductService {

    private final static BigDecimal MIN_PRICE = new BigDecimal(20);

    private DefaultProductRepository repository;
    private ProductValidationService validationService;

    @Autowired
    public ProductService(DefaultProductRepository repository, ProductValidationService validationService) {
        this.repository = repository;
        this.validationService = validationService;
    }

    public Long createProduct(Product product) {
        if (product.getPrice().compareTo(MIN_PRICE) < 0) {
            product.setDiscount(new BigDecimal(0));
        }
        validationService.validate(product);
        Product createdProduct = repository.insert(product);
        return createdProduct.getId();
    }

    public Product findProductById(Long id) {
        return repository.findProductById(id).orElseThrow(() -> new NoSuchElementException("Product not found, id: " + id));
    }

}
