package org.diego.ecommerce.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "ordered")
public class Ordered {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;

    @Column(name = "order_date")
    private LocalDate orderDate;

    @Column(name = "completion_date")
    private LocalDate completionDate;

    @OneToOne
    @JoinColumn(name = "invoice_id")
    // É possível utilizar @JoinTable aqui também, mesmo não sendo uma relação Many to Many
//    @JoinTable(name = "ordered_invoice",
//            joinColumns = @JoinColumn(name = "ordered_id"), inverseJoinColumns = @JoinColumn(name = "invoice_id"))
    private Invoice invoice; // Nota Fiscal id

    @ManyToOne(optional = false) // Muitos pedidos para um cliente
    @JoinColumn(name = "client_id")
    private Client client;

    @Enumerated(EnumType.STRING)
    private StatusOrder status;

    private BigDecimal total;

    @Embedded
    private DeliveryAddressOrdered deliveryAddressOrdered;

    @OneToMany(mappedBy = "ordered")
    private List<OrderedItem> orderedItem;

    @OneToOne(mappedBy = "ordered")
    CardPayment payment;
}
