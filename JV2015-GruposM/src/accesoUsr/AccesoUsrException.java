/** 
 * Proyecto: Juego de la vida.
 *  jv2014.ajp
 * Maneja los errores de acceso a datos.
 *  @since: prototipo2
 *  @source: AccesoUsrException.java 
 * @version: 2.2 - 18/05/2015 
 *  @author: ajp
 */

package accesoUsr;

public class AccesoUsrException extends Exception {

	/**
	 * Excepción por defecto sin mensaje
	 */
	public AccesoUsrException() {
		super();
	}
	
	/**
	 * Excepción con mensaje
	 * @param msg - el mensaje de error asociado
	 */
	public AccesoUsrException(String msg) {
		super(msg);
	}

}
