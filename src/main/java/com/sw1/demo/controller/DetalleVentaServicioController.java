package com.sw1.demo.controller;

import com.sw1.demo.model.DetalleVentaServicio;
import com.sw1.demo.service.DetalleVentaServicioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@Slf4j
public class DetalleVentaServicioController {

    @Autowired
    private DetalleVentaServicioService detalleVentaServicioService;

    @QueryMapping
    public DetalleVentaServicio getDetalleVentaServicioById(@Argument String id) {
        log.info("Query DetalleVentaServicio by Id {}:", id);
        return detalleVentaServicioService.getDetalleVentaServicioById(id);
    }

    @QueryMapping
    public List<DetalleVentaServicio> getAllDetalleVentaServicioOfNotaVenta(@Argument String notaVentaId) {
        log.info("Query DetalleVentaServicios of NotaVenta by Id {}:", notaVentaId);
        return detalleVentaServicioService.getAllDetalleVentaOfNotaVentaServicios(notaVentaId);
    }

    @MutationMapping
    public DetalleVentaServicio createDetalleVentaServicio(@Argument Float monto, @Argument String servicioId, @Argument String notaVentaId) {
        DetalleVentaServicio detalleVentaServicio = new DetalleVentaServicio();
        detalleVentaServicio.setMonto(monto);
        detalleVentaServicio.setServicioId(servicioId);
        detalleVentaServicio.setNotaVentaId(notaVentaId);
        log.info("Create DetalleVentaServicio:", detalleVentaServicio.toString());
        return detalleVentaServicioService.createDetalleVentaServicio(detalleVentaServicio);
    }

    @MutationMapping
    public Boolean deleteDetalleVentaServicio(@Argument String id) {
        detalleVentaServicioService.deleteDetalleVentaServicio(id);
        log.info("Delete DetalleVentaServicio by Id {}:", id);
        return true;
    }
}
