package com.sw1.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sw1.demo.model.Proveedor;
import com.sw1.demo.repository.ProveedorRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProveedorService {

    @Autowired
    private final ProveedorRepository proveedorRepository;

    public Proveedor createProveedor(Proveedor proveedor) {
        return proveedorRepository.save(proveedor);
    }

    public Proveedor updateProveedor(String id, Proveedor proveedorDetalle) {
        Proveedor proveedor = proveedorRepository.findById(id).orElse(null);
        if (proveedor != null) {
        	proveedor.setCelular(proveedorDetalle.getCelular());
        	proveedor.setDescripcion(proveedorDetalle.getDescripcion());
        	proveedor.setDireccion(proveedorDetalle.getDescripcion());
        	proveedor.setEmail(proveedorDetalle.getEmail());
        	proveedor.setNombre(proveedorDetalle.getNombre());
            return proveedorRepository.save(proveedor);
        }
        return null;
    }

    public List<Proveedor> getAllProveedor() {
        return proveedorRepository.findAll();
    }

    public Proveedor getProveedorById(String id) {
        return proveedorRepository.findById(id).orElse(null);
    }

    public void deleteProveedor(String id) {
    	proveedorRepository.deleteById(id);
    }
}
