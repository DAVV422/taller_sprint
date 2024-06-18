package com.sw1.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sw1.demo.model.DetalleEntrada;
import com.sw1.demo.model.Entrada;
import com.sw1.demo.model.Producto;
import com.sw1.demo.repository.EntradaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EntradaService {

    @Autowired
    private final EntradaRepository entradaRepository;
    
    @Autowired
    private DetalleEntradaService detalleentradaService;

    @Autowired
    private ProductoService productoService;

    public Entrada createEntrada(Entrada entrada,String productoId,Integer cantidad) {
    	entrada=entradaRepository.save(entrada);
        DetalleEntrada detalleEntrada=new DetalleEntrada();
        detalleEntrada.setProductoId(productoId);
        detalleEntrada.setCantidad(cantidad);
        detalleEntrada.setNotaEntradaId(entrada.getId());
		//creacion de detalle
        detalleentradaService.createDetalleEntrada(detalleEntrada);
        //actualizar stock
        Producto producto=productoService.getProductoById(productoId);
        producto.setStock((Integer.parseInt(producto.getStock())+cantidad)+"");
        productoService.updateProducto(productoId, producto);
        return entrada;
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
