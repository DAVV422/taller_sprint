package com.sw1.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "vehiculo")
public class Vehiculo {
    @Id
    private String id;
    private String matricula;
    private String color;
    private String descripcion;
    private String modelo;
    private String marcaId;
}