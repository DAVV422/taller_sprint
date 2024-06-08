package com.sw1.demo.repository;

import com.sw1.demo.model.NotaVenta;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotaVentaRepository extends MongoRepository<NotaVenta, String> {
}