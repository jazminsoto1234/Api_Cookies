package com.example.proyectofinal.Pedido_Galleta.Application;

import com.example.proyectofinal.Pedido_Galleta.Domain.Pedido_Galleta;
import com.example.proyectofinal.Pedido_Galleta.Domain.Pedido_GalletaDTO;
import com.example.proyectofinal.Pedido_Galleta.Domain.Pedido_GalletaId;
import com.example.proyectofinal.Pedido_Galleta.Domain.Pedido_GalletaRepository;
import com.example.proyectofinal.Pedido_Galleta.Service.Pedido_GalletaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidogalletas")
public class Pedido_GalletaController {

    @Autowired
    private Pedido_GalletaService pedidoGalletaService;


    @Secured({"USER", "ADMIN" })
    @GetMapping
    public ResponseEntity<List<Pedido_GalletaDTO>> getAllCarritos(){
        List<Pedido_GalletaDTO> listaPedidoGalleta = pedidoGalletaService.listarPedido_Galleta();
        return ResponseEntity.status(200).body(listaPedidoGalleta);
    }


    @GetMapping("/get")
    public ResponseEntity<Pedido_GalletaDTO> getPedidoGalletaId(@PathVariable Long idPedido, @PathVariable Long idGalleta){
        Pedido_GalletaId id = new Pedido_GalletaId(idGalleta, idPedido);
        Pedido_GalletaDTO pedido_galleta = pedidoGalletaService.buscarPorId(id).get();
        return ResponseEntity.status(200).body(pedido_galleta);
    }


    @GetMapping("/{idPedido}")
    public ResponseEntity<List<Pedido_GalletaDTO>> getPedidoGalletaPorPedido(@PathVariable Long idPedido){
        List<Pedido_GalletaDTO> listPedidoGalleta = pedidoGalletaService.listPedido_GalletaByPedidoId(idPedido);
        return ResponseEntity.status(200).body(listPedidoGalleta);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPedidoGalleta(@PathVariable Pedido_GalletaId id){
        pedidoGalletaService.deletePedido_Galleta(id);
        return ResponseEntity.status(200).body("Deleted");
    }

    @PutMapping("/{id}") //Deberia
    public ResponseEntity<Pedido_Galleta> putPedidoGalleta(@PathVariable Pedido_GalletaId id, @RequestBody Pedido_Galleta pedido_galleta){
        Pedido_Galleta actualizarPedidoGalleta = pedidoGalletaService.actualizarPedido_Galleta(id, pedido_galleta);
        return ResponseEntity.status(200).body(actualizarPedidoGalleta);
    }



    @PostMapping
    public ResponseEntity<String> postPedidoGalleta(@RequestBody Pedido_Galleta pedido_galleta){
        pedidoGalletaService.insertPedido_Galleta(pedido_galleta);
        return ResponseEntity.status(201).body("Created");
    }
}
