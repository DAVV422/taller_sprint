package com.sw1.demo.controller;

import com.sw1.demo.model.Marca;
import com.sw1.demo.service.MarcaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@Slf4j
public class MarcaController {

    @Autowired
    private MarcaService marcaService;

    @QueryMapping
    public List<Marca> getAllMarcas() {
        log.info("Query All Marcas");
        return marcaService.getAllMarcas();
    }

    @QueryMapping
    public Marca getMarcaById(@Argument String id) {
        log.info("Query Marca by Id {}:", id);
        return marcaService.getMarcaById(id);
    }

    @MutationMapping
    public Marca createMarca(@Argument String nombre, @Argument Double porcentaje) {
        Marca marca = new Marca();
        marca.setNombre(nombre);
        marca.setPorcentaje(porcentaje);
        log.info("Create Marca:", marca.toString());
        return marcaService.createMarca(marca);
    }

    @MutationMapping
    public Marca updateMarca(@Argument String id, @Argument String nombre, @Argument Double porcentaje) {
        Marca marcaDetails = new Marca();
        marcaDetails.setNombre(nombre);
        marcaDetails.setPorcentaje(porcentaje);
        log.info("Update Marca:", marcaDetails.toString());
        return marcaService.updateMarca(id, marcaDetails);
    }

    @MutationMapping
    public Boolean deleteMarca(@Argument String id) {
        marcaService.deleteMarca(id);
        log.info("Delete Marca by Id {}:", id);
        return true;
    }
}
