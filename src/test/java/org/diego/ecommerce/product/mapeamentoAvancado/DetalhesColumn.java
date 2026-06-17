package org.diego.ecommerce.product.mapeamentoAvancado;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDate;
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


public class DetalhesColumn extends EntityManagerTest{
    @Test
    void impedirInsercaoDaColunaAtualizacao() {
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
        ordered.setLastUpdateDate(LocalDate.now());

        em.getTransaction().begin();
        em.persist(ordered);
        em.getTransaction().commit();

        em.clear();

        Ordered ordered2 = em.find(Ordered.class, ordered.getId());
        assertNotNull(ordered2.getCreationDate());
        assertNull(ordered2.getLastUpdateDate());
        System.out.println(ordered2.getLastUpdateDate());
    }

        @Test
    void impedirInsercaoDaColunaCriacao() {
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
        ordered.setCreationDate(LocalDate.of(2026, 06, 16));

        em.getTransaction().begin();
        em.persist(ordered);
        em.getTransaction().commit();

        LocalDate newDate = LocalDate.of(2026, 05, 16);

        em.getTransaction().begin();
        ordered.setCreationDate(newDate);
        em.merge(ordered);
        em.getTransaction().commit();

        em.clear();

        Ordered ordered2 = em.find(Ordered.class, ordered.getId());
        assertNotEquals(ordered2.getCreationDate(), newDate);
    }
}
