package com.sw1.demo.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.sw1.demo.model.Proveedor;
import com.sw1.demo.service.ProveedorService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class ProveedorController {

    @Autowired
    private ProveedorService proveedorService;

    @QueryMapping
    public List<Proveedor> getAllProveedor() {
        log.info("Query All Proveedor");
        return proveedorService.getAllProveedor();
    }

    @QueryMapping
    public Proveedor getProveedorById(@Argument Integer id) {
        log.info("Query Proveedor by Id {}:", id);
        return proveedorService.getProveedorById(id);
    }

    @MutationMapping
    public Proveedor createProveedor(@Argument String nombre, @Argument String descripcion, @Argument String direccion, @Argument String celular,@Argument String email) {
    	Proveedor Proveedor = new Proveedor();
        Proveedor.setNombre(nombre);
        Proveedor.setDescripcion(descripcion);
        Proveedor.setDireccion(direccion);
        Proveedor.setEmail(email);
        Proveedor.setCelular(celular);
    	return proveedorService.createProveedor(Proveedor);
    }

    @MutationMapping
    public Proveedor updateProveedor(@Argument Integer id,@Argument String nombre, @Argument String descripcion, @Argument String direccion, @Argument String celular,@Argument String email) {
    	Proveedor Proveedor = new Proveedor();
    	Proveedor.setId(id);
    	Proveedor.setNombre(nombre);
        Proveedor.setDescripcion(descripcion);
        Proveedor.setDireccion(direccion);
        Proveedor.setEmail(email);
        Proveedor.setCelular(celular);
        return proveedorService.updateProveedor(id, Proveedor);
    }

    @MutationMapping
    public Boolean deleteProveedor(@Argument Integer id) {
    	proveedorService.deleteProveedor(id);
        return true;
    }
}
