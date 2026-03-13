package service;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Transaccion;
public class TransaccionService {
	private static final String API_POST_URL = System.getenv()
		    .getOrDefault("API_POST_URL", "https://7e0d9ogwzd.execute-api.us-east-1.amazonaws.com/default/guardarTransacciones");
	public static boolean enviarTransaccion(Transaccion transaccion) throws Exception {
		HttpClient client = HttpClient.newHttpClient();
		ObjectMapper mapper = new ObjectMapper();
		String jsonBody = mapper.writeValueAsString(transaccion);
		System.out.println("Enviando JSON: " + jsonBody); 
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(API_POST_URL))
				.header("Content-Type", "application/json")
				.POST(HttpRequest.BodyPublishers.ofString(jsonBody))
				.build();
				HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
				if (response.statusCode() != 201) {
					 System.out.println("Status: " + response.statusCode());
					    System.out.println("Respuesta: " + response.body());
				    return false;
				}
				return true;
				
				
	}

}
