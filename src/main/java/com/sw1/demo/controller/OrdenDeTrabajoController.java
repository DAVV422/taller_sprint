package com.sw1.demo.controller;

import com.sw1.demo.model.OrdenDeTrabajo;
import com.sw1.demo.service.OrdenDeTrabajoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@Slf4j
public class OrdenDeTrabajoController {

    @Autowired
    private OrdenDeTrabajoService ordenDeTrabajoService;

    @QueryMapping
    public OrdenDeTrabajo getOrdenDeTrabajoById(@Argument String id) {
        log.info("Query OrdenDeTrabajo by Id {}:", id);
        return ordenDeTrabajoService.getOrdenDeTrabajoById(id);
    }

    @QueryMapping
    public List<OrdenDeTrabajo> getAllOrdenesDeTrabajo() {
        log.info("Query All OrdenesDeTrabajo");
        return ordenDeTrabajoService.getAllOrdenesDeTrabajo();
    }

    @QueryMapping
    public List<OrdenDeTrabajo> getAllOrdenesDeTrabajoByDetalleVentaServicioId(@Argument String detalleVentaServicioId) {
        log.info("Query OrdenesDeTrabajo by DetalleVentaServicioId {}:", detalleVentaServicioId);
        return ordenDeTrabajoService.getAllOrdenesDeTrabajoByDetalleVentaServicioId(detalleVentaServicioId);
    }
    
    @QueryMapping
    public List<OrdenDeTrabajo> getAllOrdenesDeTrabajoByPersonalId(@Argument String personalId) {
        log.info("Query All OrdenesDeTrabajo by PersonalId {}:", personalId);
        return ordenDeTrabajoService.getAllOrdenesDeTrabajoByPersonalId(personalId);
    }

    @MutationMapping
    public OrdenDeTrabajo createOrdenDeTrabajo(@Argument String fechaInicio, @Argument String fechaFin, @Argument String estado, @Argument String observacion, @Argument String personalId, @Argument String detalleVentaServicioId) {
        OrdenDeTrabajo ordenDeTrabajo = new OrdenDeTrabajo();
        ordenDeTrabajo.setFechaInicio(fechaInicio);
        ordenDeTrabajo.setFechaFin(fechaFin);
        ordenDeTrabajo.setEstado(estado);
        ordenDeTrabajo.setObservacion(observacion);
        ordenDeTrabajo.setPersonalId(personalId);
        ordenDeTrabajo.setDetalleVentaServicioId(detalleVentaServicioId);
        log.info("Create OrdenDeTrabajo:", ordenDeTrabajo.toString());
        return ordenDeTrabajoService.createOrdenDeTrabajo(ordenDeTrabajo);
    }

    @MutationMapping
    public OrdenDeTrabajo updateOrdenDeTrabajo(@Argument String id, @Argument String fechaInicio, @Argument String fechaFin, @Argument String estado, @Argument String observacion, @Argument String personalId, @Argument String detalleVentaServicioId) {
        OrdenDeTrabajo ordenDeTrabajoDetails = new OrdenDeTrabajo();
        ordenDeTrabajoDetails.setFechaInicio(fechaInicio);
        ordenDeTrabajoDetails.setFechaFin(fechaFin);
        ordenDeTrabajoDetails.setEstado(estado);
        ordenDeTrabajoDetails.setObservacion(observacion);
        ordenDeTrabajoDetails.setPersonalId(personalId);
        ordenDeTrabajoDetails.setDetalleVentaServicioId(detalleVentaServicioId);
        log.info("Update OrdenDeTrabajo:", ordenDeTrabajoDetails.toString());
        return ordenDeTrabajoService.updateOrdenDeTrabajo(id, ordenDeTrabajoDetails);
    }

    @MutationMapping
    public Boolean deleteOrdenDeTrabajo(@Argument String id) {
        ordenDeTrabajoService.deleteOrdenDeTrabajo(id);
        log.info("Delete OrdenDeTrabajo by Id {}:", id);
        return true;
    }
}
