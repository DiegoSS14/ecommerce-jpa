package org.diego.ecommerce.product.basicaMapping;

import org.diego.ecommerce.model.Client;
import org.diego.ecommerce.model.ClientSex;
import org.diego.ecommerce.model.DeliveryAddressOrdered;
import org.diego.ecommerce.model.Ordered;
import org.diego.ecommerce.model.StatusOrder;
import org.diego.ecommerce.product.EntityManagerTest;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class BuiltInObjectMappingTest extends EntityManagerTest {
    @Test
    public void analyzeInlineObject() {
        DeliveryAddressOrdered deliveryAddressOrdered = new DeliveryAddressOrdered();
        deliveryAddressOrdered.setCity("Brasília");
        deliveryAddressOrdered.setDistrict("Ceilandia");
        deliveryAddressOrdered.setNumber("123");
        deliveryAddressOrdered.setPublicPlace("Q 51 Conjunto M");
        deliveryAddressOrdered.setComplement("Próximo ao lago");
        deliveryAddressOrdered.setDistrict("000000000-00");

        Client client = new Client();
        client.setName("Diego");
        client.setClientSex(ClientSex.MALE);

        Ordered order = new Ordered();
//        order.setId(1);
        order.setOrderDate(LocalDate.now());
        order.setCreationDate(LocalDate.of(2026, 5, 20));
        order.setTotal(BigDecimal.valueOf(500));
        order.setClient(client);
//        order.setInvoiceId(2);
        order.setStatus(StatusOrder.WAITING);
        order.setDeliveryAddressOrdered(deliveryAddressOrdered);

        em.getTransaction().begin();
        em.persist(client);
        em.persist(order);
        em.getTransaction().commit();

        em.clear();

        assertNotNull(em.find(Ordered.class, order.getId()));
    }
}
