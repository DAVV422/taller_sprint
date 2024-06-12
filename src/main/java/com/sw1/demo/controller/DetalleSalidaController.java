package com.sw1.demo.controller;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalTime;
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
    public List<DetalleSalida> getAllDetalleSalida() {
        log.info("Query All DetalleSalida");
        return detalleSalidaService.getAllDetalleSalida();
    }

    @QueryMapping
    public DetalleSalida getDetalleSalidaById(@Argument String id) {
        log.info("Query DetalleSalida by Id {}:", id);
        return detalleSalidaService.getDetalleSalidaById(id);
    }

    @MutationMapping
    public DetalleSalida createDetalleSalida(@Argument Integer cantidad) {
    	DetalleSalida DetalleSalida = new DetalleSalida();
    	DetalleSalida.setCantidad(cantidad);
    	return detalleSalidaService.createDetalleSalida(DetalleSalida);
    }

    @MutationMapping
    public DetalleSalida updateDetalleSalida(@Argument String id,@Argument String cantidad) {
    	DetalleSalida DetalleSalida = new DetalleSalida();
    	DetalleSalida.setId(id);
    	DetalleSalida.setCantidad(Integer.parseInt(cantidad));
        return detalleSalidaService.updateDetalleSalida(id, DetalleSalida);
    }

    @MutationMapping
    public Boolean deleteDetalleSalida(@Argument String id) {
    	detalleSalidaService.deleteDetalleSalida(id);
        return true;
    }
}
