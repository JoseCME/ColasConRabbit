package model;

public class Transaccion {
	private String idTransaccion;
	private double monto;
	private String moneda;
	private String cuentaOrigen;
	private String bancoDestino;
	private Detalle detalle;
	
	public Transaccion(String idTransaccion,double monto,String moneda,String cuentaOrigen,String bancoDestino,Detalle detalle) {
		
		this.idTransaccion = idTransaccion;
		this.monto = monto;
		this.moneda = moneda;
		this.cuentaOrigen = cuentaOrigen;
		this.bancoDestino = bancoDestino;
		this.detalle = detalle;
		
	}
	public Transaccion() {
		
		
	}
	
	public String getIdTransaccion() {return idTransaccion; }
	public double getMonto() {return monto; }
	public String getMoneda() {return moneda; }
	public String getCuentaOrigen() {return cuentaOrigen; }
	public String getBancoDestino() {return bancoDestino; }
	public Detalle getDetalle () {return detalle;}
	

}
