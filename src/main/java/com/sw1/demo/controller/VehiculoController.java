package com.sw1.demo.controller;

import com.sw1.demo.model.Vehiculo;
import com.sw1.demo.service.VehiculoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@Slf4j
public class VehiculoController {

    @Autowired
    private VehiculoService vehiculoService;

    @QueryMapping
    public List<Vehiculo> getAllVehiculos() {
        log.info("Query All Vehiculos");
        return vehiculoService.getAllVehiculos();
    }

    @QueryMapping
    public Vehiculo getVehiculoById(@Argument String id) {
        log.info("Query Vehiculo by Id {}:", id);
        return vehiculoService.getVehiculoById(id);
    }

    @MutationMapping
    public Vehiculo createVehiculo(@Argument String matricula, @Argument String color, @Argument String descripcion, @Argument String modelo, @Argument String marcaId) {
        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setMatricula(matricula);
        vehiculo.setColor(color);
        vehiculo.setDescripcion(descripcion);
        vehiculo.setModelo(modelo);
        vehiculo.setMarcaId(marcaId);
        log.info("Create Vehiculo:", vehiculo.toString());
        return vehiculoService.createVehiculo(vehiculo);
    }

    @MutationMapping
    public Vehiculo updateVehiculo(@Argument String id, @Argument String matricula, @Argument String color, @Argument String descripcion, @Argument String modelo, @Argument String marcaId) {
        Vehiculo vehiculoDetails = new Vehiculo();
        vehiculoDetails.setMatricula(matricula);
        vehiculoDetails.setColor(color);
        vehiculoDetails.setDescripcion(descripcion);
        vehiculoDetails.setModelo(modelo);
        vehiculoDetails.setMarcaId(marcaId);
        log.info("Update Vehiculo:", vehiculoDetails.toString());
        return vehiculoService.updateVehiculo(id, vehiculoDetails);
    }

    @MutationMapping
    public Boolean deleteVehiculo(@Argument String id) {
        vehiculoService.deleteVehiculo(id);
        log.info("Delete Vehiculo by Id {}:", id);
        return true;
    }
}