package com.sw1.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "proveedor")
public class Proveedor {
    @Id
    private Integer id;
    private String nombre;
    private String direccion;
    private String celular;
    private String email;
    private String descripcion;
}
