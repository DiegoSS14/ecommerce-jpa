package org.diego.ecommerce.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "client")
public class Client {
    @Id
    @EqualsAndHashCode.Include
    private Integer id;
    private String name;
    @Column(name = "client_sex")
    private ClientSex clientSex;
}
