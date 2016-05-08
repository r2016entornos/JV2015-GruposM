package modelo.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import modelo.Contraseña;
import modelo.Correo;
import modelo.Direccion;
import modelo.Nif;
import modelo.Persona;
import modelo.Usuario;
import modelo.Usuario.RolUsuario;
import util.Fecha;

public class PersonaTest {
	private Persona persona1;
	private Persona persona2;
	
	@Before
	public void crearObjetosPrueba() {
		persona1 = new Usuario();
		persona2 = new Usuario(new Nif("12345678A"), "Pepe", "López Pérez",
				new Direccion("30012", "Alta", "10", "Murcia", "España"), 
				new Correo("pepe@gmail.com"), new Fecha(2010, 4, 27),
				new Fecha(), new Contraseña(), RolUsuario.NORMAL);
	}
	
	@After
	public void borrarObjetosPrueba() {
		persona1 = null;
		persona2 = null;
	}
	
	@Test
	public void testPersonaConvencional() {
		assertTrue(persona2 != null);
	}

	@Test
	public void testGetNif() {
		assertEquals(persona2.getNif().getTexto(), "12345678A");	
	}

	@Test
	public void testGetNombre() {
		assertEquals(persona2.getNombre(), "Pepe");	

	}

	@Test
	public void testGetApellidos() {
		assertEquals(persona2.getApellidos(), "López Pérez");	
	}

	@Test
	public void testGetDomicilio() {
		assertNotNull(persona2.getDomicilio());
	}

	@Test
	public void testGetFechaNacimiento() {
		assertNotNull(persona2.getFechaNacimiento());
	}

	@Test
	public void testGetCorreo() {
		assertNotNull(persona2.getCorreo());
	}

	@Test
	public void testSetNif() {
		Nif nif = new Nif("12345678A");
		persona1.setNif(nif);
		assertSame(persona1.getNif(), nif);
	}

	@Test
	public void testSetNombre() {
		persona1.setNombre("Pepe");
		assertEquals(persona1.getNombre(), "Pepe");
	}

	@Test
	public void testSetApellidos() {
		persona1.setApellidos("López Pérez");
		assertEquals(persona1.getApellidos(), "López Pérez");
	}

	@Test
	public void testSetDomicilio() {
		Direccion direccion = new Direccion("30012", "Alta", "10", "Murcia", "España");
		persona1.setDomicilio(direccion);
		assertSame(persona1.getDomicilio(), direccion);
	}

	@Test
	public void testSetFechaNacimiento() {
		Fecha fecha = new Fecha(2010, 4, 27);
		persona1.setFechaNacimiento(fecha);
		assertSame(persona1.getFechaNacimiento(), fecha);
	}

	@Test
	public void testSetCorreo() {
		Correo correo = new Correo("pepe@gmail.com");
		persona1.setCorreo(correo);
		assertSame(persona1.getCorreo(), correo);
	}

	@Test
	public void testToString() {
		assertEquals(persona2.toString(), "\n nif: \t\t12345678A," +
				 "\n nombre: \tPepe," +
				 "\n apellidos: \tLópez Pérez," +
				 "\n domicilio: \t30012, Alta, 10, Murcia, España," +
				 "\n correo: \tpepe@gmail.com," +
				 "\n fechaNacimiento: \t2010.4.27,"); 
	}

}
