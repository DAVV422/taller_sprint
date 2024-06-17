package com.sw1.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "personal")
public class Personal {

	@Id
	private String id;
	private String nombre;
	private String apellido;
	private String ci;
	private String direccion;
	private String celular;
	private String fechaNacimiento;	
	private String usuarioId;
	
}
