package com.example.proyectofinal.Direccion.Domain;

import com.example.proyectofinal.Pedido.Domain.Pedido;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "direccion")
public class Direccion {
    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "iddireccion")
    private Long id;

    private String ciudad;

    private String provincia;

    private String calle;

    private String referencia;

    //@OneToMany()
    //private List<Pedido> pedidos;

    public Direccion(){}
}
