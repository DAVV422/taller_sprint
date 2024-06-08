package com.sw1.demo.repository;

import com.sw1.demo.model.Marca;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarcaRepository extends MongoRepository<Marca, String> {
}
