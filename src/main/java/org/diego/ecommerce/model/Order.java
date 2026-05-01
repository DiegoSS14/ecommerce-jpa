package org.diego.ecommerce.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
@Table(name = "order")
public class Order {
    @Id
    @EqualsAndHashCode.Include
    private Integer id;
    @Column(name = "order_date")
    private LocalDate orderDate;
    @Column(name = "completion_date")
    private LocalDate completionDate;
    @Column(name = "invoice_id")
    private Number invoiceId; // Nota FIscal id
    private StatusOrder status;
    private BigDecimal total;
}
