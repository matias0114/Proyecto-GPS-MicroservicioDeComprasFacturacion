package com.ProyectoGPS.Backend.service;

import com.ProyectoGPS.Backend.model.Factura;
import com.ProyectoGPS.Backend.model.Compra;
import com.ProyectoGPS.Backend.repository.FacturaRepository;
import com.ProyectoGPS.Backend.repository.CompraRepository;
import com.ProyectoGPS.Backend.dto.FacturaRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class FacturaService {
    
    @Autowired
    private FacturaRepository facturaRepository;

    @Autowired
    private CompraRepository compraRepository;

    public Factura crearFactura(FacturaRequest request) {
        Compra compra = compraRepository.findById(request.getCompraId())
            .orElseThrow(() -> new RuntimeException("Compra no encontrada con id: " + request.getCompraId()));

        Factura factura = new Factura();
        factura.setNumeroFactura(request.getNumeroFactura());
        factura.setCompra(compra);
        factura.setMontoTotal(request.getMontoTotal());
        factura.setRutProveedor(request.getRutProveedor());
        factura.setFechaEmision(LocalDateTime.now());
        
        return facturaRepository.save(factura);
    }

    public List<Factura> obtenerTodasLasFacturas() {
        return facturaRepository.findAll();
    }

    public Factura obtenerFacturaPorId(Long id) {
        return facturaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Factura no encontrada con id: " + id));
    }

    public Factura actualizarFactura(Long id, FacturaRequest request) {
        Factura factura = obtenerFacturaPorId(id);
        Compra compra = compraRepository.findById(request.getCompraId())
            .orElseThrow(() -> new RuntimeException("Compra no encontrada con id: " + request.getCompraId()));

        factura.setNumeroFactura(request.getNumeroFactura());
        factura.setCompra(compra);
        factura.setMontoTotal(request.getMontoTotal());
        factura.setRutProveedor(request.getRutProveedor());
        
        return facturaRepository.save(factura);
    }

    public void eliminarFactura(Long id) {
        facturaRepository.deleteById(id);
    }
}
