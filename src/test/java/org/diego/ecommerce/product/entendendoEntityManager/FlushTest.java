package org.diego.ecommerce.product.entendendoEntityManager;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.diego.ecommerce.model.Ordered;
import org.diego.ecommerce.product.EntityManagerTest;
import org.junit.jupiter.api.Test;

public class FlushTest extends EntityManagerTest {
    
    @Test
    void executeFlush() {
        assertThrows(Exception.class, ()->{
            Ordered order = new Ordered();
            em.getTransaction().begin();
            em.persist(order);
            em.flush();

            assertNull(order.getPayment());

            if (order.getStatus() == null) {
                em.getTransaction().rollback();
                throw new IllegalArgumentException("Invalid payment");
            }
        });
    }
}
