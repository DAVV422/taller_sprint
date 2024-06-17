package com.sw1.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.sw1.demo.model.DetalleDevolucion;
import java.util.List;

@Repository
public interface DetalleDevolucionRepository extends MongoRepository<DetalleDevolucion, String> {
    List<DetalleDevolucion> findByNotaDevolucionId(String notaDevolucionId);
}
