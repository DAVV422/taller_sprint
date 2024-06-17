package com.sw1.demo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import com.sw1.demo.model.NotaCompra;
import com.sw1.demo.service.NotaCompraService;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class NotaCompraController {

    @Autowired
    private NotaCompraService notaCompraService;

    @QueryMapping
    public List<NotaCompra> getAllNotasCompra() {
        log.info("Query All NotasCompra");
        return notaCompraService.getAllNotasCompra();
    }

    @QueryMapping
    public NotaCompra getNotaCompraById(@Argument String id) {
        log.info("Query NotaCompra by Id {}:", id);
        return notaCompraService.getNotaCompraById(id);
    }

    @MutationMapping
    public NotaCompra createNotaCompra(@Argument String fecha, @Argument Double montoTotal, @Argument String personalId) {
        NotaCompra notaCompra = new NotaCompra();
        notaCompra.setFecha(fecha);
        notaCompra.setMontoTotal(montoTotal);
        notaCompra.setPersonalId(personalId);
        log.info("Create NotaCompra:", notaCompra.toString());
        return notaCompraService.createNotaCompra(notaCompra);
    }

    @MutationMapping
    public NotaCompra updateNotaCompra(@Argument String id, @Argument String fecha, @Argument Double montoTotal, @Argument String personalId) {
        NotaCompra notaCompraDetails = new NotaCompra();
        notaCompraDetails.setFecha(fecha);
        notaCompraDetails.setMontoTotal(montoTotal);
        notaCompraDetails.setPersonalId(personalId);
        log.info("Update NotaCompra:", notaCompraDetails.toString());
        return notaCompraService.updateNotaCompra(id, notaCompraDetails);
    }

    @MutationMapping
    public Boolean deleteNotaCompra(@Argument String id) {
        notaCompraService.deleteNotaCompra(id);
        log.info("Delete NotaCompra by Id {}:", id);
        return true;
    }
}
