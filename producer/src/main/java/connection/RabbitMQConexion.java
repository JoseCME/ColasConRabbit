package connection;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class RabbitMQConexion {
	
	
	
	public static Connection  obtenerConexion()throws Exception  {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost(System.getenv().getOrDefault("RABBIT_HOST", "localhost"));
		factory.setPort(Integer.parseInt(System.getenv().getOrDefault("RABBIT_PORT", "5672")));
		factory.setUsername(System.getenv().getOrDefault("RABBIT_USER", "Josecm"));
		factory.setPassword(System.getenv().getOrDefault("RABBIT_PASSWORD", "admin"));
		
		return factory.newConnection();

	}

}
