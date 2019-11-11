package com.javaguru.shoppinglist.repository;

import com.javaguru.shoppinglist.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Component
public class DefaultProductRepository implements ProductRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DefaultProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Product insert(Product product){

            KeyHolder keyHolder = new GeneratedKeyHolder();
            String SQL = "insert into products (name, description, category, price, discount) values" +
                    "(?, ?, ?, ?, ?)";

            PreparedStatementCreator createdStatement = new PreparedStatementCreator() {
                @Override
                public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                    PreparedStatement ps = connection.prepareStatement(SQL, PreparedStatement.RETURN_GENERATED_KEYS);
                    ps.setString(1, product.getName());
                    ps.setString(2, product.getDescription());
                    ps.setString(3, product.getCategory());
                    ps.setBigDecimal(4, product.getPrice());
                    ps.setBigDecimal(5, product.getDiscount());
                    return ps;
                }
            };

            jdbcTemplate.update(createdStatement, keyHolder);
            Number key = keyHolder.getKey();
            product.setId(key.longValue());

            return product;
    }

    public Optional<Product> findProductById(Long id){
        String SQL = "select * from products where id = " + id;
        List<Product>  product = jdbcTemplate.query(SQL, new BeanPropertyRowMapper<>(Product.class));

        return product.isEmpty() ? Optional.empty() : Optional.ofNullable(product.get(0));
    };

    public Optional<Product> findProductByName(String name){

        String SQL = "select * from products where name = " + "\"" + name + "\"";
        List<Product>  product = jdbcTemplate.query(SQL, new BeanPropertyRowMapper<>(Product.class));

        return product.isEmpty() ? Optional.empty() : Optional.ofNullable(product.get(0));
    };
}
