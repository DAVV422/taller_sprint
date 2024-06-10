package com.sw1.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "planesDePago")
public class PlanDePago {
    @Id
    private String id;
    private String fechaInicio;
    private int cantidadCuotas;
    private Double montoCuota;
    private String tiempoCuota;
    private String tipo; // "credito" o "al contado"
}