package org.diego.ecommerce.product.startbuy;

import org.diego.ecommerce.product.EntityManagerTest;
import org.diego.ecommerce.product.Product;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class OperationsWithTransactionsTest extends EntityManagerTest {

    @Test
    public void preventOperationInDataBase() {
        Product product = em.find(Product.class, 1);
        em.detach(product); // Impede que os dados abaixo sejam persistidos no banco

        em.getTransaction().begin();
        product.setName("Livro Domain Driven Design");
        product.setDescription("O melhor livro para você dar um upgrade na sua carreira");
        em.getTransaction().commit();

        em.clear();

        Product productFinded = em.find(Product.class, 1);
        assertEquals(productFinded.getName(), "Kindle");
    }

    @Test
    public void insertWithMerge() {
        Product product = new Product();

        product.setId(4);
        product.setName("Mesa digitalizadora Wacom");
        product.setPrice(BigDecimal.valueOf(260));
        product.setDescription("Mesa digitalizadora profissional");

        em.getTransaction().begin();
        em.merge(product);
        em.getTransaction().commit();

        em.clear();

        assertEquals(em.find(Product.class, 4).getName(), product.getName());
    }

    @Test
    public void updateManagedObject() {
        Product product = em.find(Product.class, 1);
        product.setId(1);

        // Nesse caso eu posso apenas realizar as modificações no objeto que ao fim da transação ele irá persistir porque esse objeto é gerenciado pelo EM
        em.getTransaction().begin();
        product.setName("Livro Domain Driven Design");
        product.setDescription("O melhor livro para você dar um upgrade na sua carreira");
        em.getTransaction().commit();

        em.clear();

        Product productFinded = em.find(Product.class, 1);
        assertEquals(productFinded.getName(), product.getName());
    }

    @Test
    public void updateObject() {
        Product product = new Product();
        product.setId(1);
        product.setName("Livro Programador Pragmático");
        product.setDescription("O melhor livro para você dar um upgrade na sua carreira");
        product.setPrice(BigDecimal.TEN);

        em.getTransaction().begin();
        em.merge(product);
        em.getTransaction().commit();

        em.clear();

        Product productFinded = em.find(Product.class, 1);

        assertNotNull(productFinded);
        assertEquals(productFinded.getName(), product.getName());
    }

    @Test
    public void removeObject() {
        Product product = em.find(Product.class, 3);
        em.getTransaction().begin();
        em.remove(product);
        em.getTransaction().commit();

        //em.clear(); // Não é necessário na asserção para aperação de remoção

        assertNull(em.find(Product.class, 3));
    }

    @Test
    public void insertFirstProduct() {
        Product product = new Product();
        product.setId(2);
        product.setName("Notebook Acer");
        product.setDescription("Notebook com 32gb de ram, RTX 4050, ssd de 512gb.");
        product.setPrice(BigDecimal.valueOf(6700));

        em.getTransaction().begin();
        em.persist(product);
        em.getTransaction().commit();

        // Esse comando limpa a memória do Entity Manager.
        // Limpei a memória porque o EM salva na memória dele uma versão do objeto e se eu não limpar o assert vai buscar essa referência.
        // Então para a asserção fazer sentido é recomendado limpar o EM ou criar uma nova instância para ele fazer a consulta no banco corretamente.
        em.clear();

        Product findProd = em.find(Product.class, product.getId());
        assertEquals("Notebook Acer", findProd.getName());
    }

    @Test
    public void openAndCloseTransaction(){
        Product product = em.find(Product.class, 1);

        // É necessário abrir e fechar uma transação para realizar modificações no banco, sem isso não funciona
        em.getTransaction().begin();

        // O update é feito mesmo sem chamar o persist se manipular o objeto dentro da transação
        // product.setName("Monkey");
        // em.persist(product);
        // em.merge();
        // em.remove();

        em.getTransaction().commit();
        System.out.println(em.find(Product.class, 1));
    }
}
