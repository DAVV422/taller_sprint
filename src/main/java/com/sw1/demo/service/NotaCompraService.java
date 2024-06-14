package com.sw1.demo.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sw1.demo.model.NotaCompra;
import com.sw1.demo.repository.NotaCompraRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotaCompraService {

    @Autowired
    private final NotaCompraRepository notaCompraRepository;

    public NotaCompra createNotaCompra(NotaCompra notaCompra) {
        return notaCompraRepository.save(notaCompra);
    }

    public NotaCompra updateNotaCompra(String id, NotaCompra notaCompraDetails) {
        NotaCompra notaCompra = notaCompraRepository.findById(id).orElse(null);
        if (notaCompra != null) {
            notaCompra.setFecha(notaCompraDetails.getFecha() != null ? notaCompraDetails.getFecha() : notaCompra.getFecha());
            notaCompra.setMontoTotal(notaCompraDetails.getMontoTotal() != null ? notaCompraDetails.getMontoTotal() : notaCompra.getMontoTotal());
            notaCompra.setPersonalId(notaCompraDetails.getPersonalId() != null ? notaCompraDetails.getPersonalId() : notaCompra.getPersonalId());
        }
        return notaCompraRepository.save(notaCompra);
    }

    public List<NotaCompra> getAllNotasCompra() {
        return notaCompraRepository.findAll();
    }

    public NotaCompra getNotaCompraById(String id) {
        return notaCompraRepository.findById(id).orElse(null);
    }

    public void deleteNotaCompra(String id) {
        notaCompraRepository.deleteById(id);
    }
}