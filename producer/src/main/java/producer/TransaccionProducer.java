	package producer;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import java.util.List;
import connection.RabbitMQConexion;
import model.Transaccion;

public class TransaccionProducer {
	private static final Set<String> BANCOS_VALIDOS = Set.of("BANRURAL", "GYT", "BAC", "BI");
	private static final Set<String> IDS_PROCESADOS = ConcurrentHashMap.newKeySet();
	
	public static void enviar(List<Transaccion> transacciones) throws Exception {
		Connection connection = RabbitMQConexion.obtenerConexion();
		Channel channel = connection.createChannel();
		ObjectMapper mapper = new ObjectMapper();
		
		for (Transaccion transaccion:transacciones) {
			String cola = transaccion.getBancoDestino();
			String id = transaccion.getIdTransaccion();
			if (!IDS_PROCESADOS.add(id)) {
			    System.out.println("Transaccion duplicada, se omite: " + id);
			    continue;
			}
			if (!BANCOS_VALIDOS.contains(cola)) {
			    System.out.println("Banco no soportado, se omite: " + cola);
			    continue;
			}
			channel.queueDeclare(cola, true, false, false, null);
			String mensaje = mapper.writeValueAsString(transaccion);
			channel.basicPublish("", cola, null, mensaje.getBytes());
			System.out.println("Enviado a cola [" + cola + "]: " + transaccion.getIdTransaccion());
		}
		channel.close();
		connection.close();
	}

}
