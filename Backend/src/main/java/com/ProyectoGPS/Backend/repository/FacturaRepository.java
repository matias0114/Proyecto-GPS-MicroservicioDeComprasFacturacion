package com.ProyectoGPS.Backend.repository;

import com.ProyectoGPS.Backend.model.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacturaRepository extends JpaRepository<Factura, Long> {
    // Aquí puedes agregar métodos personalizados si los necesitas
}
