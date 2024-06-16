package com.sw1.demo.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sw1.demo.model.DetalleDevolucion;
import com.sw1.demo.repository.DetalleDevolucionRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DetalleDevolucionService {

    @Autowired
    private final DetalleDevolucionRepository detalleDevolucionRepository;

    public DetalleDevolucion createDetalleDevolucion(DetalleDevolucion detalleDevolucion) {
        return detalleDevolucionRepository.save(detalleDevolucion);
    }

    public DetalleDevolucion getDetalleDevolucionById(String id) {
        return detalleDevolucionRepository.findById(id).orElse(null);
    }

    public List<DetalleDevolucion> getAllDetalleDevolucionOfNotaDevolucion(String notaDevolucionId) {
        return detalleDevolucionRepository.findByNotaDevolucionId(notaDevolucionId);
    }

    public void deleteDetalleDevolucion(String id) {
        detalleDevolucionRepository.deleteById(id);
    }
}
