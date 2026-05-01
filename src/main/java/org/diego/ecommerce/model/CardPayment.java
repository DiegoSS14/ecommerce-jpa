package org.diego.ecommerce.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@ToString
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "card_payment")
public class CardPayment {
    @Id
    @EqualsAndHashCode.Include
    private Integer id;

    @Column(name = "order_id")
    private Integer orderId;
    private PaymentStatus status;
    private String number;
}
