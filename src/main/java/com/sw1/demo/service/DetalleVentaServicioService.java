package com.sw1.demo.service;

import com.sw1.demo.model.DetalleVentaServicio;
import com.sw1.demo.model.Marca;
import com.sw1.demo.model.NotaVenta;
import com.sw1.demo.model.Servicio;
import com.sw1.demo.model.Vehiculo;
import com.sw1.demo.repository.DetalleVentaServicioRepository;
import com.sw1.demo.repository.MarcaRepository;
import com.sw1.demo.repository.NotaVentaRepository;
import com.sw1.demo.repository.ServicioRepository;
import com.sw1.demo.repository.VehiculoRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DetalleVentaServicioService {

    @Autowired
    private final DetalleVentaServicioRepository detalleVentaServicioRepository;
    
    @Autowired
    private final NotaVentaRepository notaVentaRepository;
    
    @Autowired
    private final VehiculoRepository vehiculoRepository;
    
    @Autowired
    private final MarcaRepository marcaRepository;
    
    @Autowired
    private final ServicioRepository servicioRepository;

    public DetalleVentaServicio createDetalleVentaServicio(DetalleVentaServicio detalleVentaServicio) {
    	Vehiculo vehiculo = vehiculoRepository.findById(detalleVentaServicio.getVehiculoId()).orElse(null);
    	Marca marca = marcaRepository.findById(vehiculo.getMarcaId()).orElse(null);
    	Servicio servicio = servicioRepository.findById(detalleVentaServicio.getServicioId()).orElse(null);
    	Double monto = marca.getPorcentaje() * servicio.getTarifaBase();
    	detalleVentaServicio.setMonto(monto + servicio.getTarifaBase());
    	DetalleVentaServicio detalleCreado = detalleVentaServicioRepository.save(detalleVentaServicio);
    	NotaVenta notaVenta = notaVentaRepository.findById(detalleVentaServicio.getNotaVentaId()).orElse(null);
        if (notaVenta.getCodigoSeguimiento() == null) {
        	// Generar un código de seguimiento de 8 caracteres
            String codigoSeguimiento = UUID.randomUUID().toString().replace("-", "").substring(0, 8);
            notaVenta.setCodigoSeguimiento(codigoSeguimiento);
        }
        notaVenta.setSubtotal(notaVenta.getSubtotal() + detalleVentaServicio.getMonto());
        Double interes = notaVenta.getSubtotal() * notaVenta.getInteres() / 100;
        notaVenta.setTotal(notaVenta.getSubtotal() + interes);
        interes = detalleVentaServicio.getMonto() * notaVenta.getInteres() / 100;
        notaVenta.setSaldo(notaVenta.getSaldo() + detalleVentaServicio.getMonto() + interes);
        notaVentaRepository.save(notaVenta);
        return detalleCreado;
    }   

    public List<DetalleVentaServicio> getAllDetalleVentaOfNotaVentaServicios(String id) {
        return detalleVentaServicioRepository.findByNotaVentaId(id);
    }

    public DetalleVentaServicio getDetalleVentaServicioById(String id) {
        return detalleVentaServicioRepository.findById(id).orElse(null);
    }

    public void deleteDetalleVentaServicio(String id) {    	
    	DetalleVentaServicio detalleVentaServicio = detalleVentaServicioRepository.findById(id).orElse(null);
    	String noteId = detalleVentaServicio.getNotaVentaId(); 
    	Double monto = detalleVentaServicio.getMonto();
        detalleVentaServicioRepository.deleteById(id);
        
        NotaVenta notaVenta = notaVentaRepository.findById(noteId).orElse(null);		
        if (notaVenta != null) {
        	notaVenta.setSubtotal(notaVenta.getSubtotal() - monto); 
        	Double interes = notaVenta.getSubtotal() * notaVenta.getInteres() / 100;
        	notaVenta.setTotal(notaVenta.getSubtotal() + interes);
        	interes = monto * notaVenta.getInteres() / 100;
        	notaVenta.setSaldo(notaVenta.getSaldo() - monto - interes);
        }
        List<DetalleVentaServicio> listaDetalleVentaServicio = detalleVentaServicioRepository.findByNotaVentaId(notaVenta.getId());
        if(listaDetalleVentaServicio.isEmpty()) {
        	notaVenta.setCodigoSeguimiento(null);
        }
        notaVentaRepository.save(notaVenta);
    }
}