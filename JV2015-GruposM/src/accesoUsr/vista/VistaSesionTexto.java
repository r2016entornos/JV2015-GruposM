package accesoUsr.vista;

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
