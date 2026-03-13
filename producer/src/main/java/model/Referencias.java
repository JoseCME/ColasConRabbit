package model;

public class Referencias {
	private String factura;
	private String codigoInterno;

	public Referencias(String factura, String codigoInterno) {
		this.factura = factura;
		this.codigoInterno = codigoInterno;
	}

	public Referencias() {

	}

	public String getFactura() {
		return factura;
	}

	public String getCodigoInterno() {
		return codigoInterno;
	}
}
