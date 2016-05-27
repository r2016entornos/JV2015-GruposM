package accesoUsr.control;

import accesoDatos.DatosException;
import accesoDatos.GestionDatos;
import accesoUsr.vista.VistaSesionTexto;
import modelo.Contraseña;
import modelo.SesionUsuario;
import modelo.Usuario;
import util.Fecha;

public class ControlSesion {
	private VistaSesionTexto vista;
	private Usuario usrSesion;
	private SesionUsuario sesion;
	private GestionDatos datos;

	public ControlSesion() {
		this(null);
	}

	public ControlSesion(String idUsr) {
		initControlSesion(idUsr);
	}

	private void initControlSesion(String idUsr) {
		datos = GestionDatos.getInstancia();
		vista = new VistaSesionTexto();
		iniciarSesionUsuario(idUsr); 
		new ControlSimulacion(sesion);
	}

	/**
	 * Controla el acceso de usuario 
	 * y registro de la sesión correspondiente.
	 * @param credencialUsr ya obtenida, puede ser null.
	 */
	private void iniciarSesionUsuario(String idUsr) {
		boolean todoCorrecto = false;				// Control de credenciales de usuario.
		int intentos = 3;							// Contandor de intentos.
		String credencialUsr = idUsr;
		do {
			if (idUsr == null) {
				// Pide credencial usuario.
				vista.mostrar("Introduce el idUsr: ");
				credencialUsr = vista.pedirIdUsr();	
			}
			credencialUsr = credencialUsr.toUpperCase();
			// Pide contraseña
			vista.mostrar("Introduce clave acceso: ");
			String clave = vista.pedirClaveAcceso();

			// Obtiene idUsr que corresponde
			credencialUsr = datos.getEquivalenciaId(credencialUsr);	

			// Busca usuario coincidente con credencial
			vista.mostrar(credencialUsr);
			usrSesion = datos.obtenerUsuario(credencialUsr);
			if ( usrSesion != null) {			
				if (usrSesion.getClaveAcceso().equals(new Contraseña(clave))) {
					todoCorrecto = true;
				}
			}
			if (todoCorrecto == false) {
				intentos--;
				vista.mostrar("Credenciales incorrectas...");
				vista.mostrar("Quedan " + intentos + " intentos... ");
			}
		}
		while (!todoCorrecto && intentos > 0);

		if (todoCorrecto) {
			// Registra sesión
			//Crea la sesión de usuario en el sistema
			sesion = new SesionUsuario(usrSesion, new Fecha());
			try {
				datos.altaSesion(sesion);
			} catch (DatosException e) {
				e.printStackTrace();
			}
			// Falta implementar la gestion de id de sesion en la 
			// clase SesionUsuario.
			
			//vista.mostrar("Sesión: " + datos.obtenerSesion(id)
			//+ '\n' + "Iniciada por: " + usrSesion.getNombre() + " "
			//+ usrSesion.getApellidos());
		}
		else {	
			vista.mostrar("Fin de programa...");
			System.exit(0);	
		}
	}

} //class
