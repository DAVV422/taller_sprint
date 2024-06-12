package com.sw1.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "detalleVentaServicio")
public class DetalleVentaServicio {
    @Id
    private String id;
    private Double monto;
    private String servicioId;
    private String notaVentaId;
}