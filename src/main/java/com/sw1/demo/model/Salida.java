package com.sw1.demo.model;

import java.sql.Date;
import java.time.LocalTime;

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
	private Integer id;
    private Date fecha;
    private String motivo;
    private LocalTime hora;
}
