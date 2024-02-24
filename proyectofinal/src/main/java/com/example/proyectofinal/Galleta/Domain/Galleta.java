package com.example.proyectofinal.Galleta.Domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "galleta")
public class Galleta {
    @Id
    @Column(name = "idgalleta")
    private Long id;

    @Column(name = "name")
    private String nombre;

    @Column(name = "stock")
    private Integer stock;

    @Column(name = "characteristics")
    private String descripcion;


    @Column(name = "isoctogono")
    private Boolean isOctogono;


    @Column(name = "image")
    private String img;

    @Column(name = "precio")
    private Double precio;

    public Galleta() {
    }

    public Galleta(Long id, String nombre, Integer stock, String descripcion, Boolean isOctogono, String img, Double precio) {
        this.id = id;
        this.nombre = nombre;
        this.stock = stock;
        this.descripcion = descripcion;
        this.isOctogono = isOctogono;
        this.img = img;
        this.precio = precio;
    }


    //Getter and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean getisOctogono() {
        return isOctogono;
    }

    public void setisOctogono(Boolean isOctogono) {
        this.isOctogono = isOctogono;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

}