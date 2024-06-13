package com.sw1.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sw1.demo.model.DetalleSalida;
import com.sw1.demo.model.Salida;
import com.sw1.demo.repository.SalidaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SalidaService {

    @Autowired
    private final SalidaRepository salidaRepository;

    public Salida createSalida(Salida salida) {
        return salidaRepository.save(salida);
    }

    public Salida updateSalida(String id, Salida salidaDetalle) {
    	Salida salida = salidaRepository.findById(id).orElse(null);
        if (salida != null) {
        	salida.setMotivo(salidaDetalle.getMotivo());
        	salida.setFecha(salidaDetalle.getFecha());
        	salida.setHora(salidaDetalle.getHora());
        	return salidaRepository.save(salida);
        }
        return null;
    }

    public List<Salida> getAllSalida() {
        return salidaRepository.findAll();
    }

    public Salida getSalidaById(String id) {
        return salidaRepository.findById(id).orElse(null);
    }

    public void deleteSalida(String id) {
    	salidaRepository.deleteById(id);
    }
}
