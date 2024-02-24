package com.example.proyectofinal.Pedido_Galleta.Domain;

import com.example.proyectofinal.Galleta.Domain.Galleta;
import com.example.proyectofinal.Pedido.Domain.Pedido;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
@Table(name = "pedido_galleta")
public class Pedido_Galleta {

    @EmbeddedId
    private Pedido_GalletaId pedido_galletaId;

    @JsonIgnore //Implementar dto
    @ManyToOne
    @JoinColumn(name = "idpedido")
    @MapsId("pedidoid")
    private Pedido pedido;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "idgalleta")
    @MapsId("galletaid")
    private Galleta galleta;

    @Column(name = "precio")
    private Double precio;

    @Column(name = "cantidad")
    private Integer cantidad;

    public Pedido_Galleta() {

    }
}
