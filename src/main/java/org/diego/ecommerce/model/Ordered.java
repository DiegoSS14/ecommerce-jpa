package org.diego.ecommerce.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import org.diego.ecommerce.listener.InvoiceListener;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@EntityListeners(value = InvoiceListener.class)
@NoArgsConstructor
@Table(name = "ordered")
public class Ordered {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;

    @Column(name = "order_date")
    private LocalDate orderDate;

    @Column(name = "creation_date")
    private LocalDate creationDate;

    @Column(name = "last_update_date")
    private LocalDate lastUpdateDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "invoice_id")
    // É possível utilizar @JoinTable aqui também, mesmo não sendo uma relação Many to Many
//    @JoinTable(name = "ordered_invoice",
//            joinColumns = @JoinColumn(name = "ordered_id"), inverseJoinColumns = @JoinColumn(name = "invoice_id"))
    private Invoice invoice; // Nota Fiscal id

    @ManyToOne(optional = false, cascade = CascadeType.ALL) // Muitos pedidos para um cliente
    @JoinColumn(name = "client_id")
    private Client client;

    @Enumerated(EnumType.STRING)
    private StatusOrder status;

    private BigDecimal total;

    @Embedded
    private DeliveryAddressOrdered deliveryAddressOrdered;

    @OneToMany(mappedBy = "ordered", cascade = CascadeType.ALL)
    private List<OrderedItem> orderedItems;

    @OneToOne(mappedBy = "ordered", cascade = CascadeType.PERSIST)
    CardPayment payment;

    @PrePersist
    void prePersist() {
        this.creationDate = LocalDate.now();
        this.orderDate = LocalDate.now();
        calculateTotal();
    }

    @PostUpdate
    void onUpdate() {
        this.lastUpdateDate = LocalDate.now();
        calculateTotal();
    }

    void calculateTotal() {
        if (orderedItems != null) {
        this.total = this.orderedItems.stream()
                .map(OrderedItem::getProductPrice)
                .filter(Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        } else this.total =  BigDecimal.ZERO;
    }

    public boolean wasPaid() {
        return this.status.equals(StatusOrder.PAID);
    }

}
