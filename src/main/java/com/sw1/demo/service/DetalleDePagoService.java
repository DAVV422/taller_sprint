package com.sw1.demo.service;

import com.sw1.demo.model.DetalleDePago;
import com.sw1.demo.model.NotaVenta;
import com.sw1.demo.model.PlanDePago;
import com.sw1.demo.repository.DetalleDePagoRepository;
import com.sw1.demo.repository.NotaVentaRepository;
import com.sw1.demo.repository.PlanDePagoRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DetalleDePagoService {

    @Autowired
    private final DetalleDePagoRepository detalleDePagoRepository;
    
    @Autowired
    private final PlanDePagoRepository planDePagoRepository;
    
    @Autowired
    private final NotaVentaRepository notaVentaRepository;

    public DetalleDePago createDetalleDePago(DetalleDePago detalleDePago) {
    	if(detalleDePago.getEstadoPago().equals("pagado")) {
    		PlanDePago planDePago = planDePagoRepository.findById(detalleDePago.getPlanDePagoId()).orElse(null);    	
    		NotaVenta notaVenta = notaVentaRepository.findById(planDePago.getNotaVentaId()).orElse(null);
    		Double subtotal = notaVenta.getSubtotal() + detalleDePago.getMonto();
    		notaVenta.setSubtotal(subtotal);
    		notaVentaRepository.save(notaVenta);
    	}
        return detalleDePagoRepository.save(detalleDePago);
    }

    public DetalleDePago getDetalleDePagoById(String id) {
        return detalleDePagoRepository.findById(id).orElse(null);
    }
    
    public DetalleDePago updateDetalleDePago(String id, DetalleDePago detalleDePagoDetails) {
        DetalleDePago detalleDePago = detalleDePagoRepository.findById(id).orElse(null);        
        if (detalleDePago != null) {
        	if(!detalleDePago.getEstadoPago().equals("pagado")) {
        		if(detalleDePagoDetails.getEstadoPago() != null && detalleDePagoDetails.getEstadoPago().equals("pagado")) {
            		PlanDePago planDePago = planDePagoRepository.findById(detalleDePago.getPlanDePagoId()).orElse(null);    	
            		NotaVenta notaVenta = notaVentaRepository.findById(planDePago.getNotaVentaId()).orElse(null);
            		Double subtotal = notaVenta.getSubtotal() + detalleDePago.getMonto();
            		notaVenta.setSubtotal(subtotal);
            		notaVentaRepository.save(notaVenta);
            	}        		
        		detalleDePago.setFechaPago(detalleDePagoDetails.getFechaPago() != null ? 
                        detalleDePagoDetails.getFechaPago() : detalleDePago.getFechaPago());            
        		detalleDePago.setEstadoPago(detalleDePagoDetails.getEstadoPago() != null ? 
                          detalleDePagoDetails.getEstadoPago() : detalleDePago.getEstadoPago());            
				return detalleDePagoRepository.save(detalleDePago);
        	}            
        }
        return null;
    }

    public List<DetalleDePago> getAllDetallesDePagoByPlanDePagoId(String planDePagoId) {    	
        return detalleDePagoRepository.findByPlanDePagoId(planDePagoId);
    }

    public void deleteDetalleDePago(String id) {
    	DetalleDePago detalleDePago = detalleDePagoRepository.findById(id).orElse(null);
    	String estado = detalleDePago.getEstadoPago();
    	String planDePagoId = detalleDePago.getPlanDePagoId(); 
    	Double monto = detalleDePago.getMonto();
        detalleDePagoRepository.deleteById(id);
        if(estado.equals("pagado")) {
        	PlanDePago planDePago = planDePagoRepository.findById(planDePagoId).orElse(null);
            NotaVenta notaVenta = notaVentaRepository.findById(planDePago.getNotaVentaId()).orElse(null);        
            notaVenta.setSubtotal(notaVenta.getSubtotal() - monto);
            notaVentaRepository.save(notaVenta);
        }        
    }
}