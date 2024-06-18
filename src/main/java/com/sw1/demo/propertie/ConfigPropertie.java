package com.sw1.demo.propertie;

import java.io.InputStream;
import java.util.Properties;

public class ConfigPropertie {

	private static final Properties prop = new Properties();
	
		
	//public static final List<Mensajes> messages = new ArrayList<Mensajes>(0);
	
	static {
		InputStream fileMessageStream = null;
		try {
			fileMessageStream = ConfigPropertie.class.getResourceAsStream("/config.properties");
			prop.load(fileMessageStream);
			//LOGGER.info("Archivo de conexion cargado correctamente");
		} catch (Exception e) {
			//LOGGER.fatal("[Fallo al leer el archivo message_validator.properties]", e);
			System.exit(-3);
		} finally {
			try {
				if (fileMessageStream != null) {
					fileMessageStream.close();
				}
			} catch (Exception e) {
				//LOGGER.error("Error al cerrar el archivo de propiedades", e);
			}
		}
	}
	/*
	 * ==================== [INICIO] Registros genericos ====================
	 */
	//Mensajes
	public static final String url_login = prop.getProperty("url.login");
	public static final String url_login_usuario = prop.getProperty("url.login.username");
	public static final String url_login_password = prop.getProperty("url.login.pass");
	
	public static final String url_createClientes = prop.getProperty("url.createClientes");
	public static final String url_createEmpresas = prop.getProperty("url.createEmpresas");
	public static final String url_createOrdenes = prop.getProperty("url.createOrdenes");
	public static final String url_createEmpleados = prop.getProperty("url.createEmpleados");
	public static final String url_createServicios = prop.getProperty("url.createServicios");
}
