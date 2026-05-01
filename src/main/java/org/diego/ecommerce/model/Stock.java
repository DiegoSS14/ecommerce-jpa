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

@Entity
@ToString
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "stock")
public class Stock {
    @Id
    @EqualsAndHashCode.Include
    private Integer id;
    @Column(name = "product_id")
    private Integer ProductId;
    private Integer quantity;
}
