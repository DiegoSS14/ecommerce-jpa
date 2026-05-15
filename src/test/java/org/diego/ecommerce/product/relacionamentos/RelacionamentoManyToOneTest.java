package org.diego.ecommerce.product.relacionamentos;

import org.diego.ecommerce.model.*;
import org.diego.ecommerce.product.EntityManagerTest;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RelacionamentoManyToOneTest extends EntityManagerTest {
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
        ordered.setCompletionDate(LocalDate.now());
        ordered.setInvoice(invoice);
        ordered.setClient(client);
        ordered.setStatus(StatusOrder.PAID);
        ordered.setTotal(BigDecimal.valueOf(224));
        ordered.setDeliveryAddressOrdered(deliveryAddressOrdered);

        invoice.setOrderId(ordered);

        em.getTransaction().begin();
        em.persist(invoice);
        em.persist(ordered);
        em.getTransaction().commit();

        em.clear();

        assertEquals(em.find(Ordered.class, ordered.getId()).getClient().getId(), client.getId());
    }

    @Test
    public void verificaRelacionamento2() {
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

        OrderedItem orderedItem = new OrderedItem();
        Product product = new Product();

        Ordered ordered = new Ordered();
        ordered.setOrderDate(LocalDate.now());
        ordered.setCompletionDate(LocalDate.now());
        ordered.setInvoice(invoice);
        ordered.setClient(client);
        ordered.setStatus(StatusOrder.PAID);
        ordered.setTotal(BigDecimal.valueOf(224));
        ordered.setDeliveryAddressOrdered(deliveryAddressOrdered);

        orderedItem.setProduct(product);
        orderedItem.setQuantity(4);
        orderedItem.setProductPrice(BigDecimal.valueOf(110));
        orderedItem.setOrdered(ordered);

        invoice.setOrderId(ordered);
        ordered.setOrderedItem(List.of(orderedItem));

        em.getTransaction().begin();
        em.persist(invoice);
        em.persist(product);
        em.persist(ordered);
        em.persist(orderedItem);
        em.getTransaction().commit();

        em.clear();

        boolean toHaveAnyThing = em.find(Client.class, client.getId())
                .getOrdereds()
                .stream()
                .flatMap(o -> o.getOrderedItem().stream())
                .anyMatch(item -> item.getId().equals(orderedItem.getId()));

        assertTrue(toHaveAnyThing);
    }
}
