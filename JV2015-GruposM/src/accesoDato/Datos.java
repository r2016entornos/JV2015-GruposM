package accesoDato;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

import modelo.Contraseña;
import modelo.Correo;
import modelo.Direccion;
import modelo.Mundo;
import modelo.Nif;
import modelo.Patron;
import modelo.SesionUsuario;
import modelo.Simulacion;
import modelo.Usuario;
import modelo.Usuario.RolUsuario;
import util.Fecha;

public class Datos {

	// Requerido por el patrón Singleton
	private static Datos instancia;

	private ArrayList<Usuario> datosUsuarios;
	private Hashtable<String, String> equivalenciasId;
	private ArrayList<SesionUsuario> datosSesiones;
	private ArrayList<Patron> datosPatrones;
	private ArrayList<Mundo> datosMundos;
	private ArrayList<Simulacion> datosSimulaciones;

	/**
	 * Constructor por defecto de uso interno.
	 * Sólo se ejecutará una vez.
	 */
	private Datos() {
		datosUsuarios = new ArrayList<Usuario>();
		equivalenciasId = new Hashtable<String, String>();
		datosSesiones = new ArrayList<SesionUsuario>();
		datosPatrones = new ArrayList<Patron>();
		datosMundos = new ArrayList<Mundo>();
		datosSimulaciones = new ArrayList<Simulacion>();
	}

	/**
	 *  Método estático de acceso a la instancia única.
	 *  Si no existe la crea invocando al constructor interno.
	 *  Utiliza inicialización diferida.
	 *  Sólo se crea una vez; instancia única -patrón singleton-
	 *  @return instancia
	 */
	public static Datos getInstancia() {
		if (instancia == null) {
			instancia = new Datos();
		}
		return instancia;
	}


	public ArrayList<Usuario> getDatosUsuarios() {
		return datosUsuarios;
	}

	public Hashtable<String, String> getEquivalencias() {
		return equivalenciasId;
	}

	public ArrayList<SesionUsuario> getDatosSesiones() {
		return datosSesiones;
	}

	public ArrayList<Patron> getDatosPatrones() {
		return datosPatrones;
	}

	public ArrayList<Mundo> getDatosMundos() {
		return datosMundos;
	}

	public void setDatosSimulaciones(ArrayList<Simulacion> datosSimulaciones) {
		this.datosSimulaciones = datosSimulaciones;
	}

	public int getSesionesRegistradas() {
		return datosSesiones.size();
	}

	/**
	 * Obtiene el idUsr que equivale a la credencial recibida.
	 * @param credencialUsr, puede ser nif o correo.
	 */
	public String getEquivalenciaId(String credencialUsr) {	
		return equivalenciasId.get(credencialUsr);
	}

	/**
	 * Añade una nueva equivalencias para idUsr.
	 * @param usr
	 */
	public void registrarEquivalenciaId(Usuario usr) {	 
		assert usr != null;
		equivalenciasId.put(usr.getIdUsr(), usr.getIdUsr());
		equivalenciasId.put(usr.getNif().getTexto(), usr.getIdUsr());
		equivalenciasId.put(usr.getCorreo().getTexto(), usr.getIdUsr());	
	}

	/**
	 * Alta de usuario inserción binaria en orden.
	 * @param usr - el usuario a guardar.
	 * @throws AccesoDatosException 
	 */
	public void altaUsuario(Usuario usr) throws AccesoDatosException {
		assert usr != null;
		int comparacion;
		int inicio = 0;
		int fin = datosUsuarios.size() - 1;
		int medio = 0;
		boolean noExisteUsuario = true;
		while (inicio <= fin) {
			medio = (inicio + fin) / 2;			// Calcula posición central.
			// compara los dos id. Obtiene < 0 si id va después que medio.
			comparacion = datosUsuarios.get(medio).getIdUsr()
					.compareToIgnoreCase(usr.getIdUsr());
			if (comparacion == 0) {			
				throw new AccesoDatosException("El Usuario ya existe...");   				  
			}		
			if (comparacion < 0) {
				inicio = medio + 1;
			}			
			else {
				fin = medio - 1;
			}
		}	
		datosUsuarios.add(inicio, usr); 	// Inserta el usuario en orden.
		registrarEquivalenciaId(usr);		
	}

	/**
	 * Búsqueda de usuario.
	 * @param usr - el Usuario a buscar.
	 * @return - el Usuario encontrado o null si no existe.
	 */
	public Usuario buscarUsuario(Usuario usr) {
		assert usr != null;
		return this.buscarUsuario(usr.getIdUsr());				
	}
	
