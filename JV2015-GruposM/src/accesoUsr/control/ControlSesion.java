package accesoUsr.control;

import accesoDatos.Datos;
import accesoUsr.vista.VistaSesionTexto;
import modelo.Contraseña;
import modelo.SesionUsuario;
import modelo.Usuario;
import util.Fecha;

public class ControlSesion {
	private VistaSesionTexto vista;
	private Usuario usrSesion;
	private SesionUsuario sesion;
	private Datos datos;

	public ControlSesion() {
		this(null);
	}

	public ControlSesion(String idUsr) {
		initControlSesion(idUsr);
	}

	private void initControlSesion(String idUsr) {
		datos = Datos.getInstancia();
		vista = new VistaSesionTexto();
		iniciarSesionUsuario(idUsr); 
		new ControlSimulacion();
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
			usrSesion = datos.buscarUsuario(credencialUsr);
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
			datos.registrarSesion(sesion);
			vista.mostrar("Sesión: " + datos.getSesionesRegistradas()
			+ '\n' + "Iniciada por: " + usrSesion.getNombre() + " "
			+ usrSesion.getApellidos());
		}
		else {	
			vista.mostrar("Fin de programa...");
			System.exit(0);	
		}
	}

} //class
