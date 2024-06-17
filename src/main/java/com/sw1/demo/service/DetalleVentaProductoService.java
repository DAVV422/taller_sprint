package com.sw1.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sw1.demo.model.DetalleVentaProducto;
import com.sw1.demo.model.NotaVenta;
import com.sw1.demo.repository.DetalleVentaProductoRepository;
import com.sw1.demo.repository.NotaVentaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DetalleVentaProductoService {

    @Autowired
    private final DetalleVentaProductoRepository detalleVentaProductoRepository;
    
    @Autowired
    private final NotaVentaRepository notaVentaRepository;

    public DetalleVentaProducto createDetalleVentaProducto(DetalleVentaProducto detalleVentaProducto) {
    	DetalleVentaProducto detalleCreado = detalleVentaProductoRepository.save(detalleVentaProducto);
        NotaVenta notaVenta = notaVentaRepository.findById(detalleVentaProducto.getNotaVentaId()).orElse(null);        
        notaVenta.setSubtotal(notaVenta.getSubtotal() + detalleVentaProducto.getMontoTotal());
        Double interes = notaVenta.getSubtotal() * notaVenta.getInteres() / 100;
        notaVenta.setTotal(notaVenta.getSubtotal() + interes);
        interes = detalleVentaProducto.getMontoTotal() * notaVenta.getInteres() / 100;
        notaVenta.setSaldo(notaVenta.getSaldo() + detalleVentaProducto.getMontoTotal() + interes);
        notaVentaRepository.save(notaVenta);
        return detalleCreado;
    }

    public DetalleVentaProducto getDetalleVentaProductoById(String id) {
        return detalleVentaProductoRepository.findById(id).orElse(null);
    }

    public List<DetalleVentaProducto> getAllDetalleVentaProductoOfNotaVenta(String notaVentaId) {
        return detalleVentaProductoRepository.findByNotaVentaId(notaVentaId);
    }

    public void deleteDetalleVentaProducto(String id) {
    	DetalleVentaProducto detalleVentaProducto = detalleVentaProductoRepository.findById(id).orElse(null);
    	String noteId = detalleVentaProducto.getNotaVentaId(); 
    	Double monto = detalleVentaProducto.getMontoTotal();
        detalleVentaProductoRepository.deleteById(id);
        
        NotaVenta notaVenta = notaVentaRepository.findById(noteId).orElse(null);		
        if (notaVenta != null) {
        	notaVenta.setSubtotal(notaVenta.getSubtotal() - monto); 
        	Double interes = notaVenta.getSubtotal() * notaVenta.getInteres() / 100;
        	notaVenta.setTotal(notaVenta.getSubtotal() + interes);
        	interes = monto * notaVenta.getInteres() / 100;
        	notaVenta.setSaldo(notaVenta.getSaldo() - monto - interes);
        }        
        notaVentaRepository.save(notaVenta);
    }
}

