package com.sw1.demo.clientews.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class Ordenes {
	private String estado;
	private String codSeguimiento;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private String fechaInicio;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private String fechaFin;

	private String codigoEmpresa;
	private String codigoCliente;
	private String codigoEmpleado;
	private String codigoServicio;
}
