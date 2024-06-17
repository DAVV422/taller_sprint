package com.sw1.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "notas_devolucion")
public class NotaDevolucion {

    @Id
    private String id;
    private String fecha;
    private String motivo;
    private Double montoTotal;
    private String notaVentaId;
}

