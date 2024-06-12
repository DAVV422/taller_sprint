package com.sw1.demo.repository;

import com.sw1.demo.model.DetalleDePago;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetalleDePagoRepository extends MongoRepository<DetalleDePago, String> {
    List<DetalleDePago> findByPlanDePagoId(String planDePagoId);
}
