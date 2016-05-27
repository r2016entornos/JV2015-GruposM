/** 
 * Proyecto: Juego de la vida.
 * Maneja los errores de acceso a datos.
 *  @since: prototipo2.1
 *  @source: DatosException.java 
 * @version: 1.0 - 2016/05/25
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
