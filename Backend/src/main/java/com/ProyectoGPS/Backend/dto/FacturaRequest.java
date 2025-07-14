package com.ProyectoGPS.Backend.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class FacturaRequest {
    private String numeroFactura;
    private Long compraId;
    private BigDecimal montoTotal;
    private String rutProveedor;

    // Getters and Setters
    public String getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(String numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public Long getCompraId() {
        return compraId;
    }

    public void setCompraId(Long compraId) {
        this.compraId = compraId;
    }

    public BigDecimal getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(BigDecimal montoTotal) {
        this.montoTotal = montoTotal;
    }

    public String getRutProveedor() {
        return rutProveedor;
    }

    public void setRutProveedor(String rutProveedor) {
        this.rutProveedor = rutProveedor;
    }
}
