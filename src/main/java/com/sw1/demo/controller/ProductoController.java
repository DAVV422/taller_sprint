package com.sw1.demo.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.sw1.demo.model.Producto;
import com.sw1.demo.service.ProductoService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @QueryMapping
    public List<Producto> getAllProducto() {
        log.info("Query All Producto");
        return productoService.getAllProducto();
    }

    @QueryMapping
    public Producto getProductoById(@Argument Integer id) {
        log.info("Query Producto by Id {}:", id);
        return productoService.getProductoById(id);
    }

    @MutationMapping
    public Producto createProducto(@Argument String nombre, @Argument String descripcion, @Argument BigDecimal precio, @Argument String stock) {
    	Producto producto = new Producto();
        producto.setNombre(nombre);
        producto.setDescripcion(descripcion);
        producto.setPrecio(precio);
        producto.setStock(stock);
    	return productoService.createProducto(producto);
    }

    @MutationMapping
    public Producto updateProducto(@Argument Integer id,@Argument String nombre, @Argument String descripcion, @Argument BigDecimal precio, @Argument String stock) {
    	Producto producto = new Producto();
    	producto.setId(id);
        producto.setNombre(nombre);
        producto.setDescripcion(descripcion);
        producto.setPrecio(precio);
        producto.setStock(stock);
        return productoService.updateProducto(id, producto);
    }

    @MutationMapping
    public Boolean deleteProducto(@Argument Integer id) {
    	productoService.deleteProducto(id);
        return true;
    }
}
