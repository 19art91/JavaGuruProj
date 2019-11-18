package com.javaguru.shoppinglist.repository;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.domain.ShoppingCart;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Optional;

@Component
@Profile("dev2")
@Transactional
public class HibernateCartRepository implements CartRepository{

    @Autowired
    private SessionFactory sessionFactory;

    public ShoppingCart insert(ShoppingCart cart){
        sessionFactory.getCurrentSession().save(cart);
        return cart;
    }

    public Optional<ShoppingCart> findByName(String name){
        ShoppingCart cart = (ShoppingCart) sessionFactory.getCurrentSession().createQuery("from ShoppingCart where name = :name")
                .setParameter("name", name)
                .setMaxResults(1)
                .uniqueResult();
        return Optional.ofNullable(cart);
    }

    public Optional<ShoppingCart> findById(Long id){
        ShoppingCart cart = (ShoppingCart) sessionFactory.getCurrentSession().createQuery("from ShoppingCart where id = :id")
                .setParameter("id", id)
                .setMaxResults(1)
                .uniqueResult();
        return Optional.ofNullable(cart);
    }

    public void delete(Long id){
        Query cart = sessionFactory.getCurrentSession().createQuery("delete ShoppingCart where id = :id")
                .setParameter("id", id);
    }

}
