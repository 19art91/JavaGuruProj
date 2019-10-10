package com.javaguru.shoppinglist;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class ShoppingListApplication {

    private static final int MIN_NAME = 3;
    private static final int MAX_NAME = 32;

    public static void main(String[] args) {
        Map<Long, Product> productRepository = new HashMap<>();
        Long productIdSequence = 0L;
        while (true) {
            Scanner scanner = new Scanner(System.in);
            try {
                System.out.println("1. Create product");
                System.out.println("2. Find product by id");
                System.out.println("3. Exit");
                Integer userInput = Integer.valueOf(scanner.nextLine());
                switch (userInput) {
                    case 1:
                        System.out.println("Enter product name: ");
                        String name = scanner.nextLine();
                        if (name.length() < MIN_NAME || name.length() > MAX_NAME) {
                            System.out.println("Incorrect name length. Should be in range " + MIN_NAME + " - " +
                                    MAX_NAME + ". Please try again");
                        }
                        System.out.println("Enter product description: ");
                        String description = scanner.nextLine();
                        System.out.println("Enter product category: ");
                        String category = scanner.nextLine();
                        System.out.println("Enter product price: ");
                        BigDecimal price = new BigDecimal(scanner.nextLine());
                        if (price.compareTo(new BigDecimal(0)) <= 0) {
                            System.out.println("Incorrect number. Should be greater than 0. Please try again");
                            break;
                        }
                        System.out.println("Enter product discount %: ");
                        int discount = scanner.nextInt();
                        if (discount < 0 || discount > 100) {
                            System.out.println("Incorrect number. Should be in range 0 - 100. Please try again");
                            break;
                        }
                        Product product = new Product();
                        product.setName(name);
                        product.setDescription(description);
                        product.setCategory(category);
                        product.setPrice(price);
                        product.setDiscount(discount);
                        product.setId(productIdSequence);
                        productRepository.put(productIdSequence, product);
                        productIdSequence++;
                        System.out.println("Product ID: " + product.getId());
                        break;
                    case 2:
                        System.out.println("Enter product id: ");
                        long id = scanner.nextLong();
                        Product findProductResult = productRepository.get(id);
                        System.out.println(findProductResult);
                        break;
                    case 3:
                        return;
                }
            } catch (Exception e) {
                System.out.println("Error! Please try again.");
            }
        }
    }
}
