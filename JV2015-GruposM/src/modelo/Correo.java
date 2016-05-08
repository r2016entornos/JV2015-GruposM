package modelo;


import util.Formato;

public class Correo {
	
	private String texto;
	
	public Correo(String texto) {
		setTexto(texto);
	}

	public Correo() {
		this("correo@correo.com");
	}

	public Correo(Correo correo) {
		this(correo.texto);
	}
	
	public void setTexto(String texto) {
		assert textoValido(texto);
		this.texto = texto;
	}
	
	/**
	 * Comprueba validez de una dirección de correo.
	 * @param texto.
	 * @return true si cumple.
	 */
	private boolean textoValido(String texto) {
		if (texto != null 
				&& util.Formato.validar(texto, Formato.PATRON_CORREO)
				&& correoAutentico(texto)) {
			return true;
		}
		return false;
	}

	/**
	 * Comprueba que una dirección de correo existe.
	 * @return true si cumple.
	 */
	public boolean correoAutentico(String texto) {
		// Comprueba que el correo no es falso
		// --Pendiente--
		return true;
	}

	public String getTexto() {
		return texto;
	}
	
	@Override
	public String toString() {
		return texto;
	}
	
} // class
