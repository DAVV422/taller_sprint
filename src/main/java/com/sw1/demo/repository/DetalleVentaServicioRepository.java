package com.sw1.demo.repository;

import com.sw1.demo.model.DetalleVentaServicio;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleVentaServicioRepository extends MongoRepository<DetalleVentaServicio, String> {
	
	List<DetalleVentaServicio> findByNotaVentaId(String notaVentaId);
}