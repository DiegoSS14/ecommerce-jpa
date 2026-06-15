package org.diego.ecommerce.product.entendendoEntityManager;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.diego.ecommerce.model.CardPayment;
import org.diego.ecommerce.model.Client;
import org.diego.ecommerce.model.Ordered;
import org.diego.ecommerce.model.OrderedItem;
import org.diego.ecommerce.model.PaymentStatus;
import org.diego.ecommerce.model.StatusOrder;
import org.diego.ecommerce.product.EntityManagerTest;
import org.junit.jupiter.api.Test;

public class CallbackTest extends EntityManagerTest {
    @Test
    void testeDeCallbacks() {
        CardPayment cardPayment = new CardPayment();
        List<OrderedItem> orderedItems = new ArrayList<>();
        orderedItems.add(new OrderedItem());
        Ordered ordered = new Ordered();
        ordered.setClient(em.find(Client.class, 1));
        ordered.setStatus(StatusOrder.WAITING);
        ordered.setPayment(cardPayment);
        ordered.setOrderedItems(orderedItems);

        cardPayment.setStatus(PaymentStatus.PROCESSING);
        cardPayment.setNumber("123456789");
        cardPayment.setOrdered(ordered);

        em.getTransaction().begin();
        em.persist(ordered);
        em.flush();
        ordered.setStatus(StatusOrder.PAID);
        em.getTransaction().commit();

        // Para disparar o @PostUpdate é necessário que a alteração seja feita em outra transação.
        em.getTransaction().begin();
        em.merge(ordered);
        em.getTransaction().commit();

        em.clear();

        Ordered findOrder = em.find(Ordered.class, ordered.getId());

        assertNotNull(findOrder.getCreationDate());
        assertNotNull(findOrder.getOrderDate());
        assertNotNull(findOrder.getLastUpdateDate());
    }
}
