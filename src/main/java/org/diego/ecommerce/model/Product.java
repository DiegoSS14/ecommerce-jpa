package org.diego.ecommerce.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.xml.namespace.QName;
import java.math.BigDecimal;
import java.util.List;

@Entity
@ToString
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    Integer id;
    String name;
    String description;
    BigDecimal price;

    @ManyToMany
    @JoinTable(
            name = "product_category",
            joinColumns = @JoinColumn(name = "product_id"), // Owner
            inverseJoinColumns = @JoinColumn(name = "category_id")) // Secondary
    List<Category> categorys;
}
