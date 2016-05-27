/** 
 * Proyecto: Juego de la vida.
 *  Almacén de datos del programa. Utiliza patron Façade.
 *  @since: prototipo2.2
 *  @source: GestionDatos.java 
 *  @version: 1.0 - 2016/05/23 
 *  @author: ajp
 */

package accesoDatos;

import accesoDatos.fichero.MundosDAO;
import accesoDatos.fichero.PatronesDAO;
import accesoDatos.fichero.SesionesDAO;
import accesoDatos.fichero.SimulacionesDAO;
import accesoDatos.fichero.UsuariosDAO;
import modelo.Mundo;
import modelo.Patron;
import modelo.SesionUsuario;
import modelo.Simulacion;
import modelo.Usuario;

public class GestionDatos {

	// Requerido por el patrón Singleton
	private static GestionDatos instancia;

	// Requerido por el patrón Fachada
	private UsuariosDAO usuariosDAO; 
	private SesionesDAO sesionesDAO;
	private SimulacionesDAO simulacionesDAO;
	private MundosDAO mundosDAO;
	private PatronesDAO patronesDAO;

	/**
	 * Constructor por defecto de uso interno.
	 * Sólo se ejecutará una vez.
	 */
	private GestionDatos() {
		// usuariosDAO = UsuariosDAO.getInstancia();
		// sesionesDAO = SesionesDAO.getInstancia();
		// simulacionesDAO = SimulacionesDAO.getInstancia();
		// mundosDAO = MundosDAO.getInstancia();
		// patronesDAO = PatronesDAO.getInstancia();
	}

	/**
	 *  Método estático de acceso a la instancia única.
	 *  Si no existe la crea invocando al constructor interno.
	 *  Utiliza inicialización diferida.
	 *  Sólo se crea una vez; instancia única -patrón singleton-
	 *  @return instancia.
	 */
	public static GestionDatos getInstancia() {
		if (instancia == null) {
			instancia = new GestionDatos();
		}
		return instancia;
	}

	/**
	 *  Cierra almacenes de datos.
	 */
	public void cerrar() {
			
	}

	// FACHADA usuariosDAO
	/**
	 * Metodo fachada que obtiene un Usuario dado el id. 
	 * Reenvia petición al método DAO específico.
	 * @param idUsr - el idUsr de Usuario a obtener.
	 * @return - el Usuario encontrado; null si no existe.
	 */	
	public Usuario obtenerUsuario(String idUsr) {
		// return (Usuario) usuariosDAO.obtener(idUsr);
		return null;
	}

	/**
	 * Metodo fachada para alta de un Usuario. 
	 * Reenvia petición al método DAO específico.
	 * @param usuario - el objeto Usuario a dar de alta.
	 * @throws AccesoDatosException - si ya existe.
	 */
	public void altaUsuario(Usuario usuario) throws DatosException {
		// usuariosDAO.alta(usuario);
	}

	/**
	 * Metodo fachada para alta de un Usuario. 
	 * Reenvia petición al método DAO específico.
	 * @param id - el idUsr de Usuario a dar de baja.
	 * @throws AccesoDatosException - si ya existe.
	 */
	public Usuario bajaUsuario(String id) throws DatosException {
		return null;
		// return (Usuario) usuariosDAO.baja(id);
		
	}

	/**
	 * Metodo fachada para modicar un Usuario. 
	 * Reenvia petición al método DAO específico.
	 * @param u - el objeto Usuario con los cambios.
	 * @throws AccesoDatosException - si no existe.
	 */
	public void actualizarUsuario(Usuario u) throws DatosException {
		// usuariosDAO.actualizar(u);
	}

	/**
	 * Metodo fachada para obtener listado de todos
	 * los objetos en forma de texto.  
	 * Reenvia petición al método DAO específico.
	 * @return - el texto.
	 */
	public String toStringDatosUsuarios() {
		return null;
		// return usuariosDAO.listarDatos();
	}

	/**
	 * Metodo fachada para obtener IdUsr a partir 
	 * de un id equivalente. 
	 * Reenvia petición al método DAO específico.
	 * @param id - equivalente.
	 * @throws AccesoDatosException - si ya existe.
	 */
	public String getEquivalenciaId(String id) {
		return id;
		// return usuariosDAO.obtenerEquivalencia(id);
	}

	// FACHADA sesionesDAO
	/**
	 * Metodo fachada que obtiene un Usuario dado el id. 
	 * Reenvia petición al método DAO específico.
	 * @param id - el idUsr + fecha de la SesionUsuario a obtener.
	 * @return - la SesionUsuario encontrada; null si no existe.
	 */	
	public SesionUsuario obtenerSesion(String id) {
		return null;
		// return sesionesDAO.obtener(id);
	}

	/**
	 * Metodo fachada para alta de una SesionUsuario. 
	 * Reenvia petición al método DAO específico.
	 * @param s - el objeto SesionUsuario a dar de alta.
	 * @throws AccesoDatosException - si ya existe.
	 */
	public void altaSesion(SesionUsuario s) throws DatosException {
		// sesionesDAO.alta(s);
	}

	/**
	 * Metodo fachada para baja de una SesionUsuario. 
	 * Reenvia petición al método DAO específico.
	 * @param id - el idUsr + fecha de la SesionUsuario a dar de baja.
	 * @throws AccesoDatosException - si ya existe.
	 */
	public SesionUsuario bajaSesionUsuario(String id) throws DatosException {
		return null;
		// return (SesionUsuario) sesionesDAO.baja(id);
	}

	/**
	 * Metodo fachada para modicar una Sesión. 
	 * Reenvia petición al método DAO específico.
	 * @param s - el objeto SesionUsuario a modificar.
	 * @throws AccesoDatosException - si no existe.
	 */
	public void actualizarSesion(SesionUsuario s) throws DatosException {
		// sesionesDAO.actualizar(s);
	}

