package com.example.proyectofinal.Pedido_Galleta.Service;

import com.example.proyectofinal.Pedido_Galleta.Domain.Pedido_Galleta;
import com.example.proyectofinal.Pedido_Galleta.Domain.Pedido_GalletaDTO;
import com.example.proyectofinal.Pedido_Galleta.Domain.Pedido_GalletaId;
import com.example.proyectofinal.Pedido_Galleta.Domain.Pedido_GalletaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class Pedido_GalletaImpl implements Pedido_GalletaService {

    @Autowired
    private Pedido_GalletaRepository pedido_galletaRepository;

    @Override
    public void deletePedido_Galleta(Pedido_GalletaId id){
        pedido_galletaRepository.deleteById(id);
    }

    @Override
    public Pedido_GalletaDTO insertPedido_Galleta(Pedido_Galleta pedido_galleta) {
        Pedido_Galleta pg = pedido_galletaRepository.save(pedido_galleta);
        //Pedido_GalletaDTO dt = new Pedido_GalletaDTO(pg.getPedido_galletaId().getGalletaid(), pg.getPedido_galletaId().getPedidoid(), pg.getPrecio(), pg.getCantidad())
        return new Pedido_GalletaDTO(pg.getPedido_galletaId().getGalletaid(),
                pg.getPedido_galletaId().getPedidoid(), pg.getPrecio(), pg.getCantidad());
    }

    @Override
    public Pedido_Galleta actualizarPedido_Galleta(Pedido_GalletaId id, Pedido_Galleta pedido_galleta) {
        Optional<Pedido_Galleta> existPedido_Galleta = pedido_galletaRepository.findById(id);
        if(existPedido_Galleta.isPresent()){
            Pedido_Galleta upPedido_Galleta = existPedido_Galleta.get();
            upPedido_Galleta.setCantidad(pedido_galleta.getCantidad());
            upPedido_Galleta.setPrecio(pedido_galleta.getPrecio());
            return pedido_galletaRepository.save(upPedido_Galleta);
        }else{
            return null; //Excepcion
        }
    }

    @Override
    public Optional<Pedido_GalletaDTO> buscarPorId(Pedido_GalletaId id) {
        Optional<Pedido_Galleta> pedidoGalleta = pedido_galletaRepository.findById(id);
        if(pedidoGalleta.isPresent()){
            Pedido_Galleta pedidoygalleta = pedidoGalleta.get();
            Pedido_GalletaDTO pedido_galletaDTO = new Pedido_GalletaDTO(pedidoygalleta.getPedido_galletaId().getGalletaid(), pedidoygalleta.getPedido_galletaId().getPedidoid(), pedidoygalleta.getPrecio(), pedidoygalleta.getCantidad());
            Optional<Pedido_GalletaDTO> opt= Optional.ofNullable(pedido_galletaDTO);
            return opt;
        }else{
            return null;
        }
    }

    @Override
    public List<Pedido_GalletaDTO> listarPedido_Galleta() {
        List<Pedido_Galleta> lpg =  pedido_galletaRepository.findAll();
        ModelMapper modelMapper = new ModelMapper();

        // Mapear cada objeto Pedido_Galleta a Pedido_GalletaDTO
        List<Pedido_GalletaDTO> listaDTO = lpg.stream()
                .map(pedidoGalleta -> modelMapper.map(pedidoGalleta, Pedido_GalletaDTO.class))
                .collect(Collectors.toList());
        return listaDTO;
    }

    @Override
    public List<Pedido_Galleta> guardarListaPedido_Galleta(List<Pedido_Galleta> pedido_galletas) {
        return pedido_galletaRepository.saveAll(pedido_galletas);
    }

    @Override
    public List<Pedido_GalletaDTO> listPedido_GalletaByPedidoId(Long PedidoId){
        List<Pedido_Galleta> lpg= pedido_galletaRepository.findByPedido_Id(PedidoId);
        ModelMapper modelMapper = new ModelMapper();

        // Mapear cada objeto Pedido_Galleta a Pedido_GalletaDTO
        List<Pedido_GalletaDTO> listaDTO = lpg.stream()
                .map(pedidoGalleta -> modelMapper.map(pedidoGalleta, Pedido_GalletaDTO.class))
                .collect(Collectors.toList());
        return listaDTO;
    }



}
