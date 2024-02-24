package com.example.proyectofinal.Pedido_Galleta.Domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Pedido_GalletaRepository extends JpaRepository<Pedido_Galleta, Pedido_GalletaId> {

    List<Pedido_Galleta> findByPedido_Id(Long idPedido);

}
