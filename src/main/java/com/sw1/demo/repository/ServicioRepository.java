package com.sw1.demo.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.sw1.demo.model.Servicio;

@Repository
public interface ServicioRepository extends MongoRepository<Servicio, String> {
}
