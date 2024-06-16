package com.sw1.demo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import com.sw1.demo.model.NotaDevolucion;
import com.sw1.demo.service.NotaDevolucionService;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class NotaDevolucionController {

    @Autowired
    private NotaDevolucionService notaDevolucionService;

    @QueryMapping
    public List<NotaDevolucion> getAllNotasDevolucion() {
        log.info("Query All NotasDevolucion");
        return notaDevolucionService.getAllNotasDevolucion();
    }

    @QueryMapping
    public NotaDevolucion getNotaDevolucionById(@Argument String id) {
        log.info("Query NotaDevolucion by Id {}:", id);
        return notaDevolucionService.getNotaDevolucionById(id);
    }

    @MutationMapping
    public NotaDevolucion createNotaDevolucion(@Argument String fecha, @Argument String motivo, @Argument Double montoTotal, @Argument String notaVentaId) {
        NotaDevolucion notaDevolucion = new NotaDevolucion();
        notaDevolucion.setFecha(fecha);
        notaDevolucion.setMotivo(motivo);
        notaDevolucion.setMontoTotal(montoTotal);
        notaDevolucion.setNotaVentaId(notaVentaId);
        log.info("Create NotaDevolucion:", notaDevolucion.toString());
        return notaDevolucionService.createNotaDevolucion(notaDevolucion);
    }

    @MutationMapping
    public NotaDevolucion updateNotaDevolucion(@Argument String id, @Argument String fecha, @Argument String motivo, @Argument Double montoTotal, @Argument String notaVentaId) {
        NotaDevolucion notaDevolucionDetails = new NotaDevolucion();
        notaDevolucionDetails.setFecha(fecha);
        notaDevolucionDetails.setMotivo(motivo);
        notaDevolucionDetails.setMontoTotal(montoTotal);
        notaDevolucionDetails.setNotaVentaId(notaVentaId);
        log.info("Update NotaDevolucion:", notaDevolucionDetails.toString());
        return notaDevolucionService.updateNotaDevolucion(id, notaDevolucionDetails);
    }

    @MutationMapping
    public Boolean deleteNotaDevolucion(@Argument String id) {
        notaDevolucionService.deleteNotaDevolucion(id);
        log.info("Delete NotaDevolucion by Id {}:", id);
        return true;
    }
}
