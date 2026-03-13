package app;

import java.util.List;

import model.Transaccion;
import producer.TransaccionProducer;
import service.TransaccionService;

public class App {

	public App() {
		
	}

	public static void main(String[] args) {
		try {
		    List<Transaccion> transacciones = TransaccionService.obtenerTransacciones();
		    TransaccionProducer.enviar(transacciones);
		} catch (Exception e) {
		    e.printStackTrace();
		}
	}

}
