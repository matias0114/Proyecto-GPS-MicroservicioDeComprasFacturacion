package com.ProyectoGPS.Backend.repository;

import com.ProyectoGPS.Backend.model.Compra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Long> {
    // Aquí puedes agregar métodos personalizados si los necesitas
}
