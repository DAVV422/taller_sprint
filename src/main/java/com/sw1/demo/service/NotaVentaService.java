package com.sw1.demo.service;

import com.sw1.demo.model.NotaVenta;
import com.sw1.demo.repository.NotaVentaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
//import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NotaVentaService {

    @Autowired
    private final NotaVentaRepository notaVentaRepository;

    public NotaVenta createNotaVenta(NotaVenta notaVenta) {
        // Generar un código de seguimiento de 8 caracteres
        //String codigoSeguimiento = UUID.randomUUID().toString().replace("-", "").substring(0, 8);
        //notaVenta.setCodigoSeguimiento(codigoSeguimiento);
    	notaVenta.setTotal(Double.valueOf(0));
    	notaVenta.setSaldo(Double.valueOf(0));
    	notaVenta.setSubtotal(Double.valueOf(0));
        return notaVentaRepository.save(notaVenta);
    }

    public NotaVenta updateNotaVenta(String id, NotaVenta notaVentaDetails) {
        NotaVenta notaVenta = notaVentaRepository.findById(id).orElse(null);
        if (notaVenta != null) {
            notaVenta.setFecha(notaVentaDetails.getFecha() != null ? notaVentaDetails.getFecha() : notaVenta.getFecha());
            notaVenta.setInteres(notaVentaDetails.getInteres() != null ? notaVentaDetails.getInteres() : notaVenta.getInteres());                       
            return notaVentaRepository.save(notaVenta);
        }
        return null;
    }

    public List<NotaVenta> getAllNotasVenta() {
        return notaVentaRepository.findAll();
    }

    public NotaVenta getNotaVentaById(String id) {
        return notaVentaRepository.findById(id).orElse(null);
    }

    public void deleteNotaVenta(String id) {
        notaVentaRepository.deleteById(id);
    }
}