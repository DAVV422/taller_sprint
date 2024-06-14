package com.sw1.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.sw1.demo.model.NotaCompra;

@Repository
public interface NotaCompraRepository extends MongoRepository<NotaCompra, String> {
    // No additional methods required
}

