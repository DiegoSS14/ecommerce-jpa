package org.diego.ecommerce.service;

import jakarta.persistence.PostLoad;

public class GenericListener {
    @PostLoad
    public void postLoad(Object object) {
        System.out.println("Entidade carregada com sucesso! " + object.toString());
    }
}
