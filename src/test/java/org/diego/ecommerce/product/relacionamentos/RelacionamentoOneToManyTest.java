package org.diego.ecommerce.product.relacionamentos;

import org.diego.ecommerce.model.*;
import org.diego.ecommerce.product.EntityManagerTest;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class RelacionamentoOneToManyTest extends EntityManagerTest {
    @Test
    public void verificaRelacionamento() {
        Client client = em.find(Client.class, 20);

        DeliveryAddressOrdered deliveryAddressOrdered = new DeliveryAddressOrdered();
        deliveryAddressOrdered.setDistrict("DF");
        deliveryAddressOrdered.setCity("Brasilia");
        deliveryAddressOrdered.setComplement("Próximo ao atacadão ultrabox");
        deliveryAddressOrdered.setPublicPlace("Rua 50 H conjunto 50");
        deliveryAddressOrdered.setNumber("50");

        Invoice invoice = new Invoice();
        invoice.setXml("");
        invoice.setIssueDate(new Date());

        Ordered ordered = new Ordered();
        ordered.setOrderDate(LocalDate.now());
        ordered.setCreationDate(LocalDate.now());
        ordered.setInvoice(invoice);
        ordered.setClient(client);
        ordered.setStatus(StatusOrder.PAID);
        ordered.setTotal(BigDecimal.valueOf(224));
        ordered.setDeliveryAddressOrdered(deliveryAddressOrdered);

        invoice.setOrdered(ordered);

        em.getTransaction().begin();
        em.persist(invoice);
        em.persist(ordered);
        em.getTransaction().commit();

        em.clear();

        assertNotNull(em.find(Ordered.class, ordered.getId()));
        assertFalse(em.find(Client.class, client.getId()).getOrdereds().isEmpty());
    }
}
