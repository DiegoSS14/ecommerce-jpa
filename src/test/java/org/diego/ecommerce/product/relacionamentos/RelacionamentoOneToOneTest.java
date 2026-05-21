package org.diego.ecommerce.product.relacionamentos;

import org.diego.ecommerce.model.*;
import org.diego.ecommerce.product.EntityManagerTest;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class RelacionamentoOneToOneTest extends EntityManagerTest {
    @Test
    public void verificaRelacionamento() {
        Ordered ordered = new Ordered();
        ordered.setClient(em.find(Client.class, 1));
        ordered.setStatus(StatusOrder.WAITING);

        CardPayment cardPayment = new CardPayment();
        em.persist(cardPayment);
        em.persist(ordered);
        cardPayment.setId(1);
        cardPayment.setStatus(PaymentStatus.PROCESSING);
        cardPayment.setNumber("123456789");

        em.getTransaction().begin();
        em.persist(ordered);
        ordered.setPayment(cardPayment);
        em.getTransaction().commit();

        em.clear();

        assertNull(em.find(CardPayment.class, cardPayment.getId()).getOrdered());
    }

    @Test
    public void verificaRelacionamento2() {

        Invoice invoice = new Invoice();
        invoice.setXml("XML");
        invoice.setIssueDate(new Date());

        Ordered ordered = new Ordered();
        ordered.setClient(em.find(Client.class, 1));
        ordered.setStatus(StatusOrder.WAITING);
        ordered.setInvoice(invoice);

        em.getTransaction().begin();
        em.persist(invoice);
        em.persist(ordered);
        em.getTransaction().commit();

        em.clear();

        assertNotNull(em.find(Ordered.class, ordered.getId()).getInvoice());
    }
}
