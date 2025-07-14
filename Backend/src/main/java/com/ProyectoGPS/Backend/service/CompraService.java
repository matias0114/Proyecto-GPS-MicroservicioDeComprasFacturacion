package com.ProyectoGPS.Backend.service;

import com.ProyectoGPS.Backend.model.Compra;
import com.ProyectoGPS.Backend.repository.CompraRepository;
import com.ProyectoGPS.Backend.dto.CompraRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CompraService {
    
    @Autowired
    private CompraRepository compraRepository;

    public Compra crearCompra(CompraRequest request) {
        Compra compra = new Compra();
        compra.setNumeroOrden(request.getNumeroOrden());
        compra.setMontoTotal(request.getMontoTotal());
        compra.setProveedor(request.getProveedor());
        compra.setEstado(request.getEstado());
        compra.setFechaCompra(LocalDateTime.now());
        
        return compraRepository.save(compra);
    }

    public List<Compra> obtenerTodasLasCompras() {
        return compraRepository.findAll();
    }

    public Compra obtenerCompraPorId(Long id) {
        return compraRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Compra no encontrada con id: " + id));
    }

    public Compra actualizarCompra(Long id, CompraRequest request) {
        Compra compra = obtenerCompraPorId(id);
        compra.setNumeroOrden(request.getNumeroOrden());
        compra.setMontoTotal(request.getMontoTotal());
        compra.setProveedor(request.getProveedor());
        compra.setEstado(request.getEstado());
        
        return compraRepository.save(compra);
    }

    public void eliminarCompra(Long id) {
        compraRepository.deleteById(id);
    }
}
