package org.diego.ecommerce.product.entendendoEntityManager;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.diego.ecommerce.model.Ordered;
import org.diego.ecommerce.product.EntityManagerTest;
import org.junit.jupiter.api.Test;

public class RollbackTransactionTest extends EntityManagerTest {

    @Test
    void abrirFecharECancelarTransacao() {
        assertThrows(IllegalArgumentException.class, () -> {
            try {
                metodoNegocio();
            } catch (Exception e) {
                em.getTransaction().rollback();
                throw e;
            }
        });
    }

    private void metodoNegocio() {
        Ordered ordered = new Ordered();
        ordered.setPayment(null);

        if (ordered.getPayment() == null) {
            throw new IllegalArgumentException();
        }
    }
}
