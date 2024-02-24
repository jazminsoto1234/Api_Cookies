package com.example.proyectofinal.Galleta.Domain;
import com.example.proyectofinal.HandlyError.DataBaseException;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GalletaRepository extends JpaRepository<Galleta, Long> {
}
