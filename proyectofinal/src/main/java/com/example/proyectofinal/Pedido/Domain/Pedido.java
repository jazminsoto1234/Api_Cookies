package com.example.proyectofinal.Pedido.Domain;

import com.example.proyectofinal.Direccion.Domain.Direccion;
import com.example.proyectofinal.Pedido_Galleta.Domain.Pedido_Galleta;
import com.example.proyectofinal.Pedido_Galleta.Domain.Pedido_GalletaDTO;
import com.example.proyectofinal.Usuario.Domain.UserEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "pedido")
public class Pedido {

    @Id
    @Column(name = "idpedido")
    private Long id;

    @Column(name = "fecha_pedido")
    private Date fecha;

    @Column(name = "igv")
    private Double igv;

    @OneToMany(mappedBy = "pedido")
    private List<Pedido_Galleta> detalles;



    @Transient
    @JsonProperty("subtotal")
    public Double getSubtotal() {
        return detalles.stream()
                .mapToDouble(detalle -> detalle.getPrecio() * detalle.getCantidad())
                .sum();
    }

    @Transient
    @JsonProperty("montoTotal")
    public Double getMontoTotal() {
        return getSubtotal()*igv + getSubtotal();
    }




    //@Column(name = "direccionid")
    //private Long direccionid;

    @ManyToOne
    @JoinColumn(name = "direccionid")
    private Direccion direccion;

    @ManyToOne
    @JoinColumn(name = "userid")
    private UserEntity user;





}
