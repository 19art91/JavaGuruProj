package com.javaguru.shoppinglist.controller;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.domain.ShoppingCart;
import com.javaguru.shoppinglist.dto.ProductDTO;
import com.javaguru.shoppinglist.dto.ShoppingCartDTO;
import com.javaguru.shoppinglist.service.CartService;
import com.javaguru.shoppinglist.service.ProductService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/shoppingcart")
public class ShoppingCartController {

    private final CartService cartService;
    private final ProductService productService;

    public ShoppingCartController(CartService cartService, ProductService productService) {
        this.cartService = cartService;
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ShoppingCart> createCart(@RequestBody ShoppingCartDTO shoppingCartDTO) {
        ShoppingCart cart = new ShoppingCart();
        cart.setName(shoppingCartDTO.getName());
        cartService.createCart(cart);

        return ResponseEntity.ok(cart);
    }

    @GetMapping("/{id}")
    public ShoppingCartDTO findProductById(@PathVariable("id") Long id) {
        ShoppingCart cart = cartService.findCartById(id);
        List<ProductDTO> productList = new LinkedList<>();
        for (Product p : cart.getProducts()) {
            ProductDTO product = new ProductDTO();
            product.setId(p.getId());
            product.setName(p.getName());
            product.setPrice(p.getPrice());
            product.setCategory(p.getCategory());
            product.setDiscount(p.getDiscount());
            product.setDescription(p.getDescription());
            productList.add(product);
        }

        return new ShoppingCartDTO(cart.getId(), cart.getName(), productList);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCart(@PathVariable("id") Long id) {
        cartService.findCartById(id);
        cartService.deleteCart(id);

        return ResponseEntity.ok("Shopping Cart with id = " + id + " is deleted");
    }

    @PutMapping("/{cart_id}/product/{prod_id}")
    public ResponseEntity<String> addProductToCart(@PathVariable("cart_id") Long cart_id, @PathVariable("prod_id") Long prod_id) {
        ShoppingCart cart = cartService.findCartById(cart_id);
        Product product = productService.findProductById(prod_id);
        cartService.addProductToCart(cart, product);

        return ResponseEntity.ok(product.getName() + " product is added to the " + cart.getName() + " cart");

    }


    @GetMapping("/{id}/totalprice")
    public ResponseEntity<String> calculateCartTotalPrice(@PathVariable("id") Long id) {
        ShoppingCart cart = cartService.findCartById(id);
        BigDecimal total = cartService.calculateCartTotalPrice(cart);

        return ResponseEntity.ok("Cart " + "\"" + cart.getName() + "\"" + " Total price = " + total + "$");
    }
}
