package com.sw1.demo.repository;


import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.sw1.demo.model.DetalleSalida;

@Repository
public interface DetalleSalidaRepository extends MongoRepository<DetalleSalida, String> {
	List<DetalleSalida> findByNotaSalidaId(String notaSalidaId);
}
