package com.sw1.demo.clientews;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.net.ssl.SSLException;

import org.apache.hc.client5.http.HttpRequestRetryStrategy;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.entity.UrlEncodedFormEntity;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpRequest;
import org.apache.hc.core5.http.HttpResponse;
import org.apache.hc.core5.http.HttpStatus;
import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.apache.hc.core5.http.message.BasicClassicHttpRequest;
import org.apache.hc.core5.http.message.BasicNameValuePair;
import org.apache.hc.core5.http.protocol.HttpContext;
import org.apache.hc.core5.http.protocol.HttpCoreContext;
import org.apache.hc.core5.util.TimeValue;
import org.apache.hc.core5.util.Timeout;
import org.apache.http.HttpHeaders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sw1.demo.clientews.model.Clientes;
import com.sw1.demo.clientews.model.Empleados;
import com.sw1.demo.clientews.model.Empresa;
import com.sw1.demo.clientews.model.Ordenes;
import com.sw1.demo.clientews.model.ResponseLogin;
import com.sw1.demo.clientews.model.Servicios;
import com.sw1.demo.propertie.ConfigPropertie;

public class ClienteWs {

	public static final int NUMERO_MAXIMO_REINTENTOS = 2;
	public static final String PREFIX = "Bearer ";
	public static final String HEADER_KEY_AUTHENTICATION = "Authorization";

	private CloseableHttpClient client;
	// private ResponseHandler<String> responseHandler;
	private ObjectMapper mapper;
	private static final Map<String, Boolean> idempotentMethods = new ConcurrentHashMap<>();

	static {
		idempotentMethods.put("GET", Boolean.TRUE);
		idempotentMethods.put("HEAD", Boolean.TRUE);
		idempotentMethods.put("PUT", Boolean.TRUE);
		idempotentMethods.put("DELETE", Boolean.TRUE);
		idempotentMethods.put("OPTIONS", Boolean.TRUE);
		idempotentMethods.put("TRACE", Boolean.TRUE);
	}

	/*
	 * private static DefaultHttpRequestRetryStrategy makeRequestWithRetries() {
	 * return new DefaultHttpRequestRetryStrategy(NUMERO_MAXIMO_REINTENTOS,
	 * Timeout.ofSeconds(1)); }
	 */
	public ClienteWs() {
		// TODO Auto-generated constructor stub
		this.client = HttpClients.createDefault();
		mapper = new ObjectMapper();
	}

	public ClienteWs(int connectionRequestTimeout, int connectionTimeout, int socketTimeout) {
		client = HttpClients.custom()
				.setDefaultRequestConfig(RequestConfig.custom()
						.setConnectionRequestTimeout(Timeout.ofMilliseconds(connectionRequestTimeout))
						.setConnectTimeout(Timeout.ofMilliseconds(connectionTimeout))
						.setResponseTimeout(Timeout.ofMilliseconds(socketTimeout)).build())
				.setRetryStrategy(makeRequestWithRetries()).build();

		// log.info("Conexion con verificacion https");
		mapper = new ObjectMapper();
	}

	public void cerrarConexion() {
		this.mapper = null;
		// this.client = null;
	}

	public String executeGet(String url) throws IOException, ParseException {
		HttpGet httpGet = new HttpGet(url);
		try (CloseableHttpResponse response = client.execute(httpGet)) {
			return EntityUtils.toString(response.getEntity());
		}
	}

	public String executePost(String url, String json) throws IOException, ParseException {
		HttpPost httpPost = new HttpPost(url);
		httpPost.setEntity(new StringEntity(json));
		httpPost.setHeader("Content-Type", "application/json");
		try (CloseableHttpResponse response = client.execute(httpPost)) {
			return EntityUtils.toString(response.getEntity());
		}
	}

