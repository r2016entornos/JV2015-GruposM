package accesoUsr.vista;

import java.util.Scanner;

import accesoUsr.control.ControlSimulacion;

public class VistaSimulacionTexto {
	// Atributos
	final int TAMAÑO = 12;
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
		for (int i = 0; i < TAMAÑO; i++) {
			for (int j = 0; j < TAMAÑO; j++) {
				System.out.print((control.getMundo().getEspacio()[i][j] == 1) ? "|o" : "| ");
			}
			System.out.println("|");
		}
	}
	public void mostrar(String mensaje) {
		System.out.println(mensaje);
	}
}
