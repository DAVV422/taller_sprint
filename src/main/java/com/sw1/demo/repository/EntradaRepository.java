package com.sw1.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.sw1.demo.model.Entrada;

@Repository
public interface EntradaRepository extends MongoRepository<Entrada, String> {
    // No additional methods required
}

