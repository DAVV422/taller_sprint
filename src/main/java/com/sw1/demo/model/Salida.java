package com.sw1.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "salida")
public class Salida {
    
	@Id
	private String id;
    private String fecha;
    private String motivo;
    private String hora;
    private String productoId;
}
