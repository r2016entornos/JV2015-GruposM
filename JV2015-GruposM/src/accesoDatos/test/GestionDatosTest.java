package accesoDatos.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import accesoDatos.DatosException;
import accesoDatos.GestionDatos;
import modelo.Contraseña;
import modelo.Correo;
import modelo.Direccion;
import modelo.Nif;
import modelo.SesionUsuario;
import modelo.Usuario;
import modelo.Usuario.RolUsuario;
import util.Fecha;

public class GestionDatosTest {

	GestionDatos datos;

	@Before
	public void datosPrueba() {
		datos = GestionDatos.getInstancia();
		// Carga datos de prueba
		DatosPrueba.cargarUsuariosPrueba(2);
		DatosPrueba.cargarMundoPrueba();
		DatosPrueba.cargarSimulacionPrueba();
		DatosPrueba.cargarSesionesPrueba(1);
		DatosPrueba.cargarPatronesPrueba(1);
	}

	@After
	public void borraDatosPrueba() {
		// Borra datos de prueba.
		DatosPrueba.borrarDatosPrueba();
	}

	@Test
	public void testGetInstancia() {
		assertSame(datos, GestionDatos.getInstancia());
	}

	@Test
	public void testEquivalenciaId() {
		// Usuario con idUsr "PLP5L"
		Usuario usr =  new Usuario(new Nif("12345675L"), "Pepe",
				"López Pérez", new Direccion("30012", "Alta", "10", "Murcia", "España"), 
				new Correo("pepe" + "@gmail.com"), new Fecha(1990, 11, 12), 
				new Fecha(2014, 12, 3), new Contraseña("Miau#32"), RolUsuario.NORMAL);
		try {
			// Usuario nuevo, que no existe.
			datos.altaUsuario(usr);
		} 
		catch (DatosException e) {
			e.printStackTrace();
		}
		assertEquals(datos.obtenerUsuario("PLP5L").getIdUsr(), "PLP5L");
		assertEquals(datos.obtenerUsuario("12345675L").getIdUsr(), "PLP5L");
		assertEquals(datos.obtenerUsuario("pepe@gmail.com").getIdUsr(), "PLP5L");
	}

	@Test
	public void testBuscarUsuarioUsuario() {
		// Usuario con idUsr "PLP5L"
		Usuario usr =  new Usuario(new Nif("12345675L"), "Pepe",
				"López Pérez", new Direccion("30012", "Alta", "10", "Murcia", "España"), 
				new Correo("pepe" + "@gmail.com"), new Fecha(1990, 11, 12), 
				new Fecha(2014, 12, 3), new Contraseña("Miau#32"), RolUsuario.NORMAL);
		try {
			// Usuario nuevo, que no existe.
			datos.altaUsuario(usr);
		} 
		catch (DatosException e) {
			e.printStackTrace();
		}
		// Busca el mismo Usuario almacenado.
		assertSame(usr, datos.obtenerUsuario(usr.getIdUsr()));
	}

	@Test
	public void testBuscarUsuarioId() {
		assertEquals(datos.obtenerUsuario("PLP5K").getIdUsr(), "PLP5K");
	}

	@Test
	public void testAltaSesion() {
		// Usuario con idUsr "PLP5L"
		Usuario usr =  new Usuario(new Nif("12345675L"), "Pepe",
				"López Pérez", new Direccion("30012", "Alta", "10", "Murcia", "España"), 
				new Correo("pepe" + "@gmail.com"), new Fecha(1990, 11, 12), 
				new Fecha(2014, 12, 3), new Contraseña("Miau#32"), RolUsuario.NORMAL);

		SesionUsuario sesion = new SesionUsuario(usr, new Fecha());
		try {
			datos.altaSesion(sesion);
		} catch (DatosException e) {
			e.printStackTrace();
		}
		//assertSame(sesion, datos.obtenerSesion(id));
	}

} // class
