package org.diego.ecommerce.product.relacionamentos;

import org.diego.ecommerce.model.Category;
import org.diego.ecommerce.model.Product;
import org.diego.ecommerce.product.EntityManagerTest;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class RelacionamentoManyToManyTest extends EntityManagerTest {
    @Test
    public void verificaRelacionamento() {
        Category category = em.find(Category.class, 1);
        Product product = em.find(Product.class, 1);

        em.getTransaction().begin();
        product.setCategorys(Arrays.asList(category));
        em.getTransaction().commit();

        em.clear();

        Category categoryVerification = em.find(Category.class, category.getId());
        assertFalse(categoryVerification.getProducts().isEmpty());
    }
}
