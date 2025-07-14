package com.ProyectoGPS.Backend.controller;

import com.ProyectoGPS.Backend.model.Factura;
import com.ProyectoGPS.Backend.service.FacturaService;
import com.ProyectoGPS.Backend.dto.FacturaRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/facturas")
public class FacturaController {

    @Autowired
    private FacturaService facturaService;

    @PostMapping
    public ResponseEntity<Factura> crearFactura(@RequestBody FacturaRequest request) {
        return ResponseEntity.ok(facturaService.crearFactura(request));
    }

    @GetMapping
    public ResponseEntity<List<Factura>> obtenerTodasLasFacturas() {
        return ResponseEntity.ok(facturaService.obtenerTodasLasFacturas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Factura> obtenerFacturaPorId(@PathVariable Long id) {
        return ResponseEntity.ok(facturaService.obtenerFacturaPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Factura> actualizarFactura(@PathVariable Long id, @RequestBody FacturaRequest request) {
        return ResponseEntity.ok(facturaService.actualizarFactura(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarFactura(@PathVariable Long id) {
        facturaService.eliminarFactura(id);
        return ResponseEntity.ok().build();
    }
}
