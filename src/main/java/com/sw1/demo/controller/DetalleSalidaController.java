package com.sw1.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.sw1.demo.model.DetalleSalida;
import com.sw1.demo.service.DetalleSalidaService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class DetalleSalidaController {

    @Autowired
    private DetalleSalidaService detalleSalidaService;

    @QueryMapping
    public DetalleSalida getDetalleSalidaById(@Argument String id) {
        log.info("Query DetalleSalida by Id {}:", id);
        return detalleSalidaService.getDetalleSalidaById(id);
    }

    @QueryMapping
    public List<DetalleSalida> getAllDetalleSalidaOfNotaSalida(@Argument String notaSalidaId) {
        log.info("Query DetalleSalida of NotaSalida by Id {}:", notaSalidaId);
        return detalleSalidaService.getAllDetalleSalidaOfNotaSalida(notaSalidaId);
    }

    @MutationMapping
    public DetalleSalida createDetalleSalida(@Argument Integer cantidad, @Argument String productoId, @Argument String notaSalidaId) {
        DetalleSalida detalleSalida = new DetalleSalida();
        detalleSalida.setCantidad(cantidad);
        detalleSalida.setProductoId(productoId);
        detalleSalida.setNotaSalidaId(notaSalidaId);
        log.info("Create DetalleSalida:", detalleSalida.toString());
        return detalleSalidaService.createDetalleSalida(detalleSalida);
    }

    @MutationMapping
    public Boolean deleteDetalleSalida(@Argument String id) {
    	detalleSalidaService.deleteDetalleSalida(id);
        log.info("Delete DetalleSalida by Id {}:", id);
        return true;
    }
}
