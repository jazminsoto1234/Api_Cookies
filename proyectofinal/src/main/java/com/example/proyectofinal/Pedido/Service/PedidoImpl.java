package com.example.proyectofinal.Pedido.Service;

import com.example.proyectofinal.Pedido.Domain.Pedido;
import com.example.proyectofinal.Pedido.Domain.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoImpl implements PedidoService{
    @Autowired
    private PedidoRepository pedidoRepository;



    @Override
    public void deletePedido(Long id) {
        pedidoRepository.deleteById(id);

    }

    @Override
    public List<Pedido> getAllPedidos() {
        return pedidoRepository.findAll();
    }

    @Override
    public Optional<Pedido> buscarPedidoById(Long id) {
        return pedidoRepository.findById(id);
    }

    @Override
    public Pedido actualizarPedido(Long id, Pedido pedido) {
        Optional<Pedido> existPedido = pedidoRepository.findById(id);
        if(existPedido.isPresent()){
            Pedido pedidoup = existPedido.get();
            pedidoup.setFecha(pedido.getFecha());
            pedidoup.setIgv(pedido.getIgv());
            //pedidoup.setMonto_total(pedido.getMonto_total());
            return pedidoRepository.save(pedidoup);
        }else{
            return null; //Exepcion
        }

    }

    @Override
    public Pedido insertarPedido(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }
}


