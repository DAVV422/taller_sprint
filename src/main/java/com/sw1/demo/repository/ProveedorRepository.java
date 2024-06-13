package com.sw1.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.sw1.demo.model.Proveedor;

@Repository
public interface ProveedorRepository extends MongoRepository<Proveedor, String> {
}
