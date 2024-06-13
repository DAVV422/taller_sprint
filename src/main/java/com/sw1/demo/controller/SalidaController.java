package com.sw1.demo.controller;

import java.sql.Date;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.sw1.demo.model.Salida;
import com.sw1.demo.service.SalidaService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class SalidaController {

    @Autowired
    private SalidaService salidaService;

    @QueryMapping
    public List<Salida> getAllSalida() {
        log.info("Query All Salida");
        return salidaService.getAllSalida();
    }

    @QueryMapping
    public Salida getSalidaById(@Argument Integer id) {
        log.info("Query Salida by Id {}:", id);
        return salidaService.getSalidaById(id);
    }

    @MutationMapping
    public Salida createSalida(@Argument Date fecha, @Argument String motivo, @Argument String hora) {
    	Salida Salida = new Salida();
        Salida.setFecha(fecha);
        Salida.setMotivo(motivo);
        Salida.setHora(LocalTime.parse(hora));
    	return salidaService.createSalida(Salida);
    }

    @MutationMapping
    public Salida updateSalida(@Argument Integer id,@Argument Date fecha,@Argument String motivo, @Argument String hora) {
    	Salida Salida = new Salida();
    	Salida.setId(id);
        Salida.setFecha(fecha);
        Salida.setMotivo(motivo);
        Salida.setHora(LocalTime.parse(hora));
        return salidaService.updateSalida(id, Salida);
    }

    @MutationMapping
    public Boolean deleteSalida(@Argument Integer id) {
    	salidaService.deleteSalida(id);
        return true;
    }
}
