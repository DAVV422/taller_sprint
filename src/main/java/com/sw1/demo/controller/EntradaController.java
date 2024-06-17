package com.sw1.demo.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.sw1.demo.model.DetalleEntrada;
import com.sw1.demo.model.Entrada;
import com.sw1.demo.model.Producto;
import com.sw1.demo.service.DetalleEntradaService;
import com.sw1.demo.service.EntradaService;
import com.sw1.demo.service.ProductoService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class EntradaController {

    @Autowired
    private EntradaService entradaService;
    
    
    @QueryMapping
    public List<Entrada> getAllEntradas() {
        log.info("Query All Entradas");
        return entradaService.getAllEntradas();
    }

    @QueryMapping
    public Entrada getEntradaById(@Argument String id) {
        log.info("Query Entrada by Id {}:", id);
        return entradaService.getEntradaById(id);
    }

    @MutationMapping
    public Entrada createEntrada(@Argument String fecha, @Argument String motivo, @Argument String hora, 
    		@Argument String productoId,@Argument Integer cantidad) {
        Entrada entrada = new Entrada();
        entrada.setFecha(fecha);
        entrada.setMotivo("Entrada por compra producto:"+productoId+" glosa :"+motivo);
        entrada.setHora(hora);
        entrada.setProductoId(productoId);
        log.info("Create Entrada:", entrada.toString());
        entrada = entradaService.createEntrada(entrada,productoId,cantidad);
        return entrada;
    }

    @MutationMapping
    public Entrada updateEntrada(@Argument String id, @Argument String fecha, @Argument String motivo, @Argument String hora) {
        Entrada entradaDetails = new Entrada();
        entradaDetails.setFecha(fecha);
        entradaDetails.setMotivo(motivo);
        entradaDetails.setHora(hora);
        log.info("Update Entrada:", entradaDetails.toString());
        return entradaService.updateEntrada(id, entradaDetails);
    }

    @MutationMapping
    public Boolean deleteEntrada(@Argument String id) {
        entradaService.deleteEntrada(id);
        log.info("Delete Entrada by Id {}:", id);
        return true;
    }
}

