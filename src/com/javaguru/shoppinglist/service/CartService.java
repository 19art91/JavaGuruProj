package com.javaguru.shoppinglist.service;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.domain.ShoppingCart;
import com.javaguru.shoppinglist.repository.CartInMemoryRepository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class CartService {

    private CartInMemoryRepository repository = new CartInMemoryRepository();
//    private CartValidationService validationService = new CartValidationService();

    public ShoppingCart createCart(ShoppingCart cart) {
//        validationService.validate(product, repository);
        ShoppingCart createdCart = repository.insert(cart);
        return createdCart;
    }

    public void addProductToCart(ShoppingCart cart, Product product) {
        repository.update(cart.getName(), product);
    }

    public ShoppingCart findCartByName(String name){
        ShoppingCart cart = repository.read(name);
        return cart;
    }

    public void deleteCart(String name){
        repository.delete(name);
    }

    public BigDecimal calculateCartTotalPrice(String name){
        ShoppingCart cart = repository.read(name);
        BigDecimal total = new BigDecimal(0);

        for(Product p : cart.getProductList()){
            total = total.add(p.getPrice());
        }
        return total;
    }
}
