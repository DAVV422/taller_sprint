package com.sw1.demo.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sw1.demo.model.Entrada;
import com.sw1.demo.repository.EntradaRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EntradaService {

    @Autowired
    private final EntradaRepository entradaRepository;

    public Entrada createEntrada(Entrada entrada) {
        return entradaRepository.save(entrada);
    }

    public Entrada updateEntrada(String id, Entrada entradaDetails) {
        Entrada entrada = entradaRepository.findById(id).orElse(null);
        if (entrada != null) {
            entrada.setFecha(entradaDetails.getFecha() != null ? entradaDetails.getFecha() : entrada.getFecha());
            entrada.setMotivo(entradaDetails.getMotivo() != null ? entradaDetails.getMotivo() : entrada.getMotivo());
            entrada.setHora(entradaDetails.getHora() != null ? entradaDetails.getHora() : entrada.getHora());
        }
        return entradaRepository.save(entrada);
    }

    public List<Entrada> getAllEntradas() {
        return entradaRepository.findAll();
    }

    public Entrada getEntradaById(String id) {
        return entradaRepository.findById(id).orElse(null);
    }

    public void deleteEntrada(String id) {
        entradaRepository.deleteById(id);
    }
}
