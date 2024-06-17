package com.sw1.demo.controller;

import com.sw1.demo.model.DetalleEntrada;
import com.sw1.demo.service.DetalleEntradaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@Slf4j
public class DetalleEntradaController {

    @Autowired
    private DetalleEntradaService detalleEntradaService;

    @QueryMapping
    public DetalleEntrada getDetalleEntradaById(@Argument String id) {
        log.info("Query DetalleEntrada by Id {}:", id);
        return detalleEntradaService.getDetalleEntradaById(id);
    }

    @QueryMapping
    public List<DetalleEntrada> getAllDetalleEntradaOfNotaEntrada(@Argument String notaEntradaId) {
        log.info("Query DetalleEntrada of NotaEntrada by Id {}:", notaEntradaId);
        return detalleEntradaService.getAllDetalleEntradaOfNotaEntrada(notaEntradaId);
    }

    @MutationMapping
    public DetalleEntrada createDetalleEntrada(@Argument Integer cantidad, @Argument String productoId, @Argument String notaEntradaId) {
        DetalleEntrada detalleEntrada = new DetalleEntrada();
        detalleEntrada.setCantidad(cantidad);
        detalleEntrada.setProductoId(productoId);
        detalleEntrada.setNotaEntradaId(notaEntradaId);
        log.info("Create DetalleEntrada:", detalleEntrada.toString());
        return detalleEntradaService.createDetalleEntrada(detalleEntrada);
    }

    @MutationMapping
    public Boolean deleteDetalleEntrada(@Argument String id) {
        detalleEntradaService.deleteDetalleEntrada(id);
        log.info("Delete DetalleEntrada by Id {}:", id);
        return true;
    }
}
