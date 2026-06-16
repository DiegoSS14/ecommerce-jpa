package org.diego.ecommerce.product.entendendoEntityManager;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.diego.ecommerce.model.CardPayment;
import org.diego.ecommerce.model.Client;
import org.diego.ecommerce.model.Ordered;
import org.diego.ecommerce.model.OrderedItem;
import org.diego.ecommerce.model.PaymentStatus;
import org.diego.ecommerce.model.StatusOrder;
import org.diego.ecommerce.product.EntityManagerTest;
import org.junit.jupiter.api.Test;

public class CallbacksTest extends EntityManagerTest {
    @Test
    void testeDelisteners() {
        // 1. Inicia a transação (necessária para operações de escrita)
        em.getTransaction().begin();

        // 2. Cria e persiste o Cliente (necessário por causa da FK em Ordered)
        Client client = new Client();
        client.setName("Diego Sousa");
        em.persist(client);

        // 3. Cria a entidade Ordered que acionará o InvoiceListener
        Ordered ordered = new Ordered();
        ordered.setClient(client);
        ordered.setStatus(StatusOrder.PAID); // Garante que tem status para o fluxo

        // 4. Salva no banco de dados (Isso vai disparar o @PrePersist e o
        // InvoiceListener)
        em.persist(ordered);

        // 5. Sincroniza e limpa o cache do EntityManager
        em.flush();
        em.clear();

        // 6. Agora sim, busca do banco o pedido que ACABAMOS de criar
        Ordered orderedVerificacao = em.find(Ordered.class, ordered.getId());

        // 7. Validações
        assertNotNull(orderedVerificacao);
        assertNotNull(orderedVerificacao.getInvoice()); // Valida se o InvoiceListener funcionou!
        assertNotNull(orderedVerificacao.getCreationDate()); // Valida se o @PrePersist funcionou!

        em.getTransaction().commit();
    }

    @Test
    void testeDeCallbacks() {
        CardPayment cardPayment = new CardPayment();
        List<OrderedItem> orderedItems = new ArrayList<>();
        orderedItems.add(new OrderedItem());
        Ordered ordered = new Ordered();
        ordered.setClient(em.find(Client.class, 1));
        ordered.setStatus(StatusOrder.WAITING);
        ordered.setPayment(cardPayment);
        ordered.setOrderedItems(orderedItems);

        cardPayment.setStatus(PaymentStatus.PROCESSING);
        cardPayment.setNumber("123456789");
        cardPayment.setOrdered(ordered);

        em.getTransaction().begin();
        em.persist(ordered);
        em.flush();
        ordered.setStatus(StatusOrder.PAID);
        em.getTransaction().commit();

        // Para disparar o @PostUpdate é necessário que a alteração seja feita em outra
        // transação.
        em.getTransaction().begin();
        em.merge(ordered);
        em.getTransaction().commit();

        em.clear();

        Ordered findOrder = em.find(Ordered.class, ordered.getId());

        assertNotNull(findOrder.getCreationDate());
        assertNotNull(findOrder.getOrderDate());
        assertNotNull(findOrder.getLastUpdateDate());
    }
}
