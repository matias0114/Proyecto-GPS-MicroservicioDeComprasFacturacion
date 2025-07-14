package com.ProyectoGPS.Backend.controller;

import com.ProyectoGPS.Backend.model.GuiaDespacho;
import com.ProyectoGPS.Backend.service.GuiaDespachoService;
import com.ProyectoGPS.Backend.dto.GuiaDespachoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/guias-despacho")
public class GuiaDespachoController {

    @Autowired
    private GuiaDespachoService guiaDespachoService;

    @PostMapping
    public ResponseEntity<GuiaDespacho> crearGuiaDespacho(@RequestBody GuiaDespachoRequest request) {
        return ResponseEntity.ok(guiaDespachoService.crearGuiaDespacho(request));
    }

    @GetMapping
    public ResponseEntity<List<GuiaDespacho>> obtenerTodasLasGuiasDespacho() {
        return ResponseEntity.ok(guiaDespachoService.obtenerTodasLasGuiasDespacho());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GuiaDespacho> obtenerGuiaDespachoPorId(@PathVariable Long id) {
        return ResponseEntity.ok(guiaDespachoService.obtenerGuiaDespachoPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GuiaDespacho> actualizarGuiaDespacho(@PathVariable Long id, @RequestBody GuiaDespachoRequest request) {
        return ResponseEntity.ok(guiaDespachoService.actualizarGuiaDespacho(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarGuiaDespacho(@PathVariable Long id) {
        guiaDespachoService.eliminarGuiaDespacho(id);
        return ResponseEntity.ok().build();
    }
}
