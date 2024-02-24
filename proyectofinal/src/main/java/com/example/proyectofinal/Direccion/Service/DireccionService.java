package com.example.proyectofinal.Direccion.Service;

import com.example.proyectofinal.Direccion.Domain.Direccion;

import java.util.List;
import java.util.Optional;

public interface DireccionService {
    public abstract void deleteDireccion(Long id);

    public abstract List<Direccion> getAllDireccion();

    public abstract Direccion actualizarDireccion(Long id, Direccion direccion);

    public abstract Optional<Direccion> getByIdDireccion(Long id);

    public abstract Direccion insertarDireccion(Direccion direccion);
}
