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
	public static final String url_dashboard = prop.getProperty("url.servicio.dashboard");

}
