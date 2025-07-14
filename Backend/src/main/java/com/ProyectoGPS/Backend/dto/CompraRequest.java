package com.ProyectoGPS.Backend.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CompraRequest {
    private String numeroOrden;
    private BigDecimal montoTotal;
    private String proveedor;
    private String estado;

    // Getters and Setters
    public String getNumeroOrden() {
        return numeroOrden;
    }

    public void setNumeroOrden(String numeroOrden) {
        this.numeroOrden = numeroOrden;
    }

    public BigDecimal getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(BigDecimal montoTotal) {
        this.montoTotal = montoTotal;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
