package com.sw1.demo.service;

import com.sw1.demo.model.Servicio;
import com.sw1.demo.repository.ServicioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServicioService {

    @Autowired
    private final ServicioRepository servicioRepository;

    public Servicio createServicio(Servicio servicio) {
        return servicioRepository.save(servicio);
    }

    public Servicio updateServicio(String id, Servicio servicioDetails) {
        Servicio servicio = servicioRepository.findById(id).orElse(null);
        if (servicio != null) {
            servicio.setNombre(servicioDetails.getNombre() != null ? servicioDetails.getNombre() : servicio.getNombre());
            servicio.setDescripcion(servicioDetails.getDescripcion() != null ? servicioDetails.getDescripcion() : servicio.getDescripcion());
            servicio.setTipo(servicioDetails.getTipo() != null ? servicioDetails.getTipo() : servicio.getTipo());
            servicio.setTarifaBase(servicioDetails.getTarifaBase() != 0 ? servicioDetails.getTarifaBase() : servicio.getTarifaBase());
            return servicioRepository.save(servicio);
        }
        return null;
    }

    public List<Servicio> getAllServicios() {
        return servicioRepository.findAll();
    }

    public Servicio getServicioById(String id) {
        return servicioRepository.findById(id).orElse(null);
    }

    public void deleteServicio(String id) {
        servicioRepository.deleteById(id);
    }
}