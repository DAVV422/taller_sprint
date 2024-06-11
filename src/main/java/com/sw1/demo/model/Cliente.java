package com.sw1.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "cliente")
public class Cliente {
    @Id
    private String id;
    private String nombre;
    private String apellido;
    private String celular;
    private String nit;
    private String usuarioId;
}