package com.javaguru.shoppinglist.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "cart")
public class ShoppingCart {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
//    private List<Product> productList = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

//    public List<Product> getProductList() {
//        return productList;
//    }

    public void setName(String name) {
        this.name = name;
    }

//    public void addProductToList(Product product) {
//        this.productList.add(product);
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShoppingCart that = (ShoppingCart) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name);
//                Objects.equals(productList, that.productList);
    }

//    @Override
//    public int hashCode() {
//        return Objects.hash(id, name, productList);
//    }
//
//    @Override
//    public String toString() {
//        return "ShoppingCart{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", productList=" + productList +
//                '}';
//    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", productList=" +
                '}';
    }

}
