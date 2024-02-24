package com.example.proyectofinal.Galleta.Application;

import com.example.proyectofinal.Galleta.Domain.Galleta;
import com.example.proyectofinal.Galleta.Exceptions.GalletaNotFound;
import com.example.proyectofinal.Galleta.Service.GalletaImpl;
import com.example.proyectofinal.RestResponseEntityExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/galletas")
public class GalletaController {
    @Autowired
    private GalletaImpl galletaService;

    @GetMapping
    public ResponseEntity<Page<Galleta>> getGalletas(Pageable pageable) {
        Page<Galleta> pageGalletas = galletaService.getAllGalletas(pageable);
        return ResponseEntity.status(200).body(pageGalletas);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Galleta> getGalletaId(@PathVariable Long id){
        Optional<Galleta> existGalleta = galletaService.buscarPorId(id);
        if(existGalleta.isPresent()){
            return ResponseEntity.status(200).body(existGalleta.get());
        }else{
            throw new GalletaNotFound("Galleta con ID " + id + " no encontrada");
        }
    }

    /*
     * GET /galletas?page=0&size=10&sort=id,asc
     *
     *
     * */

    @Secured("ADMIN")
    @PostMapping
    public ResponseEntity<String> createGalleta(@RequestBody Galleta galleta){
        galletaService.insertGalleta(galleta);
        return ResponseEntity.status(201).body("Created");
    }


    @Secured("ADMIN")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eleminarGalleta(@PathVariable Long id){
        galletaService.deleteGalleta(id);
        return ResponseEntity.status(200).body("Deleted");
    }


    @Secured("ADMIN")
    @PutMapping("/{id}")
    public ResponseEntity<Galleta> updateGalleta(@PathVariable Long id, @RequestBody Galleta galleta){
        Galleta actualizadaGalleta = galletaService.actualizarGalleta(id, galleta);
        return ResponseEntity.status(200).body(actualizadaGalleta);
    }




}