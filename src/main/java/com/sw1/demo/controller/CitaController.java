package com.sw1.demo.controller;

import com.sw1.demo.model.Cita;
import com.sw1.demo.service.CitaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@Slf4j
public class CitaController {

    @Autowired
    private CitaService citaService;

    @QueryMapping
    public List<Cita> getAllCitas() {
        log.info("Query All Citas");
        return citaService.getAllCitas();
    }

    @QueryMapping
    public Cita getCitaById(@Argument String id) {
        log.info("Query Cita by Id {}:", id);
        return citaService.getCitaById(id);
    }

    @MutationMapping
    public Cita createCita(@Argument String fecha, @Argument String hora, @Argument String estado, @Argument String usuarioId, @Argument String personalId) {
        Cita cita = new Cita();
        cita.setFecha(fecha);
        cita.setHora(hora);
        cita.setEstado(estado);
        cita.setUsuarioId(usuarioId);
        cita.setPersonalId(personalId);
        log.info("Create Cita:", cita.toString());
        return citaService.createCita(cita);
    }

    @MutationMapping
    public Cita updateCita(@Argument String id, @Argument String fecha, @Argument String hora, @Argument String estado, @Argument String usuarioId, @Argument String personalId) {
        Cita citaDetails = new Cita();
        citaDetails.setFecha(fecha);
        citaDetails.setHora(hora);
        citaDetails.setEstado(estado);
        citaDetails.setUsuarioId(usuarioId);
        citaDetails.setPersonalId(personalId);
        log.info("Update Cita:", citaDetails.toString());
        return citaService.updateCita(id, citaDetails);
    }

    @MutationMapping
    public Boolean deleteCita(@Argument String id) {
        citaService.deleteCita(id);
        log.info("Delete Cita by Id {}:", id);
        return true;
    }
}
