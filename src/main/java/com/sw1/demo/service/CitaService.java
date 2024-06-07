package com.sw1.demo.service;

import com.sw1.demo.model.Cita;
import com.sw1.demo.repository.CitaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CitaService {

    @Autowired
    private final CitaRepository citaRepository;

    public Cita createCita(Cita cita) {
        return citaRepository.save(cita);
    }

    public Cita updateCita(String id, Cita citaDetails) {
        Cita cita = citaRepository.findById(id).orElse(null);
        if (cita != null) {
            cita.setFecha(citaDetails.getFecha() != null ? citaDetails.getFecha() : cita.getFecha());
            cita.setHora(citaDetails.getHora() != null ? citaDetails.getHora() : cita.getHora());
            cita.setEstado(citaDetails.getEstado() != null ? citaDetails.getEstado() : cita.getEstado());
            cita.setUsuarioId(citaDetails.getUsuarioId() != null ? citaDetails.getUsuarioId() : cita.getUsuarioId());
            cita.setPersonalId(citaDetails.getPersonalId() != null ? citaDetails.getPersonalId() : cita.getPersonalId());
            return citaRepository.save(cita);
        }
        return null;
    }

    public List<Cita> getAllCitas() {
        return citaRepository.findAll();
    }

    public Cita getCitaById(String id) {
        return citaRepository.findById(id).orElse(null);
    }

    public void deleteCita(String id) {
        citaRepository.deleteById(id);
    }
}
