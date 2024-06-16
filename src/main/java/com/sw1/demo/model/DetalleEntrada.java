package com.sw1.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "detalleEntrada")
public class DetalleEntrada {

    @Id
    private String id;
    private Integer cantidad;
    private String productoId;
    private String notaEntradaId;
}
