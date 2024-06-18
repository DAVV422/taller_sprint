package com.sw1.demo.controller;

import com.sw1.demo.model.NotaVenta;
import com.sw1.demo.service.NotaVentaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@Slf4j
public class NotaVentaController {

    @Autowired
    private NotaVentaService notaVentaService;

    @QueryMapping
    public List<NotaVenta> getAllNotasVenta() {
        log.info("Query All NotasVenta");
        return notaVentaService.getAllNotasVenta();
    }

    @QueryMapping
    public NotaVenta getNotaVentaById(@Argument String id) {
        log.info("Query NotaVenta by Id {}:", id);
        return notaVentaService.getNotaVentaById(id);
    }

    @MutationMapping
    public NotaVenta createNotaVenta(@Argument String fecha, @Argument Double interes, @Argument String clienteId) {
        NotaVenta notaVenta = new NotaVenta();
        notaVenta.setFecha(fecha);
        notaVenta.setInteres(interes);        
        notaVenta.setClienteId(clienteId);
        log.info("Create NotaVenta:", notaVenta.toString());
        return notaVentaService.createNotaVenta(notaVenta);
    }

    @MutationMapping
    public NotaVenta updateNotaVenta(@Argument String id, @Argument String fecha, @Argument Double interes) {
        NotaVenta notaVentaDetails = new NotaVenta();        
        notaVentaDetails.setFecha(fecha);
        notaVentaDetails.setInteres(interes);               
        log.info("Update NotaVenta:", notaVentaDetails.toString());
        return notaVentaService.updateNotaVenta(id, notaVentaDetails);
    }

    @MutationMapping
    public Boolean deleteNotaVenta(@Argument String id) {
        notaVentaService.deleteNotaVenta(id);
        log.info("Delete NotaVenta by Id {}:", id);
        return true;
    }
}