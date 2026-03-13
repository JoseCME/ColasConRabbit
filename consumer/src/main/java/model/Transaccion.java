package model;

public class Transaccion {
	private String nombre = "Jose Carlos Morataya Enrique";
	private String carnet = "0905-24-7370";
	private String idTransaccion;
	private double monto;
	private String moneda;
	private String cuentaOrigen;
	private String bancoDestino;
	private Detalle detalle;
	
	public Transaccion(String carnet,String nombre,String idTransaccion,double monto,String moneda,String cuentaOrigen,String bancoDestino,Detalle detalle) {
		
		this.idTransaccion = idTransaccion;
		this.monto = monto;
		this.moneda = moneda;
		this.cuentaOrigen = cuentaOrigen;
		this.bancoDestino = bancoDestino;
		this.detalle = detalle;
		this.nombre = nombre;
		this.carnet = carnet;
		
	}
	public Transaccion() {
		
		
	}
	 
	public String getNombre() {return nombre; }
	public String getCarnet() {return carnet; }
	public String getIdTransaccion() {return idTransaccion; }
	public double getMonto() {return monto; }
	public String getMoneda() {return moneda; }
	public String getCuentaOrigen() {return cuentaOrigen; }
	public String getBancoDestino() {return bancoDestino; }
	public Detalle getDetalle () {return detalle;}
	
	public void setNombre(String nombre) { this.nombre = nombre; }
	public void setCarnet(String carnet) { this.carnet = carnet; }
	public void setIdTransaccion(String idTransaccion) {this.idTransaccion = idTransaccion + "-" + "0905-24-7370";}
	public void setMonto(double monto) { this.monto = monto; }
	public void setMoneda(String moneda) { this.moneda = moneda; }
	public void setCuentaOrigen(String cuentaOrigen) { this.cuentaOrigen = cuentaOrigen; }
	public void setBancoDestino(String bancoDestino) { this.bancoDestino = bancoDestino; }
	public void setDetalle(Detalle detalle) { this.detalle = detalle; }
	

}
