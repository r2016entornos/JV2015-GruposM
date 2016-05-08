/** 
 * Proyecto: Juego de la vida.
 * Pruebas iniciales de las clases Usuario y SesionUsuario del modelo1.
 * @since: prototipo1.0
 * @source: JVPrincipal.java 
 * @version: 1.2 - 25/02/2016 
 * @author: ajp
 */

import java.util.Date;
import java.util.Scanner;

import accesoDato.Datos;
import accesoUsr.Presentacion;
import modelo.*;

public class JVPrincipal {	
	public static void main(String[] args) {				
		Datos datos = new Datos();
		Presentacion presentacion = new Presentacion();
		
		presentacion.mostrar(datos.textoDatosUsuarios());

		if (presentacion.iniciarSesion(datos)) {
			presentacion.arrancarSimulacion();
		}
	}

} //class
