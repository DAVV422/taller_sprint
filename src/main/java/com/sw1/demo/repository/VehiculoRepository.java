package com.sw1.demo.repository;

import com.sw1.demo.model.Vehiculo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehiculoRepository extends MongoRepository<Vehiculo, String> {
	List<Vehiculo> findByMatricula(String matricula);
}