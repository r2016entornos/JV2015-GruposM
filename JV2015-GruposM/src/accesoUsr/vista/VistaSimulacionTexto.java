package accesoUsr.vista;
/** 
 * Proyecto: Juego de la vida.
 * Clase Vista (MCV) para la presentación de la simulación.
 *  @since: prototipo2
 *  @source: VistaSimulacionTexto.java 
 *  @version: 2.1 - 2015/05/18 
 *  @author: ajp
 */
import java.util.Scanner;

import accesoUsr.control.ControlSimulacion;

public class VistaSimulacionTexto {
	// Atributos
	final int CICLOS = 120;
	private Scanner teclado;
	
	public VistaSimulacionTexto() {
		teclado = new Scanner(System.in);
	}

	/**
	 * Despliega en la consola el estado almacenado correspondiente
	 * a una generación del Juego de la vida.
	 */
	public void mostrarMundo(ControlSimulacion control) {
		final int TAMAÑO = control.getMundo().getEspacio().length;
		for (int i = 0; i < TAMAÑO; i++) {
			for (int j = 0; j < TAMAÑO; j++) {
				System.out.print((control.getMundo().getEspacio()[i][j] == 1) ? "|o" : "| ");
			}
			System.out.println("|");
		}
	}
	
	/**
	 * Muestra en la consola el texto recibido.
	 */
	public void mostrar(String mensaje) {
		System.out.println(mensaje);
	}
}
