package com.sw1.demo.repository;

import com.sw1.demo.model.Cita;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CitaRepository extends MongoRepository<Cita, String> {
}
