package com.sw1.demo.clientews;

import com.sw1.demo.clientews.model.Clientes;

public class PruebaTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//PropertyConfigurator.configure("log4j.properties");
		ClienteWs client=new ClienteWs();
		Clientes body=new Clientes();
		body.setCodigoEmpresa("10");
		body.setIdERP("10");
		body.setNombre("prueba");
		try {
			client.createClientes(body);
		}catch (Exception e) {
			// TODO: handle exception
			System.out.print("fallo"+e);
		}
		System.out.println("exitos");
	}

}
