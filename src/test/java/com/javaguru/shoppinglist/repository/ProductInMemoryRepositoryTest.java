package com.javaguru.shoppinglist.repository;

import com.javaguru.shoppinglist.domain.Product;
import com.javaguru.shoppinglist.repository.ProductInMemoryRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class ProductInMemoryRepositoryTest {

    private static final String PROD_NAME = "PROD_NAME";
    private static final String BAD_NAME = "BAD_NAME";
    private static final String PROD_DESCRIPTION = "TASK_DESCRIPTION";
    private static final long PROD_ID = 0L;
    private static final long BAD_ID = 111L;
    private ProductInMemoryRepository victim = new ProductInMemoryRepository();
    Product product = product();

    @Test
    public void shouldInsert() {
        Product result = victim.insert(product);
        assertThat(result).isEqualTo(expectedProd());
    }

    @Test
    public void shouldFindProductById() {
        victim.insert(product);

        Optional<Product> result = victim.findProductById(PROD_ID);
        assertThat(result).isNotEmpty();
        assertThat(result).hasValue(expectedProd());

        result = victim.findProductById(BAD_ID);
        assertThat(result).isEmpty();
    }

    @Test
    public void shouldFindProductByName() {
        victim.insert(product);

        Optional<Product> result = victim.findProductByName(PROD_NAME);
        assertThat(result).isNotEmpty();
        assertThat(result).hasValue(expectedProd());

        result = victim.findProductByName(BAD_NAME);
        assertThat(result).isEmpty();
    }

    private Product expectedProd() {
        Product product = new Product();
        product.setName(PROD_NAME);
        product.setDescription(PROD_DESCRIPTION);
        product.setId(PROD_ID);
        return product;
    }

    private Product product() {
        Product product = new Product();
        product.setName(PROD_NAME);
        product.setDescription(PROD_DESCRIPTION);
        return product;
    }
}