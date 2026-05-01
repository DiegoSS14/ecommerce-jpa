package org.diego.ecommerce.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Entity
@ToString
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
// Nota fiscal
@Table(name = "invoice")
public class Invoice {
    @Id
    @EqualsAndHashCode.Include
    private Integer id;
    @Column(name = "order_id")
    private Integer orderId;
    private String xml;
    @Column(name = "issue_date")
    private Date issueDate; // Data de emissão
}
