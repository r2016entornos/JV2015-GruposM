/** 
 * Proyecto: Juego de la vida.
 * Pruebas iniciales de las clases Usuario y SesionUsuario del modelo1.
 * @since: prototipo1.0
 * @source: JVPrincipal.java 
 * @version: 1.2 - 25/02/2016 
 * @author: ajp
 */

import accesoDatos.Datos;
import accesoDatos.test.DatosPrueba;
import accesoUsr.control.ControlSesion;

public class JVPrincipal {	
	public static void main(String[] args) {					
		DatosPrueba.cargarSimulacionPrueba();
			
		if (args.length == 0) { 
			new ControlSesion();
		}	
		else {
			new ControlSesion(args[0]);
		}
	}

} //class