	/**
	 * Búsqueda binaria de usuario dado su idUsr.
	 * @param idUsr - el idUsr de Usuario a buscar.
	 * @return - el Usuario encontrado o null si no existe.
	 */
	public Usuario buscarUsuario(String idUsr) {
		assert idUsr != null;
		int comparacion;
		int inicio = 0;
		int fin = datosUsuarios.size() - 1;
		int medio;
		idUsr =  getEquivalenciaId(idUsr);
		while (inicio <= fin) {
			medio = (inicio + fin) / 2;
			comparacion = datosUsuarios.get(medio).getIdUsr()
					.compareToIgnoreCase(idUsr);
			if (comparacion == 0) {
				return datosUsuarios.get(medio);
			}
			if (comparacion < 0) {
				inicio = medio + 1;
			}
			else {
				fin = medio - 1;
			}
		}
		return null;				
	}
	
	/**
	 * Añade una nueva sesión en el almacén de datos.
	 * @param sesionUsuario a guardar.
	 */
	public void registrarSesion(SesionUsuario sesionUsuario) {
		assert sesionUsuario != null;
		datosSesiones.add(sesionUsuario);	
	}

	/**
	 * Añade una nuevo mundo en el almacén de datos.
	 * @param mundo a guardar.
	 */
	public void altaMundo(Mundo mundo) {	
		assert mundo != null;
		datosMundos.add(mundo);	
	}

	/**
	 * Añade una nuevo patrón en el almacén de datos.
	 * @param patron a guardar.
	 */
	public void altaPatron(Patron patron) {
		assert patron != null;
		datosPatrones.add(patron);	
	}

	/**
	 * Añade una nueva simulación en el almacén de datos.
	 * @param simulacion a guardar.
	 */
	public void altaSimulacion(Simulacion simulacion) {
		assert simulacion != null;
		datosSimulaciones.add(simulacion);
	}

	/**
	 * Obtiene todos los usuarios almacenados en texto formateado.
	 */
	public String textoDatosUsuarios() {
		StringBuilder aux = new StringBuilder();
		for (Usuario u: datosUsuarios) {
			aux.append("\n" + u);
		}
		return aux.toString();
	}

	/**
	 * Volcado de todos los usuarios almacenados.
	 */
	public String volcarDatosUsuarios() {
		StringBuilder aux = new StringBuilder();
		for (Usuario u: datosUsuarios) {
			aux.append("<usr>"  
					+"<attrib>"+u.getNif()+"</attrib>"
					+"<attrib>"+u.getNombre()+"</attrib>"
					+"<attrib>"+u.getApellidos()+"</attrib>"
					+"<attrib>"+u.getIdUsr()+"</attrib>"
					+"<attrib>"+u.getDomicilio()+"</attrib>"
					+"<attrib>"+u.getCorreo()+"</attrib>"
					+"<attrib>"+u.getFechaNacimiento()+"</attrib>"
					+"<attrib>"+u.getFechaAlta()+"</attrib>"
					+"<attrib>"+u.getClaveAcceso()+"</attrib>"
					+"<attrib>"+u.getRol()+"</attrib>"
					+"</usr>");
		}
		return aux.toString();
	}

	/**
	 * Volcado de todos las sesiones almacenadas.
	 */
	public String volcarDatosSesiones() {
		StringBuilder aux = new StringBuilder();
		for (SesionUsuario s: datosSesiones) {
			aux.append("<sesion>"
					+"<usr>"  
					+"<attrib>"+s.getUsr().getNif()+"</attrib>"
					+"<attrib>"+s.getUsr().getNombre()+"</attrib>"
					+"<attrib>"+s.getUsr().getApellidos()+"</attrib>"
					+"<attrib>"+s.getUsr().getIdUsr()+"</attrib>"
					+"<attrib>"+s.getUsr().getDomicilio()+"</attrib>"
					+"<attrib>"+s.getUsr().getCorreo()+"</attrib>"
					+"<attrib>"+s.getUsr().getFechaNacimiento()+"</attrib>"
					+"<attrib>"+s.getUsr().getFechaAlta()+"</attrib>"
					+"<attrib>"+s.getUsr().getClaveAcceso()+"</attrib>"
					+"<attrib>"+s.getUsr().getRol()+"</attrib>"
					+"</usr>"
					+"<attrib>"+s.getFecha()+"</attrib>"
					+"<sesion>");
		}
		return aux.toString();
	}

} //class
