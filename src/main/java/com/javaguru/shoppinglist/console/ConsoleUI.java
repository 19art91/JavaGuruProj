package com.javaguru.shoppinglist.console;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.domain.ShoppingCart;
import com.javaguru.shoppinglist.service.CartService;
import com.javaguru.shoppinglist.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Scanner;

@Component
public class ConsoleUI {

    private ProductService productService;
    private CartService cartService;

    @Autowired
    public ConsoleUI(ProductService productService, CartService cartService) {
        this.productService = productService;
        this.cartService = cartService;
    }

    public void execute() {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            try {
                System.out.println("\n1. Create product");
                System.out.println("2. Find product by id");
                System.out.println("3. Shopping cart menu");
                System.out.println("4. Exit");
                Integer userInput = Integer.valueOf(scanner.nextLine());
                switch (userInput) {
                    case 1:
                        createProduct();
                        break;
                    case 2:
                        System.out.println(findProduct());
                        break;
                    case 3:
                        boolean productMenu = false;
                        while (true) {
                            System.out.println("\n1. Create new cart");
                            System.out.println("2. Add product to cart");
                            System.out.println("3. Find and print cart");
                            System.out.println("4. Delete cart");
                            System.out.println("5. Calculate total price in cart");
                            System.out.println("6. Back to product menu");
                            System.out.println("7. Exit");
                            userInput = Integer.valueOf(scanner.nextLine());
                            switch (userInput) {
                                case 1:
                                    createCart();
                                    break;
                                case 2:
                                    addProductToCart();
                                    break;
                                case 3:
                                    printCart(findCart());
                                    break;
                                case 4:
                                    deleteCart();
                                    break;
                                case 5:
                                    calculateCartTotalPrice();
                                    break;
                                case 6:
                                    productMenu = true;
                                    break;
                                case 7:
                                    return;
                            }
                            if (productMenu) break;
                        }
                        break;
                    case 4:
                        return;
                }
            } catch (Exception e) {
                System.out.println("Error! Please try again.");
            }
        }

    }

    private void createProduct() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter product name: ");
        String name = scanner.nextLine();
        System.out.println("Enter product description: ");
        String description = scanner.nextLine();
        System.out.println("Enter product category: ");
        String category = scanner.nextLine();
        System.out.println("Enter product price: ");
        BigDecimal price = new BigDecimal(scanner.nextLine());
        System.out.println("Enter product discount %: ");
        BigDecimal discount = scanner.nextBigDecimal();

        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setCategory(category);
        product.setPrice(price);
        product.setDiscount(discount);

        Long id = productService.createProduct(product);
        System.out.println("Product ID: " + id);
    }

    private Product findProduct() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter product id: ");
        Long id = scanner.nextLong();
        Product product = productService.findProductById(id);
        return product;
    }

    private void createCart() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter cart name");
        String name = scanner.nextLine();

        ShoppingCart cart = new ShoppingCart();
        cart.setName(name);

        ShoppingCart createdCart = cartService.createCart(cart);
        System.out.println(createdCart.getName() + " is created, id = " + createdCart.getId());
    }

    private void addProductToCart() {
        ShoppingCart cart = findCart();
        Product product = findProduct();
        cartService.addProductToCart(cart, product);
        System.out.println(product.getName() + " is added to the " + cart.getName());
    }

    private ShoppingCart findCart() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter shopping cart id: ");
        Long id = scanner.nextLong();

        return cartService.findCartById(id);
    }

    private void printCart(ShoppingCart cart) {
        int i = 1;
        for (Product p : cart.getProductList()) {
            System.out.println(i++ + ". " + p);
        }
    }

    private void deleteCart() {
        ShoppingCart cart = findCart();
        cartService.deleteCart(cart.getId());
        System.out.println(cart.getName() + " is deleted, id = " + cart.getId());
    }

    private void calculateCartTotalPrice() {
        ShoppingCart cart = findCart();
        BigDecimal total = cartService.calculateCartTotalPrice(cart);

        System.out.println("Cart " + "\"" + cart.getName() + "\"" + " Total price = " + total + "$");
    }

}
