package com.javaguru.shoppinglist.repository;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.domain.ShoppingCart;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;

import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Component
@Profile("dev2")
public class HibernateCartRepository implements CartRepository {

    private final SessionFactory sessionFactory;

    @Autowired
    public HibernateCartRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public ShoppingCart insert(ShoppingCart cart) {
        sessionFactory.getCurrentSession().save(cart);
        return cart;
    }

    @Transactional
    public Optional<ShoppingCart> findByName(String name) {
        ShoppingCart cart = (ShoppingCart) sessionFactory.getCurrentSession().createQuery("from ShoppingCart where name = :name")
                .setParameter("name", name)
                .setMaxResults(1)
                .uniqueResult();
        return Optional.ofNullable(cart);
    }

    @Transactional
    public void update(ShoppingCart cart, Product product) {
        List<Product> productList;
        productList = cart.getProducts();
        productList.add(product);
        sessionFactory.getCurrentSession().update(product);
        sessionFactory.getCurrentSession().update(cart);
    }

    @Transactional
    public Optional<ShoppingCart> findById(Long id) {
        ShoppingCart cart = (ShoppingCart) sessionFactory.getCurrentSession().createQuery("from ShoppingCart where id = :id")
                .setParameter("id", id)
                .setMaxResults(1)
                .uniqueResult();
        return Optional.ofNullable(cart);
    }

    @Transactional
    public void delete(Long id) {
        System.out.println("id before delete = " + id);
        Query cart = sessionFactory.getCurrentSession().createQuery("delete ShoppingCart where id = :id")
                .setParameter("id", id);
        cart.executeUpdate();
    }

}
