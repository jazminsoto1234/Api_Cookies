package com.example.proyectofinal.Galleta.Service;
import com.example.proyectofinal.Galleta.Domain.Galleta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.Set;


public interface GalletaService {

    public abstract void deleteGalleta(Long id);
    public abstract Page<Galleta> getAllGalletas(Pageable pageable);

    public abstract Galleta insertGalleta(Galleta galleta);

    public abstract Galleta actualizarGalleta(Long id, Galleta galleta);

    public abstract Optional<Galleta> buscarPorId(Long id);


}