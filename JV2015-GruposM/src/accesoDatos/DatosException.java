/** 
 * Proyecto: Juego de la vida.
 *  jv2014.ajp
 * Maneja los errores de acceso a datos.
 *  @since: prototipo2
 *  @source: AccesoDatosException.java 
 * @version: 2.2 - 18/05/2015 
 *  @author: ajp
 */

package accesoDatos;

public class DatosException extends Exception {

	/**
	 * Excepción por defecto sin mensaje
	 */
	public DatosException() {
		super();
	}
	
	/**
	 * Excepción con mensaje
	 * @param msg - el mensaje de error asociado
	 */
	public DatosException(String msg) {
		super(msg);
	}
}
