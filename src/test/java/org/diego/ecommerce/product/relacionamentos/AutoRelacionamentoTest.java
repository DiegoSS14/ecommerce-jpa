package org.diego.ecommerce.product.relacionamentos;

import org.diego.ecommerce.model.Category;
import org.diego.ecommerce.product.EntityManagerTest;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AutoRelacionamentoTest extends EntityManagerTest {
    @Test
    public void relacionamentoTest(){
        Category category = new Category();
        category.setName("Padaria");

        Category subcategory = new Category();
        category.setName("Padaria de bairro");

        subcategory.setParentCategory(category);

        em.getTransaction().begin();
        em.persist(subcategory);
        em.persist(category);
        em.getTransaction().commit();

        em.clear();

        Category findCategory = em.find(Category.class, category.getId());
        assertNotNull(findCategory.getCategories());

        List<Category> findSubcategories = em.find(Category.class, category.getId()).getCategories();
        assertFalse(findSubcategories.isEmpty());
    }
}
