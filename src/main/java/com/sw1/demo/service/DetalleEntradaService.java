package com.sw1.demo.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sw1.demo.model.DetalleEntrada;
import com.sw1.demo.repository.DetalleEntradaRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DetalleEntradaService {

    @Autowired
    private final DetalleEntradaRepository detalleEntradaRepository;

    public DetalleEntrada createDetalleEntrada(DetalleEntrada detalleEntrada) {
        return detalleEntradaRepository.save(detalleEntrada);
    }

    public DetalleEntrada getDetalleEntradaById(String id) {
        return detalleEntradaRepository.findById(id).orElse(null);
    }

    public List<DetalleEntrada> getAllDetalleEntradaOfNotaEntrada(String notaEntradaId) {
        return detalleEntradaRepository.findByNotaEntradaId(notaEntradaId);
    }

    public void deleteDetalleEntrada(String id) {
        detalleEntradaRepository.deleteById(id);
    }
}
