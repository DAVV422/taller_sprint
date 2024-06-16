package com.sw1.demo.controller;

import com.sw1.demo.model.DetalleCompra;
import com.sw1.demo.service.DetalleCompraService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@Slf4j
public class DetalleCompraController {

    @Autowired
    private DetalleCompraService detalleCompraService;

    @QueryMapping
    public DetalleCompra getDetalleCompraById(@Argument String id) {
        log.info("Query DetalleCompra by Id {}:", id);
        return detalleCompraService.getDetalleCompraById(id);
    }

    @QueryMapping
    public List<DetalleCompra> getAllDetalleCompraOfNotaCompra(@Argument String notaCompraId) {
        log.info("Query DetalleCompra of NotaCompra by Id {}:", notaCompraId);
        return detalleCompraService.getAllDetalleCompraOfNotaCompra(notaCompraId);
    }

    @MutationMapping
    public DetalleCompra createDetalleCompra(@Argument Double monto, @Argument Integer cantidad, @Argument String productoId, @Argument String notaCompraId) {
        DetalleCompra detalleCompra = new DetalleCompra();
        detalleCompra.setMonto(monto);
        detalleCompra.setCantidad(cantidad);
        detalleCompra.setProductoId(productoId);
        detalleCompra.setNotaCompraId(notaCompraId);
        log.info("Create DetalleCompra:", detalleCompra.toString());
        return detalleCompraService.createDetalleCompra(detalleCompra);
    }

    @MutationMapping
    public Boolean deleteDetalleCompra(@Argument String id) {
        detalleCompraService.deleteDetalleCompra(id);
        log.info("Delete DetalleCompra by Id {}:", id);
        return true;
    }
}
