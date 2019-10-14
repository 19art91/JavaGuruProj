package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.repository.ProductInMemoryRepository;
import com.javaguru.shoppinglist.service.validation.ProductValidationService;

public class ProductService {

    private ProductInMemoryRepository repository = new ProductInMemoryRepository();
    private ProductValidationService validationService = new ProductValidationService();


    public Long createProduct(Product product) {
        validationService.validate(product, repository);
        Product createdProduct = repository.insert(product);
        return createdProduct.getId();
    }

    public Product findProductById(Long id) {
        return repository.findProductById(id);
    }

}
