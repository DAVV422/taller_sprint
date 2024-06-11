package com.sw1.demo.service;

import com.sw1.demo.model.OrdenDeTrabajo;
import com.sw1.demo.repository.OrdenDeTrabajoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrdenDeTrabajoService {

    @Autowired
    private final OrdenDeTrabajoRepository ordenDeTrabajoRepository;

    public OrdenDeTrabajo createOrdenDeTrabajo(OrdenDeTrabajo ordenDeTrabajo) {
        return ordenDeTrabajoRepository.save(ordenDeTrabajo);
    }

    public OrdenDeTrabajo updateOrdenDeTrabajo(String id, OrdenDeTrabajo ordenDeTrabajoDetails) {
        OrdenDeTrabajo ordenDeTrabajo = ordenDeTrabajoRepository.findById(id).orElse(null);
        if (ordenDeTrabajo != null) {
            ordenDeTrabajo.setFechaInicio(ordenDeTrabajoDetails.getFechaInicio() != null ? 
                                          ordenDeTrabajoDetails.getFechaInicio() : ordenDeTrabajo.getFechaInicio());
            ordenDeTrabajo.setFechaFin(ordenDeTrabajoDetails.getFechaFin() != null ? 
                                       ordenDeTrabajoDetails.getFechaFin() : ordenDeTrabajo.getFechaFin());
            ordenDeTrabajo.setEstado(ordenDeTrabajoDetails.getEstado() != null ? 
                                     ordenDeTrabajoDetails.getEstado() : ordenDeTrabajo.getEstado());
            ordenDeTrabajo.setObservacion(ordenDeTrabajoDetails.getObservacion() != null ? 
                                          ordenDeTrabajoDetails.getObservacion() : ordenDeTrabajo.getObservacion());
            ordenDeTrabajo.setPersonalId(ordenDeTrabajoDetails.getPersonalId() != null ? 
                                         ordenDeTrabajoDetails.getPersonalId() : ordenDeTrabajo.getPersonalId());
            ordenDeTrabajo.setDetalleVentaServicioId(ordenDeTrabajoDetails.getDetalleVentaServicioId() != null ? 
                                                     ordenDeTrabajoDetails.getDetalleVentaServicioId() : ordenDeTrabajo.getDetalleVentaServicioId());
            return ordenDeTrabajoRepository.save(ordenDeTrabajo);
        }
        return null;
    }

    public List<OrdenDeTrabajo> getAllOrdenesDeTrabajo() {
        return ordenDeTrabajoRepository.findAll();
    }

    public OrdenDeTrabajo getOrdenDeTrabajoById(String id) {
        return ordenDeTrabajoRepository.findById(id).orElse(null);
    }

    public List<OrdenDeTrabajo> getAllOrdenesDeTrabajoByDetalleVentaServicioId(String detalleVentaServicioId) {
        return ordenDeTrabajoRepository.findByDetalleVentaServicioId(detalleVentaServicioId);
    }

    public void deleteOrdenDeTrabajo(String id) {
        ordenDeTrabajoRepository.deleteById(id);
    }
    
    public List<OrdenDeTrabajo> getAllOrdenesDeTrabajoByPersonalId(String personalId) {
        return ordenDeTrabajoRepository.findByPersonalId(personalId);
    }
}