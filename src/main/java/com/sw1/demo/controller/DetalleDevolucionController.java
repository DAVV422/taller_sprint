package com.sw1.demo.controller;

import com.sw1.demo.model.DetalleDevolucion;
import com.sw1.demo.service.DetalleDevolucionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@Slf4j
public class DetalleDevolucionController {

    @Autowired
    private DetalleDevolucionService detalleDevolucionService;

    @QueryMapping
    public DetalleDevolucion getDetalleDevolucionById(@Argument String id) {
        log.info("Query DetalleDevolucion by Id {}:", id);
        return detalleDevolucionService.getDetalleDevolucionById(id);
    }

    @QueryMapping
    public List<DetalleDevolucion> getAllDetalleDevolucionOfNotaDevolucion(@Argument String notaDevolucionId) {
        log.info("Query DetalleDevolucion of NotaDevolucion by Id {}:", notaDevolucionId);
        return detalleDevolucionService.getAllDetalleDevolucionOfNotaDevolucion(notaDevolucionId);
    }

    @MutationMapping
    public DetalleDevolucion createDetalleDevolucion(@Argument Integer cantidad, @Argument Double monto, @Argument String productoId, @Argument String notaDevolucionId) {
        DetalleDevolucion detalleDevolucion = new DetalleDevolucion();
        detalleDevolucion.setCantidad(cantidad);
        detalleDevolucion.setMonto(monto);
        detalleDevolucion.setProductoId(productoId);
        detalleDevolucion.setNotaDevolucionId(notaDevolucionId);
        log.info("Create DetalleDevolucion:", detalleDevolucion.toString());
        return detalleDevolucionService.createDetalleDevolucion(detalleDevolucion);
    }

    @MutationMapping
    public Boolean deleteDetalleDevolucion(@Argument String id) {
        detalleDevolucionService.deleteDetalleDevolucion(id);
        log.info("Delete DetalleDevolucion by Id {}:", id);
        return true;
    }
}

