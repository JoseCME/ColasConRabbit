package model;

public class Detalle {
	private String nombreBeneficiario;
	private String tipoTransferencia;
	private String descripcion;
	private Referencias referencias;

	public Detalle(String nombreBeneficiario, String tipoTransferencia, String descripcion, Referencias referencias) {
		this.nombreBeneficiario = nombreBeneficiario;
		this.tipoTransferencia = tipoTransferencia;
		this.descripcion = descripcion;
		this.referencias = referencias;
	}

	public Detalle() {

	}

	public String getNombreBeneficiario() {
		return nombreBeneficiario;
	}

	public String getTipoTransferencia() {
		return tipoTransferencia;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public Referencias getReferencias() {
		return referencias;
	}
}
