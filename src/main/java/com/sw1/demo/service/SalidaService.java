package com.sw1.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sw1.demo.model.DetalleSalida;
import com.sw1.demo.model.Producto;
import com.sw1.demo.model.Salida;
import com.sw1.demo.repository.SalidaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SalidaService {

    @Autowired
    private final SalidaRepository salidaRepository;
    
    
    @Autowired
    private final DetalleSalidaService detalleSalidaService;
    
    @Autowired
    private final ProductoService productoService;

    public Salida createSalida(Salida salida,String productoId , Integer cantidad) {
    	salida=salidaRepository.save(salida);
    	DetalleSalida detalleSalida=new DetalleSalida();
    	detalleSalida.setProductoId(productoId);
    	detalleSalida.setCantidad(cantidad);
    	detalleSalida.setNotaSalidaId(salida.getId());
		//creacion de detalle
    	detalleSalidaService.createDetalleSalida(detalleSalida);
        //actualizar stock
        Producto producto=productoService.getProductoById(productoId);
        producto.setStock((Integer.parseInt(producto.getStock())-cantidad)+"");
        productoService.updateProducto(productoId, producto);
        return salida;
    }

    public Salida updateSalida(String id, Salida salidaDetalle) {
    	Salida salida = salidaRepository.findById(id).orElse(null);
        if (salida != null) {
        	salida.setMotivo(salidaDetalle.getMotivo());
        	salida.setFecha(salidaDetalle.getFecha());
        	salida.setHora(salidaDetalle.getHora());
        	return salidaRepository.save(salida);
        }
        return null;
    }

    public List<Salida> getAllSalida() {
        return salidaRepository.findAll();
    }

    public Salida getSalidaById(String id) {
        return salidaRepository.findById(id).orElse(null);
    }

    public void deleteSalida(String id) {
    	salidaRepository.deleteById(id);
    }
    
   /* public void IngresoSalidaPorVenta(Integer tipoventa,Producto producto,String ventaId,String motivo,Integer cantidad) {
		Salida salida=new Salida();
		DetalleSalida detalleSalida=new DetalleSalida();
		salida.setFecha(new Date().toGMTString());
		salida.setHora(new Date().getHours()+"");
    	if (tipoventa==1) {//manual
    		salida.setMotivo("Salida por venta "+motivo);
    	}
    	if(tipoventa==2) {//por venta
    		salida.setMotivo("Salida por venta con codigo :"+ventaId);
    	}
    	salidaRepository.save(salida);
    	detalleSalida.setProductoId(producto.getId());
    	detalleSalida.setSalidaId(salida.getId());
    	detalleSalida.setCantidad(cantidad);
    	detalleSalidaRepository.save(detalleSalida);
    }*/
}
