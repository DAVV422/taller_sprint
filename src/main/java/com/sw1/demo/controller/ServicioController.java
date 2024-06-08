package com.sw1.demo.controller;

import com.sw1.demo.model.Servicio;
import com.sw1.demo.service.ServicioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@Slf4j
public class ServicioController {

    @Autowired
    private ServicioService servicioService;

    @QueryMapping
    public List<Servicio> getAllServicios() {
        log.info("Query All Servicios");
        return servicioService.getAllServicios();
    }

    @QueryMapping
    public Servicio getServicioById(@Argument String id) {
        log.info("Query Servicio by Id {}:", id);
        return servicioService.getServicioById(id);
    }

    @MutationMapping
    public Servicio createServicio(@Argument String nombre, @Argument String descripcion, @Argument String tipo, @Argument double tarifaBase) {
        Servicio servicio = new Servicio();
        servicio.setNombre(nombre);
        servicio.setDescripcion(descripcion);
        servicio.setTipo(tipo);
        servicio.setTarifaBase(tarifaBase);
        log.info("Create Servicio:", servicio.toString());
        return servicioService.createServicio(servicio);
    }

    @MutationMapping
    public Servicio updateServicio(@Argument String id, @Argument String nombre, @Argument String descripcion, @Argument String tipo, @Argument double tarifaBase) {
        Servicio servicioDetails = new Servicio();
        servicioDetails.setNombre(nombre);
        servicioDetails.setDescripcion(descripcion);
        servicioDetails.setTipo(tipo);
        servicioDetails.setTarifaBase(tarifaBase);
        log.info("Update Servicio:", servicioDetails.toString());
        return servicioService.updateServicio(id, servicioDetails);
    }

    @MutationMapping
    public Boolean deleteServicio(@Argument String id) {
        servicioService.deleteServicio(id);
        log.info("Delete Servicio by Id {}:", id);
        return true;
    }
}
