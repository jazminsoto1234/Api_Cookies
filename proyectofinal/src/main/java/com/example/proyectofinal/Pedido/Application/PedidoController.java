package com.example.proyectofinal.Pedido.Application;


import com.example.proyectofinal.Pedido.Domain.Pedido;
import com.example.proyectofinal.Pedido.Service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {
    @Autowired
    private PedidoService pedidoService;

    @Secured({"USER", "ADMIN"})
    @GetMapping
    public ResponseEntity<List<Pedido>> getAllpedidos(){
        List<Pedido> listaPedido = pedidoService.getAllPedidos();
        return ResponseEntity.status(200).body(listaPedido);
    }


    @Secured({"USER", "ADMIN"})
    @GetMapping("{id}")
    public ResponseEntity<Pedido> getPedidoId(@PathVariable Long id){
        Optional<Pedido> existPedido = pedidoService.buscarPedidoById(id);
        if(existPedido.isPresent()){
            return ResponseEntity.status(200).body(existPedido.get());
        }else{
            return null; //Por ahora esto
        }
    }

    @Secured("USER")
    @DeleteMapping("{id}")
    public ResponseEntity<String> eliminarPedido(@PathVariable Long id){
        pedidoService.deletePedido(id);
        return ResponseEntity.status(200).body("Deleted");
    }



    @Secured("USER")
    @PutMapping("{id}")
    public ResponseEntity<Pedido> updatePedido(@PathVariable Long id, @RequestBody Pedido pedido){
        Pedido upPedido  = pedidoService.actualizarPedido(id, pedido);
        return ResponseEntity.status(200).body(upPedido);
    }


    @Secured("USER")
    @PostMapping
    public ResponseEntity<String> postPedido(@RequestBody Pedido pedido){
        pedidoService.insertarPedido(pedido);
        return ResponseEntity.status(201).body("Created");
    }


}
