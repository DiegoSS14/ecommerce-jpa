package org.diego.ecommerce.product.basicaMapping;

import org.diego.ecommerce.model.Category;
import org.diego.ecommerce.product.EntityManagerTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PrimaryKeyStrategyTest extends EntityManagerTest {
    @Test
    public void TestStrategyKey() {
        Category category = new Category();
//        category.setParentCategoryId(1);
        category.setName("Category test");

        em.getTransaction().begin();
        em.persist(category);
        em.getTransaction().commit();

        em.clear();

        Category verifyCategory = em.find(Category.class, category.getId());
        assertEquals(category, verifyCategory);
    }
}
