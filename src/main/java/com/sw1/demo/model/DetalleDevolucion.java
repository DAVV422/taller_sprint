package com.sw1.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "detalleDevolucion")
public class DetalleDevolucion {

    @Id
    private String id;
    private Integer cantidad;
    private Double monto;
    private String productoId;
    private String notaDevolucionId;
}
