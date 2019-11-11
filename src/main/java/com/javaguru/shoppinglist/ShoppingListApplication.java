package com.javaguru.shoppinglist;

import com.javaguru.shoppinglist.console.ConsoleUI;
import com.javaguru.shoppinglist.repository.CartInMemoryRepository;
import com.javaguru.shoppinglist.repository.ProductInMemoryRepository;
import com.javaguru.shoppinglist.service.CartService;
import com.javaguru.shoppinglist.service.ProductService;
import com.javaguru.shoppinglist.service.validation.*;

import java.util.HashSet;
import java.util.Set;

class ShoppingListApplication {

    public static void main(String[] args) {

        ProductInMemoryRepository prodRepository = new ProductInMemoryRepository();
        CartInMemoryRepository cartRepository = new CartInMemoryRepository();

        Set<ProductValidationRule> prodRules= new HashSet<>();
        prodRules.add(new ProductDiscountValidationRule());
        prodRules.add(new ProductPriceValidationRule());
        prodRules.add(new ProductNameValidationRule(prodRepository));

        Set<CartValidationRule> cartRules = new HashSet<>();
        cartRules.add(new CartNameValidationRule(cartRepository));

        ProductValidationService productValidationService = new ProductValidationService(prodRules);
        ProductService productService = new ProductService(prodRepository, productValidationService);

        CartValidationService cartValidationService = new CartValidationService(cartRules);
        CartService cartService = new CartService(cartRepository, cartValidationService);
        ConsoleUI consoleUI = new ConsoleUI(productService, cartService);
        consoleUI.execute();
    }
}
