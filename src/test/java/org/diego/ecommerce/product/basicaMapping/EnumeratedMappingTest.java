package org.diego.ecommerce.product.basicaMapping;

import org.diego.ecommerce.model.Client;
import org.diego.ecommerce.model.ClientSex;
import org.diego.ecommerce.product.EntityManagerTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EnumeratedMappingTest extends EntityManagerTest {
    @Test
    public void enumeratedMappingTest(){
        Client client = new Client();
        client.setId(4);
        client.setName("José Ferreira Bruto");
        client.setClientSex(ClientSex.MALE);

        em.getTransaction().begin();
        em.persist(client);
        em.getTransaction().commit();

        em.clear();

        assertNotNull(em.find(Client.class, 4));
    }
}
