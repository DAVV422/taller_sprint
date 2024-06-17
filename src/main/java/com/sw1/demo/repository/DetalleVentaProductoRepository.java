package com.sw1.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.sw1.demo.model.DetalleVentaProducto;
import java.util.List;

@Repository
public interface DetalleVentaProductoRepository extends MongoRepository<DetalleVentaProducto, String> {
    List<DetalleVentaProducto> findByNotaVentaId(String notaVentaId);
}
