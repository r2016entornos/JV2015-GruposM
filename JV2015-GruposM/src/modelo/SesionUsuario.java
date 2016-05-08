package modelo;


import util.Fecha;

/** 
 * Proyecto: Juego de la vida.
 *  Implementa el concepto de SesionUsuario según el modelo1
 *  En esta versión no se han aplicado la mayoría de los estándares 
 *  de diseño OO dirigidos a conseguir un "código limpio". 
 *  @since: prototipo1.0
 *  @source: SesionUsuario.java 
 *  @version: 2.0 - 11/04/2016 
 *  @author: ajp
 */

public class SesionUsuario {
	
	// Atributos	
	private Usuario usr;   // Concreta la relación de composición del modelo UML
	private Fecha fecha; 
	
	// Constructores
	
	/**
	 * @param usr
	 * @param fecha
	 */
	public SesionUsuario(Usuario usr, Fecha fecha) {
		setUsr(usr);
		setFecha(fecha);
	}
	
	public SesionUsuario(){
		this(new Usuario(), new Fecha());
	}

	public SesionUsuario(SesionUsuario su){
		this(new Usuario(su.usr), new Fecha(su.fecha));
	}
	
	
	// Métodos de acceso
	
	public Usuario getUsr() {
		return usr;
	}
	
	public Fecha getFecha() {
		return fecha;
	}

	public void setUsr(Usuario usr) {
		assert usr != null;
		this.usr = usr;
	}
	
	public void setFecha(Fecha fecha) {
		assert fechaSesionValida(fecha);
		this.fecha = fecha;
	}

	/**
	 * Comprueba validez de una fecha.
	 * @param fecha.
	 * @return true si cumple.
	 */
	private boolean fechaSesionValida(Fecha fecha) {
		if (fecha != null
				&& fechaSesionCoherente(fecha)) {
			return true;
		}
		return false;
	}
	
	/**
	 * Comprueba coherencia de una fecha de sesión.
	 * @param fecha.
	 * @return true si cumple.
	 */
	private boolean fechaSesionCoherente(Fecha fecha) {
		// Comprueba que fechaSesion no es, por ejemplo, del futuro
		// --Pendiente--
		return true;
	}
	
	// Métodos redefinidos
	
	/**
	 * Redefine el método heredado de la clase Objecto.
	 * @return el texto formateado del estado (valores de atributos) 
	 * del objeto de la clase SesionUsuario  
	 */
	@Override
	public String toString() {
		return  "\n" + usr
				+ String.format("\n fecha: \t\t%s", fecha);
	}

} // class
