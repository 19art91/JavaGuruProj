package com.javaguru.shoppinglist.repository;

import com.javaguru.shoppinglist.domain.Product;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Component
@Profile("dev2")
public class HibernateProductRepository implements ProductRepository {

    @Autowired
    private SessionFactory sessionFactory;

    public Product insert(Product product) {
        sessionFactory.getCurrentSession().save(product);
        return product;
    }

    public Optional<Product> findProductById(Long id) {
        Product product = sessionFactory.getCurrentSession().get(Product.class, id);
        return Optional.ofNullable(product);
    }

    public Optional<Product> findProductByName(String name) {
        Product product = (Product) sessionFactory.getCurrentSession().createQuery("from Product where name = :name")
                .setParameter("name", name)
                .setMaxResults(1)
                .uniqueResult();
        return Optional.ofNullable(product);
    }

    ;

}
