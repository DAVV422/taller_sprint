package com.sw1.demo.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sw1.demo.model.NotaDevolucion;
import com.sw1.demo.repository.NotaDevolucionRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotaDevolucionService {

    @Autowired
    private final NotaDevolucionRepository notaDevolucionRepository;

    public NotaDevolucion createNotaDevolucion(NotaDevolucion notaDevolucion) {
        return notaDevolucionRepository.save(notaDevolucion);
    }

    public NotaDevolucion updateNotaDevolucion(String id, NotaDevolucion notaDevolucionDetails) {
        NotaDevolucion notaDevolucion = notaDevolucionRepository.findById(id).orElse(null);
        if (notaDevolucion != null) {
            notaDevolucion.setFecha(notaDevolucionDetails.getFecha() != null ? notaDevolucionDetails.getFecha() : notaDevolucion.getFecha());
            notaDevolucion.setMotivo(notaDevolucionDetails.getMotivo() != null ? notaDevolucionDetails.getMotivo() : notaDevolucion.getMotivo());
            notaDevolucion.setMontoTotal(notaDevolucionDetails.getMontoTotal() != null ? notaDevolucionDetails.getMontoTotal() : notaDevolucion.getMontoTotal());
            notaDevolucion.setNotaVentaId(notaDevolucionDetails.getNotaVentaId() != null ? notaDevolucionDetails.getNotaVentaId() : notaDevolucion.getNotaVentaId());
        }
        return notaDevolucionRepository.save(notaDevolucion);
    }

    public List<NotaDevolucion> getAllNotasDevolucion() {
        return notaDevolucionRepository.findAll();
    }

    public NotaDevolucion getNotaDevolucionById(String id) {
        return notaDevolucionRepository.findById(id).orElse(null);
    }

    public void deleteNotaDevolucion(String id) {
        notaDevolucionRepository.deleteById(id);
    }
}

