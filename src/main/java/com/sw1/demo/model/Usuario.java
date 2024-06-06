package com.sw1.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "usuario")
public class Usuario {
	@Id
	private String id;
    private String nombreUsuario;
    private String email;
    private String password;
    private String tipo;
    
}
