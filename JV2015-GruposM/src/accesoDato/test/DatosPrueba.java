/** 
 * Proyecto: Juego de la vida.
 *  Clase auxiliar para generar datos de prueba.
 *  @since: prototipo2.1
 *  @source: DatosPrueba.java 
 * @version: 1.0 - 9/05/2016 
 *  @author: ajp
 */

package accesoDato.test;

import java.util.ArrayList;
import java.util.Hashtable;

import accesoDato.AccesoDatosException;
import accesoDato.Datos;
import modelo.Contraseña;
import modelo.Correo;
import modelo.Direccion;
import modelo.Mundo;
import modelo.Nif;
import modelo.Patron;
import modelo.Posicion;
import modelo.SesionUsuario;
import modelo.Simulacion;
import modelo.Usuario;
import modelo.Usuario.RolUsuario;
import util.Fecha;

public class DatosPrueba {

	// Almacen de datos
	private static Datos datos = Datos.getInstancia();

	/**
	 * Genera datos de prueba válidos dentro 
	 * del almacén de datos.
	 * @param numero - el número de usuarios a generar.
	 * @throws DatosException 
	 */
	public static void cargarUsuariosPrueba(int numero) {
		for (int i = 0; i < numero; i++) {
			Usuario usr =  new Usuario(new Nif("0234455"+i+"K"), "Pepe" + i,
					"López Pérez" +i, new Direccion("30012", "Alta", "10", "Murcia", "España"), 
					new Correo("pepe" + i + "@gmail.com"), new Fecha(1990, 11, 12), 
					new Fecha(2014, 12, 3), new Contraseña("Miau#" + i), RolUsuario.NORMAL);				
			try {
				datos.altaUsuario(usr);
			} catch (AccesoDatosException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Genera datos de prueba válidos dentro 
	 * de los almacenes de datos.
	 * @param numero - el número de sesiones a generar.
	 */
	public static void cargarSesionesPrueba(int numero) {
		for (int i = 0; i < numero; i++) {
			Usuario usr = datos.buscarUsuario("PLP56" + (char) ('A' + i));
			if (usr != null) {
				SesionUsuario aux = new SesionUsuario();
				aux.setUsr(usr);
				aux.setFecha(new Fecha(2015, 1, 13)); 
				datos.registrarSesion(aux);
			}
		}
	}

	/**
	 * Genera datos de prueba válidos dentro 
	 * de los almacenes de datos.
	 * @param numero - el número de sesiones a generar.
	 */
	public static void cargarMundosPrueba(int numero) {
		for (int i = 0; i < numero; i++) {
			Mundo aux = new Mundo();
			aux.setNombre("Mundo" + i);
			aux.setDistribucion(new Hashtable<Patron,Posicion>()); 
			aux.setEspacio(new byte[0][0]);
			aux.setConstantes(new ArrayList<Integer>());
			datos.altaMundo(aux);
		}
	}

	/**
	 * Genera datos de prueba válidos dentro 
	 * del almacén de datos.
	 * @param numero - el número de patrones a generar.
	 */
	public static void cargarPatronesPrueba(int numero) {
		for (int i = 0; i < numero; i++) {
			Patron patron = new Patron();
			patron.setNombre("Patron" + i);
			patron.setEsquema(new byte[0][0]);
			datos.altaPatron(patron);
		}
	}

	/**
	 * Genera datos de prueba válidos dentro 
	 * de los almacenes de datos.
	 * @param numero - el número de simulaciones a generar.
	 */
	public static void cargarSimulacionesPrueba(int numero) {
		for (int i = 0; i < numero; i++) {
			Usuario usr = datos.buscarUsuario("PLP56" + (char) ('A' + i));
			if (usr != null) {
				Simulacion aux = new Simulacion();
				aux.setUsr(usr);
				aux.setFecha(new Fecha(2015, 1, 13)); 
				datos.altaSimulacion(aux);
			}
		}
	}

} //class
