package org.diego.ecommerce.product.basicaMapping;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.diego.ecommerce.model.Client;
import org.diego.ecommerce.model.ClientSex;
import org.diego.ecommerce.product.EntityManagerTest;
import org.junit.jupiter.api.Test;

public class EnumeratedMappingTest extends EntityManagerTest {
    @Test
    public void enumeratedMappingTest(){
        Client client = new Client();
//        client.setId(4);
        client.setName("José Ferreira Bruto");
        client.setClientSex(ClientSex.MALE);

        em.getTransaction().begin();
        em.persist(client);
        em.getTransaction().commit();

        em.clear();

        Client foundClient = em.createQuery("SELECT c FROM Client c WHERE c.name = :clientName", Client.class)
                .setParameter("clientName", "José Ferreira Bruto").getSingleResult();

        assertEquals(foundClient.getName(), client.getName());
    }
}
