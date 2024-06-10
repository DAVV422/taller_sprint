package com.sw1.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sw1.demo.model.Producto;
import com.sw1.demo.repository.ProductoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductoService {

    @Autowired
    private final ProductoRepository productoRepository;

    public Producto createProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    public Producto updateProducto(Integer id, Producto productoDetalle) {
    	Producto producto = productoRepository.findById(id).orElse(null);
        if (producto != null) {
        	producto.setDescripcion(productoDetalle.getDescripcion());
        	producto.setPrecio(productoDetalle.getPrecio());
        	producto.setStock(productoDetalle.getStock());
        	producto.setNombre(productoDetalle.getNombre());
            return productoRepository.save(producto);
        }
        return null;
    }

    public List<Producto> getAllProducto() {
        return productoRepository.findAll();
    }

    public Producto getProductoById(Integer id) {
        return productoRepository.findById(id).orElse(null);
    }

    public void deleteProducto(Integer id) {
    	productoRepository.deleteById(id);
    }
}
