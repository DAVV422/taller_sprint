package com.sw1.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "detalleVentaProducto")
public class DetalleVentaProducto {

    @Id
    private String id;
    private Double precioUnitario;
    private Integer cantidad;
    private Double montoTotal;
    private String notaVentaId;
    private String productoId;
}
