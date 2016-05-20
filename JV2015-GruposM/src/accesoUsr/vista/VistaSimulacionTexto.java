package accesoUsr.vista;

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
		byte[][] espacio = control.getMundo().getEspacio();
		for (int i = 0; i < espacio.length; i++) {
			for (int j = 0; j < espacio.length; j++) {
				System.out.print((espacio[i][j] == 1) ? "|o" : "| ");
			}
			System.out.println("|");
		}
	}
	public void mostrar(String mensaje) {
		System.out.println(mensaje);
	}
}
