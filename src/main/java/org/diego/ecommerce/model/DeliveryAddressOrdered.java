package org.diego.ecommerce.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryAddressOrdered {
    @Column(name = "zip_code")
    private String zipCode; // CEP
    @Column(name = "public_place")
    private String publicPlace; // Logradouro
    private String number;
    private String complement;
    private String district; // Bairro
    private String city;
}
