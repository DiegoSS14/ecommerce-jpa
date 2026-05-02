package org.diego.ecommerce.model;

import jakarta.persistence.*;
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
    @Enumerated(EnumType.STRING)
    private ClientSex clientSex;
}
