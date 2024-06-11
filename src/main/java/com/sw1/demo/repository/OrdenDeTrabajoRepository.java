package com.sw1.demo.repository;

import com.sw1.demo.model.OrdenDeTrabajo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdenDeTrabajoRepository extends MongoRepository<OrdenDeTrabajo, String> {
    List<OrdenDeTrabajo> findByDetalleVentaServicioId(String detalleVentaServicioId);
    List<OrdenDeTrabajo> findByPersonalId(String personalId);
}
