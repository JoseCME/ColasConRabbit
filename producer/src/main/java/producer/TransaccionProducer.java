	package producer;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import connection.RabbitMQConexion;
import model.Transaccion;

public class TransaccionProducer {
	private static final Set<String> BANCOS_VALIDOS = Set.of("BANRURAL", "GYT", "BAC", "BI");
	private static final Set<String> IDS_PROCESADOS = ConcurrentHashMap.newKeySet();
	
	public static void enviar(List<Transaccion> transacciones) throws Exception {
		Connection connection = RabbitMQConexion.obtenerConexion();
		Channel channel = connection.createChannel();
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> argumentos = new HashMap<>();
		
		for (Transaccion transaccion:transacciones) {
			String cola = transaccion.getBancoDestino();
			String id = transaccion.getIdTransaccion();
			double monto = transaccion.getMonto();
			if (!IDS_PROCESADOS.add(id)) {
			    System.out.println("Transaccion duplicada, se omite: " + id);
			    continue;
			}
			if (!BANCOS_VALIDOS.contains(cola)) {
			    System.out.println("Banco no soportado, se omite: " + cola);
			    continue;
			}
			if (monto > 4000) {
				channel.queueDeclare(cola, true, false, false, null);
				String mensaje = mapper.writeValueAsString(transaccion);
				channel.basicPublish("", cola, null, mensaje.getBytes());
				
				System.out.println("Se aprobo la cola " + transaccion.getIdTransaccion() + "con monto" + transaccion.getMonto());
			}else {
				String mensaje = mapper.writeValueAsString(transaccion);
				channel.queueDeclare("cola_Rechazados", true, false, false, null);
				channel.basicPublish("", "cola_Rechazados", null, mensaje.getBytes());
				System.out.println("Se rechazo la cola "+ transaccion.getIdTransaccion()+" con "+ transaccion.getMonto());
			}
			}
			
		channel.close();
		connection.close();
	}

}
