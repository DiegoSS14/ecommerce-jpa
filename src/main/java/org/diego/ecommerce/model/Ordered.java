package org.diego.ecommerce.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@ToString
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
    @Column(name = "invoice_id")
    private Number invoiceId; // Nota FIscal id
    @Enumerated(EnumType.STRING)
    private StatusOrder status;
    private BigDecimal total;
    @Embedded
    private DeliveryAddressOrdered deliveryAddressOrdered;
}
