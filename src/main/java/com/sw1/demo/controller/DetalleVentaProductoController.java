package com.sw1.demo.controller;

import com.sw1.demo.model.DetalleVentaProducto;
import com.sw1.demo.service.DetalleVentaProductoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@Slf4j
public class DetalleVentaProductoController {

    @Autowired
    private DetalleVentaProductoService detalleVentaProductoService;

    @QueryMapping
    public DetalleVentaProducto getDetalleVentaProductoById(@Argument String id) {
        log.info("Query DetalleVentaProducto by Id {}:", id);
        return detalleVentaProductoService.getDetalleVentaProductoById(id);
    }

    @QueryMapping
    public List<DetalleVentaProducto> getAllDetalleVentaProductoOfNotaVenta(@Argument String notaVentaId) {
        log.info("Query DetalleVentaProducto of NotaVenta by Id {}:", notaVentaId);
        return detalleVentaProductoService.getAllDetalleVentaProductoOfNotaVenta(notaVentaId);
    }

    @MutationMapping
    public DetalleVentaProducto createDetalleVentaProducto(@Argument Double precioUnitario, @Argument Integer cantidad, @Argument Double montoTotal, @Argument String notaVentaId, @Argument String productoId) {
        DetalleVentaProducto detalleVentaProducto = new DetalleVentaProducto();
        detalleVentaProducto.setPrecioUnitario(precioUnitario);
        detalleVentaProducto.setCantidad(cantidad);
        detalleVentaProducto.setMontoTotal(montoTotal);
        detalleVentaProducto.setNotaVentaId(notaVentaId);
        detalleVentaProducto.setProductoId(productoId);
        log.info("Create DetalleVentaProducto:", detalleVentaProducto.toString());
        return detalleVentaProductoService.createDetalleVentaProducto(detalleVentaProducto);
    }

    @MutationMapping
    public Boolean deleteDetalleVentaProducto(@Argument String id) {
        detalleVentaProductoService.deleteDetalleVentaProducto(id);
        log.info("Delete DetalleVentaProducto by Id {}:", id);
        return true;
    }
}

