package org.diego.ecommerce.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Entity
@ToString
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
// Nota fiscal
@Table(name = "ordered_item")
public class OrderedItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;

    @ManyToOne //(fetch = FetchType.LAZY)
    @JoinColumn(name = "ordered_id")
    private Ordered ordered;

    @OneToOne
    private Product product;

    @Column(name = "product_price")
    private BigDecimal productPrice;

    private Integer quantity;
}
