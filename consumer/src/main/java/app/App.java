package app;

import consumer.TransaccionConsumer;

public class App {
public static void main(String[] args) {
	try {
		TransaccionConsumer.escuchar("BANRURAL");
		TransaccionConsumer.escuchar("GYT");
		TransaccionConsumer.escuchar("BAC");
		TransaccionConsumer.escuchar("BI");
	} catch (Exception e) {
		 e.printStackTrace();
	}
	
	
}
	

}
