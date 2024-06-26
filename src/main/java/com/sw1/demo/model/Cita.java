package com.sw1.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "cita")
public class Cita {
    @Id
    private String id;
    private String fecha;
    private String hora;
    private String estado;
    private String usuarioId;
    private String personalId;
}
