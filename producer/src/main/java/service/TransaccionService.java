package service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import java.util.List;
import java.util.ArrayList;
import model.Transaccion;

public class TransaccionService {
	private static final String API_URL = System.getenv()
		    .getOrDefault("API_GET_URL", "https://hly784ig9d.execute-api.us-east-1.amazonaws.com/default/transacciones");
	public static List<Transaccion> obtenerTransacciones() throws Exception {
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder()
		.uri(URI.create(API_URL))
		.timeout(Duration.ofSeconds(20))
		.GET()
		.build();
		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
		if (response.statusCode() != 200) {
		    throw new Exception("Error al obtener transacciones, status: " + response.statusCode());
		}
		ObjectMapper mapper = new ObjectMapper();
		JsonNode root = mapper.readTree(response.body());
		JsonNode nodoTransacciones = root.path("transacciones");
		List<Transaccion> lista = new ArrayList<>();
		for (JsonNode nodo : nodoTransacciones) {
		    Transaccion t = mapper.treeToValue(nodo, Transaccion.class);
		    lista.add(t);
		}
		return lista;
	}
	
	
}
