package org.diego.ecommerce.product;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class ConsultingRecordsTest {

    private static EntityManagerFactory emf;
    private static EntityManager em;

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

    // Testes
    @Test
    public void testFindById() {
        Product product = em.find(Product.class, 1);
        // Product product = em.getReference(Product.class, 1);
        assertEquals("Kindle", product.getName());
    }

    @Test
    public void updateReference() {
        Product product = em.getReference(Product.class, 1);
        product.setName("Notebook Samsung");

        em.refresh(product);

        assertEquals("Kindle", product.getName());
        // assertEquals("Notebook Samsung", product.getName());
    }
}
