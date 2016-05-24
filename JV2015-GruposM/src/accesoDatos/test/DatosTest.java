package accesoDatos.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import accesoDatos.DatosException;
import accesoDatos.Datos;
import modelo.Contraseña;
import modelo.Correo;
import modelo.Direccion;
import modelo.Nif;
import modelo.SesionUsuario;
import modelo.Usuario;
import modelo.Usuario.RolUsuario;
import util.Fecha;

public class DatosTest {

	Datos datos;

	@Before
	public void datosPrueba() {
		datos = Datos.getInstancia();
		// Carga datos ordenados
		DatosPrueba.cargarUsuariosPrueba(10);
	}

	@After
	public void borraDatosPrueba() {
		// Borra datos de prueba.
		datos.getDatosUsuarios().clear();

	}

	@Test
	public void testGetInstancia() {
		assertSame(datos, Datos.getInstancia());
	}

	@Test
	public void testGetDatosUsuarios() {
		assertNotNull(datos.getDatosUsuarios());
	}

	@Test
	public void testGetDatosSesiones() {
		assertNotNull(datos.getDatosSesiones());
	}

	@Test
	public void testGetEquivalencias() {
		assertNotNull(datos.getEquivalencias());
	}

	@Test
	public void testGetSesionesRegistradas() {
		assertTrue(datos.getDatosSesiones().size() >= 0);
	}

	@Test
	public void testTextoDatosUsuarios() {
		assertNotNull(datos.textoDatosUsuarios());
	}

	@Test
	public void testVolcarDatosUsuarios() {
		assertNotNull(datos.volcarDatosUsuarios());
	}

	@Test
	public void testVolcarDatosSesiones() {
		assertNotNull(datos.volcarDatosSesiones());
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
		assertEquals(datos.buscarUsuario("PLP5L").getIdUsr(), "PLP5L");
		assertEquals(datos.buscarUsuario("12345675L").getIdUsr(), "PLP5L");
		assertEquals(datos.buscarUsuario("pepe@gmail.com").getIdUsr(), "PLP5L");
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
		assertSame(usr, datos.buscarUsuario(usr));
	}

	@Test
	public void testBuscarUsuarioId() {
		assertEquals(datos.buscarUsuario("PLP5K").getIdUsr(), "PLP5K");
	}

	@Test
	public void testAltaUsuarioIntermedio() {	
		// Usuario con idUsr "PLP5L" irá en posición intermedia.
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
		// Queda después de idUsr "PLP5K", posición 6 del arrayList
		assertSame(usr, datos.getDatosUsuarios().get(6));
	}

	@Test
	public void testAltaUsuarioPrincipio() {	
		// Usuario con idUsr "PLP0J" irá en posición intermedia.
		Usuario usr =  new Usuario(new Nif("12345670J"), "Pepe",
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
		// Queda en la primera posición del ArrayList, antes de "PLP0K" 
		assertSame(usr, datos.getDatosUsuarios().get(0));
	}
	
	@Test
	public void testAltaUsuarioRepetido() {	
		// Usuario con idUsr "PLP5L"
		Usuario usr =  new Usuario(new Nif("12345675L"), "Pepe",
				"López Pérez", new Direccion("30012", "Alta", "10", "Murcia", "España"), 
				new Correo("pepe" + "@gmail.com"), new Fecha(1990, 11, 12), 
				new Fecha(2014, 12, 3), new Contraseña("Miau#32"), RolUsuario.NORMAL);		
		try {
			datos.altaUsuario(usr);
			// Usuario ya existe.
			datos.altaUsuario(usr);
		} 
		catch (DatosException e) {
			assertSame(usr, datos.buscarUsuario(usr));
		}
	}
	
	@Test
	public void testRegistrarSesion() {
		// Usuario con idUsr "PLP5L"
		Usuario usr =  new Usuario(new Nif("12345675L"), "Pepe",
				"López Pérez", new Direccion("30012", "Alta", "10", "Murcia", "España"), 
				new Correo("pepe" + "@gmail.com"), new Fecha(1990, 11, 12), 
				new Fecha(2014, 12, 3), new Contraseña("Miau#32"), RolUsuario.NORMAL);
		
		SesionUsuario sesion = new SesionUsuario(usr, new Fecha());
		datos.registrarSesion(sesion);
		assertSame(sesion, datos.getDatosSesiones().get(0));
	}

} // class
