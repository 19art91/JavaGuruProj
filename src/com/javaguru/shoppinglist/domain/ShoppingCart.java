package com.javaguru.shoppinglist.domain;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

    private String name;
    private List<Product> productList = new ArrayList<>();

    public String getName() {
        return name;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addProductToList(Product product) {
        this.productList.add(product);
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "name='" + name + '\'' +
                ", productList=" + productList +
                '}';
    }
}
