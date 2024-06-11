package com.sw1.demo.controller;

import com.sw1.demo.model.DetalleDePago;
import com.sw1.demo.service.DetalleDePagoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@Slf4j
public class DetalleDePagoController {

    @Autowired
    private DetalleDePagoService detalleDePagoService;

    @QueryMapping
    public DetalleDePago getDetalleDePagoById(@Argument String id) {
        log.info("Query DetalleDePago by Id {}:", id);
        return detalleDePagoService.getDetalleDePagoById(id);
    }    

    @QueryMapping
    public List<DetalleDePago> getAllDetallesDePagoByPlanDePagoId(@Argument String planDePagoId) {
        log.info("Query DetallesDePago by PlanDePagoId {}:", planDePagoId);
        return detalleDePagoService.getAllDetallesDePagoByPlanDePagoId(planDePagoId);
    }

    @MutationMapping
    public DetalleDePago createDetalleDePago(@Argument String fechaPago, @Argument Double monto, @Argument String estadoPago, @Argument String planDePagoId) {
        DetalleDePago detalleDePago = new DetalleDePago();
        detalleDePago.setFechaPago(fechaPago);
        detalleDePago.setMonto(monto);
        detalleDePago.setEstadoPago(estadoPago);
        detalleDePago.setPlanDePagoId(planDePagoId);
        log.info("Create DetalleDePago:", detalleDePago.getEstadoPago());
        return detalleDePagoService.createDetalleDePago(detalleDePago);
    }
    
    @MutationMapping
    public DetalleDePago updateDetalleDePago(@Argument String id, @Argument String fechaPago, @Argument String estadoPago) {
        DetalleDePago detalleDePagoDetails = new DetalleDePago();
        detalleDePagoDetails.setFechaPago(fechaPago);        
        detalleDePagoDetails.setEstadoPago(estadoPago);        
        log.info("Update DetalleDePago:", detalleDePagoDetails.toString());
        return detalleDePagoService.updateDetalleDePago(id, detalleDePagoDetails);
    }


    @MutationMapping
    public Boolean deleteDetalleDePago(@Argument String id) {
        detalleDePagoService.deleteDetalleDePago(id);
        log.info("Delete DetalleDePago by Id {}:", id);
        return true;
    }
}