package com.sw1.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "notaVenta")
public class NotaVenta {
    @Id
    private String id;
    private String fecha;
    private String hora;
    private Double total;
    private Double saldo;
    private Double interes;
    private Double subtotal;
    private String codigoSeguimiento;
    private String salidaId;
    private String clienteId;
}