	private static HttpRequestRetryStrategy makeRequestWithRetries() {
		return new HttpRequestRetryStrategy() {

			@Override
			public boolean retryRequest(HttpRequest request, IOException exception, int executionCount,
					HttpContext context) {
				HttpCoreContext clientContext = HttpCoreContext.adapt(context);

				if (clientContext == null) {
					return false;
				}

				if (request == null) {
					return false;
				}

				StringBuilder sb = new StringBuilder("ExecutionCount " + executionCount + ", ");

				sb.append("header { ");
				final int size = request.getHeaders().length;
				for (int i = 0; i < size; i++) {
					sb.append("(").append(request.getHeaders()[i]).append(") ");
				}
				sb.append("}");

				sb.append("-> metodo : ").append(request.getMethod());
				try {
					sb.append(", uri : ").append(request.getUri());
				} catch (URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				HttpResponse response = clientContext.getResponse();

				if (response != null) {
					try {
						sb.append(EntityUtils.toString(((BasicClassicHttpRequest) response).getEntity()));
					} catch (IOException | ParseException ex) {
						sb.append(", Error al obtener la respuesta ").append(ex.getMessage());
					}
				}

				if (executionCount >= NUMERO_MAXIMO_REINTENTOS) {
					sb.append(", Numero de reintentos superado ").append(executionCount);
					return false;
				}
				if (exception instanceof InterruptedIOException) {
					sb.append(", Timeout : ").append(exception.getMessage());
				}
				if (exception instanceof UnknownHostException) {
					sb.append(", Unknown host : ").append(exception.getMessage());
				}
				if (exception instanceof SSLException) {
					sb.append(", SSL handshake exception : ").append(exception.getMessage());
				}

				// log.warn(sb.toString());

				final String method = request.getMethod().toUpperCase(Locale.ENGLISH);
				return idempotentMethods.getOrDefault(method, false);
			}

			@Override
			public boolean retryRequest(HttpResponse response, int executionCount, HttpContext context) {
				return executionCount < NUMERO_MAXIMO_REINTENTOS
						&& response.getCode() >= HttpStatus.SC_INTERNAL_SERVER_ERROR;
			}

			@Override
			public TimeValue getRetryInterval(HttpResponse response, int executionCount, HttpContext context) {
				return TimeValue.ofSeconds(1);
			}
		};
	}

	public void clienteGet() throws IOException, ParseException {
		HttpGet request = new HttpGet("");

		try (CloseableHttpResponse response = client.execute(request)) {
			int statusCode = response.getCode();
			String responseBody = EntityUtils.toString(response.getEntity());
			System.out.println("Status Code: " + statusCode);
			System.out.println("Response Body: " + responseBody);

			switch (statusCode) {
			case HttpStatus.SC_OK:
				// Procesar respuesta exitosa
				break;
			case HttpStatus.SC_BAD_REQUEST:
				// Procesar error de solicitud incorrecta
				break;
			case HttpStatus.SC_METHOD_NOT_ALLOWED:
				// Procesar error de método no permitido
				break;
			case HttpStatus.SC_NOT_FOUND:
				// Procesar error de recurso no encontrado
				break;
			default:
				// Procesar otros códigos de estado HTTP
				break;
			}
		}
	}

	public void createClientes(Clientes body) {
		String token = login();
		clientCreateClientes(body,token);
	}
	public void createEmpresas(Empresa body) {
		String token = login();
		clientCreateEmpresas(body,token);
	}
	public void createEmpleados(Empleados body) {
		String token = login();
		clientCreateEmpleados(body,token);
	}
	public void createOrdenes(Ordenes body) {
		String token = login();
		clientCreateOrdenes(body,token);
	}
	public void createServicios(Servicios body) {
		String token = login();
		clientCreateServicios(body,token);
	}
	
	private void clientCreateEmpresas(Empresa body,String token)  {
		//String token = "";
		HttpPost request = new HttpPost(ConfigPropertie.url_createEmpresas);
		request.setHeader("Content-Type", "application/json");
		request.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token);
		String bodyJsonRequest = "";
		try {
			 bodyJsonRequest = mapper.writeValueAsString(body);
		}catch (Exception e) {
			System.out.println("error parseo "+e);
		}
		request.setEntity(new StringEntity(bodyJsonRequest));
		try (CloseableHttpResponse response = client.execute(request)) {
			int statusCode = response.getCode();
			String responseBody = EntityUtils.toString(response.getEntity());
			System.out.println("Status Code: " + statusCode);
			System.out.println("Response Body: " + responseBody);
			switch (response.getCode()) {
			case HttpStatus.SC_OK:
				System.out.println("exitoso");
				break;
			case HttpStatus.SC_BAD_REQUEST:
				System.out.println("problema request cuerpo");
				break;
			case HttpStatus.SC_METHOD_NOT_ALLOWED:
				System.out.println("problema en el metodo");
				break;
			case HttpStatus.SC_NOT_FOUND:
				System.out.println("not found");
				break;
			default:
				System.out.println("fallo causa desconocida");
				break;
			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("error " + e);
		}
	}

	private void clientCreateEmpleados(Empleados body,String token)  {
		//String token = "";
		
		HttpPost request = new HttpPost(ConfigPropertie.url_createEmpleados);
		request.setHeader("Content-Type", "application/json");
		request.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token);
		String bodyJsonRequest = "";
		try {
			 bodyJsonRequest = mapper.writeValueAsString(body);
		}catch (Exception e) {
			System.out.println("error parseo "+e);
		}
		request.setEntity(new StringEntity(bodyJsonRequest));
		try (CloseableHttpResponse response = client.execute(request)) {
			int statusCode = response.getCode();
			String responseBody = EntityUtils.toString(response.getEntity());
			System.out.println("Status Code: " + statusCode);
			System.out.println("Response Body: " + responseBody);
			switch (response.getCode()) {
			case HttpStatus.SC_OK:
				System.out.println("exitoso");
				break;
			case HttpStatus.SC_BAD_REQUEST:
				System.out.println("problema request cuerpo");
				break;
			case HttpStatus.SC_METHOD_NOT_ALLOWED:
				System.out.println("problema en el metodo");
				break;
			case HttpStatus.SC_NOT_FOUND:
				System.out.println("not found");
				break;
			default:
				System.out.println("fallo causa desconocida");
				break;
			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("error " + e);
		}
	}

	private void clientCreateServicios(Servicios body,String token)  {
		//String token = "";
		HttpPost request = new HttpPost(ConfigPropertie.url_createServicios);
		request.setHeader("Content-Type", "application/json");
		request.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token);
		String bodyJsonRequest = "";
		try {
			 bodyJsonRequest = mapper.writeValueAsString(body);
		}catch (Exception e) {
			System.out.println("error parseo "+e);
		}
		request.setEntity(new StringEntity(bodyJsonRequest));
		try (CloseableHttpResponse response = client.execute(request)) {
			int statusCode = response.getCode();
			String responseBody = EntityUtils.toString(response.getEntity());
			System.out.println("Status Code: " + statusCode);
			System.out.println("Response Body: " + responseBody);
			switch (response.getCode()) {
			case HttpStatus.SC_OK:
				System.out.println("exitoso");
				break;
			case HttpStatus.SC_BAD_REQUEST:
				System.out.println("problema request cuerpo");
				break;
			case HttpStatus.SC_METHOD_NOT_ALLOWED:
				System.out.println("problema en el metodo");
				break;
			case HttpStatus.SC_NOT_FOUND:
				System.out.println("not found");
				break;
			default:
				System.out.println("fallo causa desconocida");
				break;
			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("error " + e);
		}
	}

	private void clientCreateOrdenes(Ordenes body,String token)  {
		//String token = "";
		HttpPost request = new HttpPost(ConfigPropertie.url_createOrdenes);
		request.setHeader("Content-Type", "application/json");
		request.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token);
		String bodyJsonRequest = "";
		try {
			 bodyJsonRequest = mapper.writeValueAsString(body);
		}catch (Exception e) {
			System.out.println("error parseo "+e);
		}
		request.setEntity(new StringEntity(bodyJsonRequest));
		try (CloseableHttpResponse response = client.execute(request)) {
			int statusCode = response.getCode();
			String responseBody = EntityUtils.toString(response.getEntity());
			System.out.println("Status Code: " + statusCode);
			System.out.println("Response Body: " + responseBody);
			switch (response.getCode()) {
			case HttpStatus.SC_OK:
				System.out.println("exitoso");
				break;
			case HttpStatus.SC_BAD_REQUEST:
				System.out.println("problema request cuerpo");
				break;
			case HttpStatus.SC_METHOD_NOT_ALLOWED:
				System.out.println("problema en el metodo");
				break;
			case HttpStatus.SC_NOT_FOUND:
				System.out.println("not found");
				break;
			default:
				System.out.println("fallo causa desconocida");
				break;
			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("error " + e);
		}
	}

	
	private void clientCreateClientes(Clientes body,String token)  {
		//String token = "";
		HttpPost request = new HttpPost(ConfigPropertie.url_createClientes);
		request.setHeader("Content-Type", "application/json");
		request.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token);
		String bodyJsonRequest = "";
		try {
			 bodyJsonRequest = mapper.writeValueAsString(body);
		}catch (Exception e) {
			System.out.println("error parseo "+e);
		}
		request.setEntity(new StringEntity(bodyJsonRequest));
		try (CloseableHttpResponse response = client.execute(request)) {
			int statusCode = response.getCode();
			String responseBody = EntityUtils.toString(response.getEntity());
			System.out.println("Status Code: " + statusCode);
			System.out.println("Response Body: " + responseBody);
			switch (response.getCode()) {
			case HttpStatus.SC_OK:
				System.out.println("exitoso");
				break;
			case HttpStatus.SC_BAD_REQUEST:
				System.out.println("problema request cuerpo");
				break;
			case HttpStatus.SC_METHOD_NOT_ALLOWED:
				System.out.println("problema en el metodo");
				break;
			case HttpStatus.SC_NOT_FOUND:
				System.out.println("not found");
				break;
			default:
				System.out.println("fallo causa desconocida");
				break;
			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("error " + e);
		}
	}

	public String login() {
		ResponseLogin entity = new ResponseLogin();
		HttpPost request = new HttpPost(ConfigPropertie.url_login);
		List<NameValuePair> params = new ArrayList<>();
		params.add(new BasicNameValuePair("client_id", ""));
		params.add(new BasicNameValuePair("client_secret", ""));
		params.add(new BasicNameValuePair("grant_type", ""));
		params.add(new BasicNameValuePair("name", ""));
		params.add(new BasicNameValuePair("username",ConfigPropertie.url_login_usuario ));
		params.add(new BasicNameValuePair("password", ConfigPropertie.url_login_password));
		request.addHeader("content-type", "application/x-www-form-urlencoded");
		request.setEntity(new UrlEncodedFormEntity(params));

		try (CloseableHttpResponse response = client.execute(request)) {
			int statusCode = response.getCode();
			String responseBody = EntityUtils.toString(response.getEntity());
			System.out.println("Status Code: " + statusCode);
			System.out.println("Response Body: " + responseBody);
			switch (response.getCode()) {
			case HttpStatus.SC_OK:
				entity = mapper.readValue(responseBody, ResponseLogin.class);
				System.out.println("exitoso");
				break;
			case HttpStatus.SC_BAD_REQUEST:
				System.out.println("problema request cuerpo");
				break;
			case HttpStatus.SC_METHOD_NOT_ALLOWED:
				System.out.println("problema en el metodo");
				break;
			case HttpStatus.SC_NOT_FOUND:
				System.out.println("not found");
				break;
			default:
				System.out.println("fallo causa desconocida");
				break;
			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("error " + e);
		}
		return entity.getAccess_token();
	}
}
