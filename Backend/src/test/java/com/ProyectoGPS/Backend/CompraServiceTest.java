package com.proyectogps.Backend;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.ProyectoGPS.Backend.dto.CompraRequest;
import com.ProyectoGPS.Backend.model.Compra;
import com.ProyectoGPS.Backend.repository.CompraRepository;
import com.ProyectoGPS.Backend.service.CompraService;

@SpringBootTest
@ActiveProfiles("test")
public class CompraServiceTest {

    @Mock
    private CompraRepository compraRepository;

    @InjectMocks
    private CompraService compraService;

    private CompraRequest compraRequest;
    private Compra compra;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); 
        
        compraRequest = new CompraRequest();
        compraRequest.setNumeroOrden("ORD123");
        compraRequest.setMontoTotal(java.math.BigDecimal.valueOf(1500.0));
        compraRequest.setProveedor("Proveedor XYZ");
        compraRequest.setEstado("Pendiente");

        compra = new Compra();
        compra.setId(1L);
        compra.setNumeroOrden(compraRequest.getNumeroOrden());
        compra.setMontoTotal(compraRequest.getMontoTotal());
        compra.setProveedor(compraRequest.getProveedor());
        compra.setEstado(compraRequest.getEstado());
        compra.setFechaCompra(LocalDateTime.now());
    }

    @Test
    public void testCrearCompra() {
        when(compraRepository.save(any(Compra.class))).thenReturn(compra);

        Compra result = compraService.crearCompra(compraRequest);

        assertNotNull(result);
        assertEquals("ORD123", result.getNumeroOrden());
        assertEquals(1500.0, result.getMontoTotal());
        assertEquals("Proveedor XYZ", result.getProveedor());
        assertEquals("Pendiente", result.getEstado());
        verify(compraRepository, times(1)).save(any(Compra.class));
    }

    @Test
    public void testObtenerCompraPorId() {
        when(compraRepository.findById(1L)).thenReturn(Optional.of(compra));

        Compra result = compraService.obtenerCompraPorId(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("ORD123", result.getNumeroOrden());
        assertEquals(1500.0, result.getMontoTotal());
        assertEquals("Proveedor XYZ", result.getProveedor());
    }

    @Test
    public void testActualizarCompra() {
        when(compraRepository.findById(1L)).thenReturn(Optional.of(compra));
        when(compraRepository.save(any(Compra.class))).thenReturn(compra);

        compraRequest.setMontoTotal(java.math.BigDecimal.valueOf(2000.0));
        Compra result = compraService.actualizarCompra(1L, compraRequest);

        assertNotNull(result);
        assertEquals(2000.0, result.getMontoTotal()); 
        verify(compraRepository, times(1)).save(any(Compra.class));
    }

    @Test
    public void testEliminarCompra() {
        when(compraRepository.findById(1L)).thenReturn(Optional.of(compra));

        compraService.eliminarCompra(1L);

        verify(compraRepository, times(1)).deleteById(1L);
    }
}
