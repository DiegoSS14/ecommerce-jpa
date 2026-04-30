package org.diego.ecommerce.cliente;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Client {
    @Id
    @EqualsAndHashCode.Include
    Integer id;
    String name;
}
