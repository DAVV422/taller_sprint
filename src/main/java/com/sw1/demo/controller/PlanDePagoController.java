package com.sw1.demo.controller;

import com.sw1.demo.model.PlanDePago;
import com.sw1.demo.service.PlanDePagoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@Slf4j
public class PlanDePagoController {

    @Autowired
    private PlanDePagoService planDePagoService;

    @QueryMapping
    public PlanDePago getPlanDePagoById(@Argument String id) {
        log.info("Query PlanDePago by Id {}:", id);
        return planDePagoService.getPlanDePagoById(id);
    }

    @QueryMapping
    public List<PlanDePago> getAllPlanesDePago() {
        log.info("Query All PlanesDePago");
        return planDePagoService.getAllPlanesDePago();
    }

    @MutationMapping
    public PlanDePago createPlanDePago(@Argument String fechaInicio, @Argument int cantidadCuotas, @Argument Double montoCuota, @Argument String tiempoCuota, @Argument String tipo) {
        PlanDePago planDePago = new PlanDePago();
        planDePago.setFechaInicio(fechaInicio);
        planDePago.setCantidadCuotas(cantidadCuotas);
        planDePago.setMontoCuota(montoCuota);
        planDePago.setTiempoCuota(tiempoCuota);
        planDePago.setTipo(tipo);
        log.info("Create PlanDePago:", planDePago.toString());
        return planDePagoService.createPlanDePago(planDePago);
    }

    @MutationMapping
    public PlanDePago updatePlanDePago(@Argument String id, @Argument String fechaInicio, @Argument int cantidadCuotas, @Argument Double montoCuota, @Argument String tiempoCuota, @Argument String tipo) {
        PlanDePago planDePagoDetails = new PlanDePago();
        planDePagoDetails.setFechaInicio(fechaInicio);
        planDePagoDetails.setCantidadCuotas(cantidadCuotas);
        planDePagoDetails.setMontoCuota(montoCuota);
        planDePagoDetails.setTiempoCuota(tiempoCuota);
        planDePagoDetails.setTipo(tipo);
        log.info("Update PlanDePago:", planDePagoDetails.toString());
        return planDePagoService.updatePlanDePago(id, planDePagoDetails);
    }

    @MutationMapping
    public Boolean deletePlanDePago(@Argument String id) {
        planDePagoService.deletePlanDePago(id);
        log.info("Delete PlanDePago by Id {}:", id);
        return true;
    }
}