	/**
	 * Metodo fachada para obtener listado de todos
	 * los objetos en forma de texto.  
	 * Reenvia petición al método DAO específico.
	 * @return - el texto.
	 */
	public String toStringDatosSesiones() {
		return null;
		// return sesionesDAO.listarDatos();
	}

	// FACHADA simulacionesDAO
	/**
	 * Metodo fachada que obtiene una Simulacion dado el id. 
	 * Reenvia petición al método DAO específico.
	 * @param id - el idUsr + fecha de la Simulacion a obtener.
	 * @return - la Simulacion encontrada; null si no existe.
	 */	
	public Simulacion obtenerSimulacion(String id) {
		return null;
		// return simulacionesDAO.obtener(id);
	}

	/**
	 * Metodo fachada para alta de una Simulacion. 
	 * Reenvia petición al método DAO específico.
	 * @param s - el objeto Simulacion a dar de alta.
	 * @throws AccesoDatosException - si ya existe.
	 */
	public void altaSimulacion(Simulacion s) throws DatosException {
		// simulacionesDAO.alta(s);
	}

	/**
	 * Metodo fachada para baja de una Simulacion. 
	 * Reenvia petición al método DAO específico.
	 * @param id - el idUsr + fecha de la Simulacion a dar de baja.
	 * @throws AccesoDatosException - si ya existe.
	 */
	public Simulacion bajaSimulacion(String id) throws DatosException {
		return null;
		// return (Simulacion) simulacionesDAO.baja(id);
	}

	/**
	 * Metodo fachada para modicar una Sesión. 
	 * Reenvia petición al método DAO específico.
	 * @param s - el objeto SesionUsuario a modificar.
	 * @throws AccesoDatosException - si no existe.
	 */
	public void actualizarSimulacion(Simulacion s) throws DatosException {
		// simulacionesDAO.actualizar(s);
	}

	/**
	 * Metodo fachada para obtener listado de todos
	 * los objetos en forma de texto.  
	 * Reenvia petición al método DAO específico.
	 * @return - el texto.
	 */
	public String toStringDatosSimulaciones() {
		return null;
		// return simulacionesDAO.listarDatos();
	}

	// FACHADA mundosDAO
	/**
	 * Metodo fachada para alta de un Mundo. 
	 * Reenvia petición al método DAO específico.
	 * @param id - el nombre de un Mundo a buscar.
	 * @return - el Mundo encontrado; null si no existe.
	 */
	public Mundo obtenerMundo(String id) {
		return null;
		// return (Mundo) mundosDAO.obtener(id);
	}

	/**
	 * Metodo fachada para alta de un Mundo. 
	 * Reenvia petición al método DAO específico.
	 * @param m - el objeto Mundo a dar de alta.
	 * @throws AccesoDatosException - si ya existe.
	 */
	public void altaMundo(Mundo mundo) throws DatosException {
		// mundosDAO.alta(mundo);
	}

	/**
	 * Metodo fachada para baja de una Simulacion. 
	 * Reenvia petición al método DAO específico.
	 * @param id - el nombre de un Mundo a dar de baja.
	 * @throws AccesoDatosException - si ya existe.
	 */
	public Mundo bajaMundo(String id) throws DatosException {
		return null;
		// return mundosDAO.baja(id);
	}

	/**
	 * Metodo fachada para modicar un Mundo. 
	 * Reenvia petición al método DAO específico.
	 * @param m - el objeto Mundo a modificar.
	 * @throws AccesoDatosException - si no existe.
	 */
	public void actualizarMundo(Mundo mundo)  throws DatosException {
		// mundosDAO.actualizar(mundo);
	}

	/**
	 * Metodo fachada para obtener listado de todos
	 * los objetos en forma de texto.  
	 * Reenvia petición al método DAO específico.
	 * @return - el texto.
	 */
	public String toStringDatosMundos() {
		return null;
		// return mundosDAO.listarDatos();
	}

	// FACHADA patronesDAO
	/**
	 * Metodo fachada para alta de un Patron. 
	 * Reenvia petición al método DAO específico.
	 * @param id - el nombre de Patron a buscar.
	 * @return - el Patron encontrado; null si no existe.
	 */
	public Patron obtenerPatron(String id) {
		return null;
		// return patronesDAO.obtener(id);
	}

	/**
	 * Metodo fachada para alta de una Patron. 
	 * Reenvia petición al método DAO específico.
	 * @param p - el objeto Patron a dar de alta.
	 * @throws AccesoDatosException - si ya existe.
	 */
	public void altaPatron(Patron patron) throws DatosException {
		// patronesDAO.alta(patron);
	}

	/**
	 * Metodo fachada para baja de un Patron. 
	 * Reenvia petición al método DAO específico.
	 * @param id - el nombre de Patron a dar de baja.
	 * @throws AccesoDatosException - si ya existe.
	 */
	public Patron bajaPatron(String id) throws DatosException {
		return null;
		// return patronesDAO.baja(id);
	}

	/**
	 * Metodo fachada para modicar un Patron. 
	 * Reenvia petición al método DAO específico.
	 * @param p - el objeto Patron a modificar.
	 * @throws AccesoDatosException - si no existe.
	 */
	public void actualizarPatron(Patron p) throws DatosException {
		// patronesDAO.actualizar(p);
	}

	/**
	 * Metodo fachada para obtener listado de todos
	 * los objetos en forma de texto.  
	 * Reenvia petición al método DAO específico.
	 * @return - el texto.
	 */
	public String toStringDatosPatrones() {
		return null;
		// return patronesDAO.listarDatos();
	}

} //class