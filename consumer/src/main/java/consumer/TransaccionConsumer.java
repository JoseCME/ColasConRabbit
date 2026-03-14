package consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DeliverCallback;
import model.Transaccion;
import service.TransaccionService;

import connection.RabbitMQConexion;

public class TransaccionConsumer {

	public static void escuchar(String nombreCola) throws Exception {
		Connection connection = RabbitMQConexion.obtenerConexion();
		Channel channel = connection.createChannel();
		ObjectMapper mapper = new ObjectMapper();
		channel.basicQos(3);
		DeliverCallback callback = (consumerTag, delivery) -> {
		    try {
		        String mensajeStr = new String(delivery.getBody());
		        Transaccion transaccion = mapper.readValue(mensajeStr, Transaccion.class);
		        boolean exito = TransaccionService.enviarTransaccion(transaccion);
		        if (transaccion.getMonto() < 4000) {
		        	
		        	  System.out.println(" Se RECHAZA "+ transaccion.getIdTransaccion() + " monto " + transaccion.getMonto());
		        	  
		        }else if(exito) {
		        	channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
		            System.out.println(" Se APROBO: " + transaccion.getIdTransaccion()+ " monto " + transaccion.getMonto());
		         
		        }else {
		        	 System.out.println(" Error POST, no se hace ACK: " + transaccion.getIdTransaccion());
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		};
		channel.basicConsume(nombreCola, false, callback, consumerTag -> {});
	}

}
