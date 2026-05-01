package org.diego.ecommerce.product.startbuy;

import org.diego.ecommerce.product.EntityManagerTest;
import org.diego.ecommerce.model.Product;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConsultingRecordsTest extends EntityManagerTest {
    @Test
    public void testFindById() {
        Product product = em.find(Product.class, 1);
        // Product product = em.getReference(Product.class, 1);
        assertEquals("Kindle", product.getName());
    }

    @Test
    public void updateReference() {
        Product product = em.getReference(Product.class, 1);
        product.setName("Notebook Samsung");

        em.refresh(product);

        assertEquals("Kindle", product.getName());
        // assertEquals("Notebook Samsung", product.getName());
    }
}
