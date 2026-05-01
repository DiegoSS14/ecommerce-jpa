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
// Nota fiscal
@Table(name = "order_item")
public class OrderItem {
    @Id
    @EqualsAndHashCode.Include
    private Integer id;
    @Column(name = "order_id")
    private Integer orderId;
    @Column(name = "product_id")
    private Integer productId;
    @Column(name = "product_price")
    private BigDecimal productPrice;
    private Integer quantity;
}
