package com.sw1.demo.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sw1.demo.model.DetalleCompra;
import com.sw1.demo.repository.DetalleCompraRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DetalleCompraService {

    @Autowired
    private final DetalleCompraRepository detalleCompraRepository;

    public DetalleCompra createDetalleCompra(DetalleCompra detalleCompra) {
        return detalleCompraRepository.save(detalleCompra);
    }

    public DetalleCompra getDetalleCompraById(String id) {
        return detalleCompraRepository.findById(id).orElse(null);
    }

    public List<DetalleCompra> getAllDetalleCompraOfNotaCompra(String notaCompraId) {
        return detalleCompraRepository.findByNotaCompraId(notaCompraId);
    }

    public void deleteDetalleCompra(String id) {
        detalleCompraRepository.deleteById(id);
    }
}

