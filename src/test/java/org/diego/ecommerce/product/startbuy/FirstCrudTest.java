package org.diego.ecommerce.product.startbuy;

import org.diego.ecommerce.model.Client;
import org.diego.ecommerce.model.ClientSex;
import org.diego.ecommerce.product.EntityManagerTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class FirstCrudTest extends EntityManagerTest {

    @Test
    public void create(){
        Client product = new Client(3, "Fábio Júnior", ClientSex.FAMALE);

        em.getTransaction().begin();
        em.persist(product);
        em.flush();
        em.getTransaction().commit();

        assertEquals(product.getName(), em.find(Client.class,3).getName());
    }

    @Test
    public void findById() {
        Assertions.assertNotNull(em.find(Client.class,1));
    }

    @Test
    public void findAll() {
        var clients =  em.createNativeQuery(
                "select * from Client",
                Client.class).getResultList();

        Assertions.assertNotNull(clients);
        System.out.println(clients);
    }

    @Test
    public void update() {
        String name = "Manuel Jaca da Selva";

        Client client = em.find(Client.class, 1);

        em.getTransaction().begin();
        client.setName(name);
        em.getTransaction().commit();

        em.clear();

        assertEquals(name, em.find(Client.class, 1).getName());
    }

    @Test
    public void delete() {
        Client client = em.find(Client.class, 1);

        em.getTransaction().begin();
        em.remove(client);
        em.getTransaction().commit();

        em.clear();

        assertNull(em.find(Client.class,1));
    }
}
