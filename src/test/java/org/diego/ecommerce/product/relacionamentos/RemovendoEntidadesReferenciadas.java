package org.diego.ecommerce.product.relacionamentos;

import org.diego.ecommerce.model.Client;
import org.diego.ecommerce.model.ClientSex;
import org.diego.ecommerce.model.Ordered;
import org.diego.ecommerce.model.OrderedItem;
import org.diego.ecommerce.product.EntityManagerTest;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNull;

public class RemovendoEntidadesReferenciadas extends EntityManagerTest {

    @Test
    public void removeEntidadesReferenciadas() {

        Client client = new Client();
        client.setName("Filipe Silva");
        client.setClientSex(ClientSex.MALE);

        OrderedItem orderedItem = new OrderedItem();
        Ordered ordered = new Ordered();
        ordered.setOrderedItems(List.of(orderedItem));
        ordered.setClient(client);

        em.getTransaction().begin();
        // Setei a propriedade cascade de Ordered para CascadeType.ALL, assim o client é persistido automaticamente
        // em.persist(client);
        em.persist(orderedItem);
        em.persist(ordered);
        em.getTransaction().commit();

        em.getTransaction().begin();
        ordered.getOrderedItems().stream().forEach(item -> em.remove(item));
        em.remove(ordered);
        em.getTransaction().commit();

        em.clear();

        assertNull(em.find(Ordered.class, ordered.getId()));
    }
}
