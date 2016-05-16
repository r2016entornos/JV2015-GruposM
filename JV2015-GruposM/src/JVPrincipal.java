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
import accesoUsr.Presentacion;

public class JVPrincipal {	
	public static void main(String[] args) {				
		final int  MAX_USUARIOS_PRUEBA = 10;
		Datos datos = Datos.getInstancia();
		DatosPrueba.cargarUsuariosPrueba(MAX_USUARIOS_PRUEBA);
		
		Presentacion presentacion = new Presentacion();
		
		presentacion.mostrar(datos.textoDatosUsuarios());

		if (presentacion.iniciarSesion(datos)) {
			presentacion.arrancarSimulacion();
		}	
	}

} //class
