package com.javaguru.shoppinglist;

import com.javaguru.shoppinglist.config.SpringJdbcConfig;
import com.javaguru.shoppinglist.console.ConsoleUI;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class ShoppingListApplication {

    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(SpringJdbcConfig.class);
        ConsoleUI console = context.getBean(ConsoleUI.class);
        console.execute();
    }
}
