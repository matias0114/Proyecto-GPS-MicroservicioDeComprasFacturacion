package com.ProyectoGPS.Backend.service;

import com.ProyectoGPS.Backend.model.GuiaDespacho;
import com.ProyectoGPS.Backend.model.Factura;
import com.ProyectoGPS.Backend.repository.GuiaDespachoRepository;
import com.ProyectoGPS.Backend.repository.FacturaRepository;
import com.ProyectoGPS.Backend.dto.GuiaDespachoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class GuiaDespachoService {
    
    @Autowired
    private GuiaDespachoRepository guiaDespachoRepository;

    @Autowired
    private FacturaRepository facturaRepository;

    public GuiaDespacho crearGuiaDespacho(GuiaDespachoRequest request) {
        Factura factura = facturaRepository.findById(request.getFacturaId())
            .orElseThrow(() -> new RuntimeException("Factura no encontrada con id: " + request.getFacturaId()));

        GuiaDespacho guiaDespacho = new GuiaDespacho();
        guiaDespacho.setNumeroGuia(request.getNumeroGuia());
        guiaDespacho.setFactura(factura);
        guiaDespacho.setFechaEmision(LocalDateTime.now());
        guiaDespacho.setTransportista(request.getTransportista());
        guiaDespacho.setEstado(request.getEstado());
        
        return guiaDespachoRepository.save(guiaDespacho);
    }

    public List<GuiaDespacho> obtenerTodasLasGuiasDespacho() {
        return guiaDespachoRepository.findAll();
    }

    public GuiaDespacho obtenerGuiaDespachoPorId(Long id) {
        return guiaDespachoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Guía de despacho no encontrada con id: " + id));
    }

    public GuiaDespacho actualizarGuiaDespacho(Long id, GuiaDespachoRequest request) {
        GuiaDespacho guiaDespacho = obtenerGuiaDespachoPorId(id);
        Factura factura = facturaRepository.findById(request.getFacturaId())
            .orElseThrow(() -> new RuntimeException("Factura no encontrada con id: " + request.getFacturaId()));

        guiaDespacho.setNumeroGuia(request.getNumeroGuia());
        guiaDespacho.setFactura(factura);
        guiaDespacho.setTransportista(request.getTransportista());
        guiaDespacho.setEstado(request.getEstado());
        
        return guiaDespachoRepository.save(guiaDespacho);
    }

    public void eliminarGuiaDespacho(Long id) {
        guiaDespachoRepository.deleteById(id);
    }
}
