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

    public DetalleSalida createDetalleSalida(DetalleSalida detalleEntrada) {
        return detalleSalidaRepository.save(detalleEntrada);
    }

    public DetalleSalida getDetalleSalidaById(String id) {
        return detalleSalidaRepository.findById(id).orElse(null);
    }

    public List<DetalleSalida> getAllDetalleSalidaOfNotaSalida(String notaSalidaId) {
        return detalleSalidaRepository.findByNotaSalidaId(notaSalidaId);
    }

    public void deleteDetalleSalida(String id) {
    	detalleSalidaRepository.deleteById(id);
    }
}
