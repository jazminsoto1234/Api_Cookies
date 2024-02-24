package com.example.proyectofinal.Pedido_Galleta.Domain;

import com.example.proyectofinal.Galleta.Domain.Galleta;
import com.example.proyectofinal.Pedido.Domain.Pedido;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import lombok.AllArgsConstructor;
//import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;


@AllArgsConstructor
@Setter
@Getter
@Embeddable
public class Pedido_GalletaId implements Serializable {

    @Column(name = "idgalleta")
    private Long galletaid;

    @Column(name = "idpedido")
    private Long pedidoid;


    public Pedido_GalletaId() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pedido_GalletaId that = (Pedido_GalletaId) o;
        return Objects.equals(galletaid, that.galletaid) && Objects.equals(pedidoid, that.pedidoid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(galletaid, pedidoid);
    }
}
