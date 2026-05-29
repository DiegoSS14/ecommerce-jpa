package org.diego.ecommerce.product;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EntityManagerTest {

    protected static EntityManagerFactory emf;
    protected static EntityManager em;

    // Métodos padrões para iniciar corretamente o Entity Manager Factory e Entity Manager
    @BeforeAll
    public static void setUpBeforeClass() {
        emf = Persistence
                .createEntityManagerFactory("Ecommerce-PU");
    }

    @AfterAll
    public static void tearDownAfterClass() {
        emf.close();
    }

    @BeforeEach
    public void setUp() {
        em = emf.createEntityManager();
    }

    @AfterEach
    public void tearDown() {
        em.close();
    }
}
