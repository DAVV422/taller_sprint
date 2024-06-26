package com.sw1.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "servicio")
public class Servicio {
    @Id
    private String id;
    private String nombre;
    private String descripcion;
    private String tipo;
    private Double tarifaBase;
    private String categoriaIA;
}