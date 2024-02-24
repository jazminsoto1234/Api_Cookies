package com.example.proyectofinal.Direccion.Application;

import com.example.proyectofinal.Direccion.Domain.Direccion;
import com.example.proyectofinal.Direccion.Service.DireccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/direccion")
public class DireccionController {
    @Autowired
    private DireccionService direccionService;

    //@Autowired
    //private DireccionExceptionHandler direccionExceptionHandler;

    @Secured({"USER"})
    @GetMapping
    public ResponseEntity<List<Direccion>> GetAllDireccion(){
        List<Direccion> direcciones = direccionService.getAllDireccion();
        return ResponseEntity.status(200).body(direcciones);
    }

    @Secured({"USER"})
    @GetMapping("/{id}") //Manejando el error de que no se encuentre esa direccion
    public ResponseEntity<Direccion> GetDireccionId(@PathVariable Long id){
        //try {
            Direccion direccion = direccionService.getByIdDireccion(id).get();
            return ResponseEntity.status(200).body(direccion);
        //}catch (DireccionNotFoundException e){

          //  return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        //}
    }

    @Secured("USER")
    @PostMapping
    public ResponseEntity<String> PostDireccion(@RequestBody Direccion direccion){
        direccionService.insertarDireccion(direccion);
        return ResponseEntity.status(200).body("Created");
    }

    @Secured("ADMIN")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> EliminarDireccion(@PathVariable Long id){
        direccionService.deleteDireccion(id);
        return  ResponseEntity.status(200).body("Deleted");
    }

    @Secured({"ADMIN", "USER"})
    @PutMapping("/{id}")
    public ResponseEntity<Direccion> PutDireccion(@RequestBody Direccion direccion, @PathVariable Long id){
        Direccion direccionactualizado =  direccionService.actualizarDireccion(id, direccion);
        return ResponseEntity.status(200).body(direccionactualizado);
    }

}
