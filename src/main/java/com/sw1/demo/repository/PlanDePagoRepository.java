package com.sw1.demo.repository;

import com.sw1.demo.model.PlanDePago;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanDePagoRepository extends MongoRepository<PlanDePago, String> {
}