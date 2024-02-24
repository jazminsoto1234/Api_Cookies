package com.example.proyectofinal.Pedido.Service;

import com.example.proyectofinal.Pedido.Domain.Pedido;

import java.util.List;
import java.util.Optional;

public interface PedidoService {
    public abstract void deletePedido(Long id);

    public abstract List<Pedido> getAllPedidos();

    public abstract Optional<Pedido> buscarPedidoById(Long id);

    public abstract Pedido actualizarPedido(Long id, Pedido pedido);

    public abstract Pedido insertarPedido(Pedido pedido);
}
