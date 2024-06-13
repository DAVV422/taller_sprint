package com.sw1.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sw1.demo.model.DetalleSalida;
import com.sw1.demo.repository.DetalleSalidaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DetalleSalidaService {

    @Autowired
    private final DetalleSalidaRepository detalleSalidaRepository;

    public DetalleSalida createDetalleSalida(DetalleSalida detalleSalida) {
        return detalleSalidaRepository.save(detalleSalida);
    }

    public DetalleSalida updateDetalleSalida(String id, DetalleSalida detalleSalidaDetalle) {
    	DetalleSalida detalleSalida = detalleSalidaRepository.findById(id).orElse(null);
        if (detalleSalida != null) {
        	detalleSalida.setCantidad(detalleSalidaDetalle.getCantidad());
            return detalleSalidaRepository.save(detalleSalida);
        }
        return null;
    }

    public List<DetalleSalida> getAllDetalleSalida() {
        return detalleSalidaRepository.findAll();
    }

    public DetalleSalida getDetalleSalidaById(String id) {
        return detalleSalidaRepository.findById(id).orElse(null);
    }

    public void deleteDetalleSalida(String id) {
    	detalleSalidaRepository.deleteById(id);
    }
}
