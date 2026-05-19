package org.diego.ecommerce.product.relacionamentos;

import org.diego.ecommerce.model.Ordered;
import org.diego.ecommerce.model.OrderedItem;
import org.diego.ecommerce.product.EntityManagerTest;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNull;

public class RemovendoEntidadesReferenciadas extends EntityManagerTest {

    @Test
    public void removeEntidadesReferenciadas() {
        OrderedItem orderedItem = new OrderedItem();
        Ordered ordered = new Ordered();
        ordered.setOrderedItem(List.of(orderedItem));

        em.getTransaction().begin();
        em.persist(orderedItem);
        em.persist(ordered);
        em.getTransaction().commit();

        em.getTransaction().begin();
        ordered.getOrderedItem().stream().forEach(item -> em.remove(item));
        em.remove(ordered);
        em.getTransaction().commit();

        em.clear();

        assertNull(em.find(Ordered.class, ordered.getId()));
    }
}
