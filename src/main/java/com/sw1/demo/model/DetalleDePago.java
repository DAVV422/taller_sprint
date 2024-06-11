package com.sw1.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "detallesDePago")
public class DetalleDePago {
    @Id
    private String id;
    private String fechaPago;
    private Double monto;
    private String estadoPago;
    private String planDePagoId;
}