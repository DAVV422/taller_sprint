package com.sw1.demo.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sw1.demo.model.DetalleVentaProducto;
import com.sw1.demo.repository.DetalleVentaProductoRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DetalleVentaProductoService {

    @Autowired
    private final DetalleVentaProductoRepository detalleVentaProductoRepository;

    public DetalleVentaProducto createDetalleVentaProducto(DetalleVentaProducto detalleVentaProducto) {
        return detalleVentaProductoRepository.save(detalleVentaProducto);
    }

    public DetalleVentaProducto getDetalleVentaProductoById(String id) {
        return detalleVentaProductoRepository.findById(id).orElse(null);
    }

    public List<DetalleVentaProducto> getAllDetalleVentaProductoOfNotaVenta(String notaVentaId) {
        return detalleVentaProductoRepository.findByNotaVentaId(notaVentaId);
    }

    public void deleteDetalleVentaProducto(String id) {
        detalleVentaProductoRepository.deleteById(id);
    }
}

