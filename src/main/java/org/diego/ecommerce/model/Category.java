package org.diego.ecommerce.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@ToString
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "category")
public class Category {
    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
//    @SequenceGenerator(name = "seq", sequenceName = "sequence_primary_key", allocationSize = 50)
//    @GeneratedValue(strategy = GenerationType.TABLE, generator = "tabela")
//    @TableGenerator(name = "tabela", table = "hibernate_sequences",
//            pkColumnName = "sequence_name",
//            pkColumnValue = "categoria",
//            valueColumnName = "next_val",
//            initialValue = 0, // Default
//            allocationSize = 50 // Default
//    )
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;

    private String name;

    @Column(name = "parent_category_id")
    private Number parentCategoryId;
}
