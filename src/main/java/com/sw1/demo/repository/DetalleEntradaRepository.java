package com.sw1.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.sw1.demo.model.DetalleEntrada;
import java.util.List;

@Repository
public interface DetalleEntradaRepository extends MongoRepository<DetalleEntrada, String> {
    List<DetalleEntrada> findByNotaEntradaId(String notaEntradaId);
}
