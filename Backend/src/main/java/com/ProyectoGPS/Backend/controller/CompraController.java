package com.ProyectoGPS.Backend.controller;

import com.ProyectoGPS.Backend.model.Compra;
import com.ProyectoGPS.Backend.service.CompraService;
import com.ProyectoGPS.Backend.dto.CompraRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/compras")
public class CompraController {

    @Autowired
    private CompraService compraService;

    @PostMapping
    public ResponseEntity<Compra> crearCompra(@RequestBody CompraRequest request) {
        return ResponseEntity.ok(compraService.crearCompra(request));
    }

    @GetMapping
    public ResponseEntity<List<Compra>> obtenerTodasLasCompras() {
        return ResponseEntity.ok(compraService.obtenerTodasLasCompras());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Compra> obtenerCompraPorId(@PathVariable Long id) {
        return ResponseEntity.ok(compraService.obtenerCompraPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Compra> actualizarCompra(@PathVariable Long id, @RequestBody CompraRequest request) {
        return ResponseEntity.ok(compraService.actualizarCompra(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCompra(@PathVariable Long id) {
        compraService.eliminarCompra(id);
        return ResponseEntity.ok().build();
    }
}
