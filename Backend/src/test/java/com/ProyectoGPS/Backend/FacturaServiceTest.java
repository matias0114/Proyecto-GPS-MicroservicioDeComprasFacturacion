package com.ProyectoGPS.Backend;

import java.math.BigDecimal;
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

import com.ProyectoGPS.Backend.dto.FacturaRequest;
import com.ProyectoGPS.Backend.model.Compra;
import com.ProyectoGPS.Backend.model.Factura;
import com.ProyectoGPS.Backend.repository.CompraRepository;
import com.ProyectoGPS.Backend.repository.FacturaRepository;
import com.ProyectoGPS.Backend.service.FacturaService;

@SpringBootTest
@ActiveProfiles("test")
public class FacturaServiceTest {

    @Mock
    private FacturaRepository facturaRepository;

    @Mock
    private CompraRepository compraRepository;

    @InjectMocks
    private FacturaService facturaService;

    private FacturaRequest facturaRequest;
    private Factura factura;
    private Compra compra;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        compra = new Compra();
        compra.setId(1L);

        facturaRequest = new FacturaRequest();
        facturaRequest.setCompraId(1L);
        facturaRequest.setNumeroFactura("F123");
        facturaRequest.setMontoTotal(java.math.BigDecimal.valueOf(1000.0));
        facturaRequest.setRutProveedor("12345678-9");

        factura = new Factura();
        factura.setId(1L);
        factura.setNumeroFactura(facturaRequest.getNumeroFactura());
        factura.setCompra(compra);
        factura.setMontoTotal(facturaRequest.getMontoTotal());
        factura.setRutProveedor(facturaRequest.getRutProveedor());
        factura.setFechaEmision(LocalDateTime.now());
    }

    @Test
    public void testCrearFactura() {
        when(compraRepository.findById(1L)).thenReturn(Optional.of(compra));

        when(facturaRepository.save(any(Factura.class))).thenReturn(factura);

        Factura result = facturaService.crearFactura(facturaRequest);

        assertNotNull(result);
        assertEquals("F123", result.getNumeroFactura());
        assertEquals(java.math.BigDecimal.valueOf(1000.0), result.getMontoTotal());
        assertEquals("12345678-9", result.getRutProveedor());
        verify(facturaRepository, times(1)).save(any(Factura.class));
    }

    @Test
    public void testObtenerFacturaPorId() {
        when(facturaRepository.findById(1L)).thenReturn(Optional.of(factura));

        Factura result = facturaService.obtenerFacturaPorId(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("F123", result.getNumeroFactura());

        assertEquals(0, result.getMontoTotal().compareTo(new BigDecimal("1000.0")));
    }

    @Test
    public void testActualizarFactura() {
        when(facturaRepository.findById(1L)).thenReturn(Optional.of(factura));
        when(compraRepository.findById(1L)).thenReturn(Optional.of(compra));
        when(facturaRepository.save(any(Factura.class))).thenReturn(factura);

        facturaRequest.setMontoTotal(java.math.BigDecimal.valueOf(1500.0));
        Factura result = facturaService.actualizarFactura(1L, facturaRequest);

        assertNotNull(result);
        assertEquals(java.math.BigDecimal.valueOf(1500.0), result.getMontoTotal());  
        verify(facturaRepository, times(1)).save(any(Factura.class)); 
    }

    @Test
    public void testEliminarFactura() {
        when(facturaRepository.findById(1L)).thenReturn(Optional.of(factura));

        facturaService.eliminarFactura(1L);

        verify(facturaRepository, times(1)).deleteById(1L);
    }
}
