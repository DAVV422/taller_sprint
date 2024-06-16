package com.sw1.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "detalleCompra")
public class DetalleCompra {

    @Id
    private String id;
    private Double monto;
    private Integer cantidad;
    private String productoId;
    private String notaCompraId;
}
