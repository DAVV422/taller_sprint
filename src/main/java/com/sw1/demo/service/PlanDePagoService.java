package com.sw1.demo.service;

import com.sw1.demo.model.PlanDePago;
import com.sw1.demo.repository.PlanDePagoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlanDePagoService {

    @Autowired
    private final PlanDePagoRepository planDePagoRepository;

    public PlanDePago createPlanDePago(PlanDePago planDePago) {
        return planDePagoRepository.save(planDePago);
    }

    public PlanDePago updatePlanDePago(String id, PlanDePago planDePagoDetails) {
        PlanDePago planDePago = planDePagoRepository.findById(id).orElse(null);
        if (planDePago != null) {
            planDePago.setFechaInicio(planDePagoDetails.getFechaInicio() != null ? 
                                      planDePagoDetails.getFechaInicio() : planDePago.getFechaInicio());
            planDePago.setCantidadCuotas(planDePagoDetails.getCantidadCuotas() != 0 ? 
                                         planDePagoDetails.getCantidadCuotas() : planDePago.getCantidadCuotas());
            planDePago.setMontoCuota(planDePagoDetails.getMontoCuota() != null ? 
                                     planDePagoDetails.getMontoCuota() : planDePago.getMontoCuota());
            planDePago.setTiempoCuota(planDePagoDetails.getTiempoCuota() != null ? 
                                      planDePagoDetails.getTiempoCuota() : planDePago.getTiempoCuota());
            planDePago.setTipo(planDePagoDetails.getTipo() != null ? 
                               planDePagoDetails.getTipo() : planDePago.getTipo());
            return planDePagoRepository.save(planDePago);
        }
        return null;
    }

    public List<PlanDePago> getAllPlanesDePago() {
        return planDePagoRepository.findAll();
    }

    public PlanDePago getPlanDePagoById(String id) {
        return planDePagoRepository.findById(id).orElse(null);
    }

    public void deletePlanDePago(String id) {
        planDePagoRepository.deleteById(id);
    }
}
