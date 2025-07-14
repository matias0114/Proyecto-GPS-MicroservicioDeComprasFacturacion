package com.ProyectoGPS.Backend.repository;

import com.ProyectoGPS.Backend.model.GuiaDespacho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuiaDespachoRepository extends JpaRepository<GuiaDespacho, Long> {
    // Aquí puedes agregar métodos personalizados si los necesitas
}
