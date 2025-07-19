package com.ProyectoGPS.Backend.TestIntegracion;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import com.ProyectoGPS.Backend.dto.CompraRequest;
import com.ProyectoGPS.Backend.model.Compra;
import com.ProyectoGPS.Backend.repository.CompraRepository;
import com.ProyectoGPS.Backend.service.CompraService;

@SpringBootTest
@ActiveProfiles("test") 
@Transactional
public class CompraServiceIntegrationTest {

    @Autowired
    private CompraService compraService;

    @Autowired
    private CompraRepository compraRepository;

    private CompraRequest compraRequest;

    @BeforeEach
    public void setUp() {
        compraRepository.deleteAll();
        compraRequest = new CompraRequest();
        compraRequest.setNumeroOrden("ORD123");
        compraRequest.setMontoTotal(new BigDecimal("1500.0"));
        compraRequest.setProveedor("Proveedor XYZ");
        compraRequest.setEstado("Pendiente");
    }

    @Test
    public void testCrearCompra() {
        Compra result = compraService.crearCompra(compraRequest);

        assertNotNull(result);
        assertEquals("ORD123", result.getNumeroOrden());
        assertEquals(0, result.getMontoTotal().compareTo(new BigDecimal("1500.0")));
        assertEquals("Proveedor XYZ", result.getProveedor());
        assertEquals("Pendiente", result.getEstado());
    }

    @Test
    public void testObtenerCompraPorId() {
        Compra createdCompra = compraService.crearCompra(compraRequest);

        Compra result = compraService.obtenerCompraPorId(createdCompra.getId());

        assertNotNull(result);
        assertEquals(createdCompra.getId(), result.getId());
        assertEquals("ORD123", result.getNumeroOrden());
        assertEquals(0, result.getMontoTotal().compareTo(new BigDecimal("1500.0")));
        assertEquals("Proveedor XYZ", result.getProveedor());
    }

    @Test
    public void testActualizarCompra() {
        Compra createdCompra = compraService.crearCompra(compraRequest);

        CompraRequest updateRequest = new CompraRequest();
        updateRequest.setNumeroOrden(createdCompra.getNumeroOrden());
        updateRequest.setMontoTotal(new BigDecimal("2000.0"));
        updateRequest.setProveedor(createdCompra.getProveedor());
        updateRequest.setEstado("Completada");

        Compra updatedCompra = compraService.actualizarCompra(createdCompra.getId(), updateRequest);

        assertNotNull(updatedCompra);
        assertEquals(0, updatedCompra.getMontoTotal().compareTo(new BigDecimal("2000.0")));  
        assertEquals("Completada", updatedCompra.getEstado()); 
    }

    @Test
    public void testEliminarCompra() {
        Compra createdCompra = compraService.crearCompra(compraRequest);

        compraService.eliminarCompra(createdCompra.getId());

        assertThrows(RuntimeException.class, () -> {
            compraService.obtenerCompraPorId(createdCompra.getId());
        });
    }

    @Test
    public void testObtenerTodasLasCompras() {
        compraService.crearCompra(compraRequest);

        CompraRequest compraRequest2 = new CompraRequest();
        compraRequest2.setNumeroOrden("ORD124");
        compraRequest2.setMontoTotal(new BigDecimal("1600.0"));
        compraRequest2.setProveedor("Proveedor XYZ");
        compraRequest2.setEstado("Pendiente");
        compraService.crearCompra(compraRequest2);

        List<Compra> result = compraService.obtenerTodasLasCompras();

        assertEquals(2, result.size());
    }
}