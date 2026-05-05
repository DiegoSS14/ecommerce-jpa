package org.diego.ecommerce.product.startbuy;

import org.diego.ecommerce.model.Client;
import org.diego.ecommerce.model.ClientSex;
import org.diego.ecommerce.product.EntityManagerTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class FirstCrudTest extends EntityManagerTest {

    @Test
    public void create(){
        Client client = new Client();
        client.setName("Fábio Júnior");
        client.setClientSex(ClientSex.MALE);

        em.getTransaction().begin();
        em.persist(client);
        em.flush();
        em.getTransaction().commit();

        assertEquals(client.getName(), em.find(Client.class,3).getName());
    }

    @Test
    public void findById() {
        assertNotNull(em.find(Client.class,1));
    }

//    @Test
//    public void findAll() {
    // Desse jeito não funciona, o Java não consegue associar o objeto da tabela ao objeto java por diferença nos nomes de propriedades
//        var clients =  em.createNativeQuery("""
//                        SELECT
//                        id,
//                        name,
//                        client_sex
//                        FROM Client
//                        """,
////                        "select c from c",
//                Client.class).getResultList();
//
//        assertNotNull(clients);
//    }

@Test
public void findAll() {
    // Desse jeito funciona porque ao invés de usar native query, usa-se JPQL que interpreta o dado e converte para objeto.
    var clients = em.createQuery("SELECT c FROM Client c", Client.class)
            .getResultList();
    assertNotNull(clients);
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
