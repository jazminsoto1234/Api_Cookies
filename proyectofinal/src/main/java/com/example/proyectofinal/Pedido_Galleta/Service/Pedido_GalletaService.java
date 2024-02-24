package com.example.proyectofinal.Pedido_Galleta.Service;

import com.example.proyectofinal.Pedido_Galleta.Domain.Pedido_Galleta;
import com.example.proyectofinal.Pedido_Galleta.Domain.Pedido_GalletaDTO;
import com.example.proyectofinal.Pedido_Galleta.Domain.Pedido_GalletaId;

import java.util.List;
import java.util.Optional;

public interface Pedido_GalletaService {

    public abstract void deletePedido_Galleta(Pedido_GalletaId id);

    public abstract Pedido_GalletaDTO insertPedido_Galleta(Pedido_Galleta pedido_galleta);

    public abstract Pedido_Galleta actualizarPedido_Galleta(Pedido_GalletaId id, Pedido_Galleta pedido_galleta);

    public abstract Optional<Pedido_GalletaDTO> buscarPorId(Pedido_GalletaId id);

    public abstract List<Pedido_GalletaDTO> listarPedido_Galleta();

    public abstract List<Pedido_GalletaDTO> listPedido_GalletaByPedidoId(Long PedidoId);

    public abstract List<Pedido_Galleta> guardarListaPedido_Galleta(List<Pedido_Galleta> pedido_galletas);
}
