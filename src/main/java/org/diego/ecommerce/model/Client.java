package org.diego.ecommerce.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;
    private String name;
    @Column(name = "client_sex")
    @Enumerated(EnumType.STRING)
    private ClientSex clientSex;

    @OneToMany(mappedBy = "client")
    private List<Ordered> ordereds;
}
