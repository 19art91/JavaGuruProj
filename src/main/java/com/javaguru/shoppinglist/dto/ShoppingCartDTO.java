package com.javaguru.shoppinglist.dto;

import com.javaguru.shoppinglist.domain.Product;

import java.util.LinkedList;
import java.util.List;

public class ShoppingCartDTO {
    private Long id;
    private String name;
    private List<ProductDTO> products = new LinkedList<>();

    public ShoppingCartDTO() {
    }

    public ShoppingCartDTO(Long id, String name, List<ProductDTO> products) {
        this.id = id;
        this.name = name;
        this.products = products;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ProductDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDTO> products) {
        this.products = products;
    }
}
