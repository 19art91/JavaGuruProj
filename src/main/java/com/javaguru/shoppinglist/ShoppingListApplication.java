package com.javaguru.shoppinglist;

import com.javaguru.shoppinglist.config.DBConfig;
import com.javaguru.shoppinglist.console.ConsoleUI;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class ShoppingListApplication {

    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(DBConfig.class);
        ConsoleUI console = context.getBean(ConsoleUI.class);
        console.execute();
    }
}
