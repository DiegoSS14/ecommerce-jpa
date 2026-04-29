package org.diego.ecommerce.product.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.diego.ecommerce.product.Product;

public class StartPersistenceUnit {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Ecommerce-PU");
        EntityManager em = emf.createEntityManager();

        Product product = em.find(Product.class, 1);
        System.out.println(product);

        em.close();
        emf.close();
    }
}
