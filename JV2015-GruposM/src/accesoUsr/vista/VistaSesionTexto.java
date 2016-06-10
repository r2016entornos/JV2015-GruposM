package accesoUsr.vista;
/** 
 * Proyecto: Juego de la vida.
 * Clase Vista (MCV) para la presentación de la sesión de usuario.
 *  @since: prototipo2.1
 *  @source: VistaSesionTexto.java 
 * @version: 1.0 - 2015/05/18 
 *  @author: ajp
 */
import java.util.Scanner;

public class VistaSesionTexto {
	
	private Scanner teclado;
	
	public VistaSesionTexto() {
		teclado = new Scanner(System.in);
	}
	
	public String pedirIdUsr() {	
		return teclado.nextLine();
	}
	
	public String pedirClaveAcceso() {
		return teclado.nextLine();
	}

	public void mostrar(String mensaje) {
		System.out.println(mensaje);
	}
}
