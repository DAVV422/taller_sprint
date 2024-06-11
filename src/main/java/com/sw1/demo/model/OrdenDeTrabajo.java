package com.sw1.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "ordenDeTrabajo")
public class OrdenDeTrabajo {
    @Id
    private String id;
    private String fechaInicio;
    private String fechaFin;
    private String estado;
    private String observacion;
    private String personalId;
    private String detalleVentaServicioId;
}