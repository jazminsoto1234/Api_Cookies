package com.example.proyectofinal.Pedido_Galleta.Domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.io.Serializable;

@AllArgsConstructor
@Getter
@Data
public class Pedido_GalletaDTO implements Serializable {
    private Long galletaId;
    private Long idPedido;

    private Double precio;

    private Integer cantidad;

    public Pedido_GalletaDTO() {}





}
