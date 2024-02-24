package com.example.proyectofinal.Direccion.Service;

import com.example.proyectofinal.Direccion.Domain.Direccion;
import com.example.proyectofinal.Direccion.Domain.DireccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DireccionImpl implements DireccionService{
    @Autowired
    private DireccionRepository direccionRepository;

    @Override
    public void deleteDireccion(Long id) {
        direccionRepository.deleteById(id);
    }

    @Override
    public List<Direccion> getAllDireccion() {
        List<Direccion> direccions = direccionRepository.findAll();
        return direccions;
    }

    @Override
    public Direccion actualizarDireccion(Long id, Direccion direccion) {
        Direccion existDirecion = direccionRepository.findById(id).get();
        if(existDirecion != null){
            existDirecion.setCalle(direccion.getCalle());
            existDirecion.setCiudad(direccion.getCiudad());
            existDirecion.setProvincia(direccion.getProvincia());
            existDirecion.setReferencia(direccion.getReferencia());

            direccionRepository.save(existDirecion);
            return existDirecion;
        }else{
            return null;
        }

    }

    @Override
    public Optional<Direccion> getByIdDireccion(Long id) {
        return direccionRepository.findById(id);
    }

    @Override
    public Direccion insertarDireccion(Direccion direccion) {
        return direccionRepository.save(direccion);
    }

}
