package accesoDatos.memoria;
/** 
 * Proyecto: Juego de la vida.
 *  Resuelve todos los aspectos del almacenamiento del
 *  DTO Usuario utilizando un ArrayList no persistentes; sólo en memoria.
 *  Colabora en el patron Fachada.
 *  @since: prototipo2.2
 *  @source: MundosDAO.java 
 *  @version: 1.0 - 2016/05/23 
 *  @author: ajp
 */
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

import accesoDatos.DatosException;
import accesoDatos.OperacionesDAO;
import modelo.Usuario;

public class UsuariosDAO  implements OperacionesDAO {

	// Requerido por el Singleton. 
	private static UsuariosDAO instancia = null;

	// Elementos de almacenamiento.
	private ArrayList<Usuario> datosUsuarios;
	private Map<String,String> equivalenciasId;

	/**
	 * Constructor por defecto de uso interno.
	 * Sólo se ejecutará una vez.
	 * @throws AccesoDatosException 
	 */
	private UsuariosDAO() {
		datosUsuarios = new ArrayList<Usuario>();
		equivalenciasId = new Hashtable<String, String>();
	}

	/**
	 *  Método estático de acceso a la instancia única.
	 *  Si no existe la crea invocando al constructor interno.
	 *  Utiliza inicialización diferida.
	 *  Sólo se crea una vez; instancia única -patrón singleton-
	 *  @return instancia
	 * @throws AccesoDatosException 
	 */
	public static UsuariosDAO getInstancia() {
		if (instancia == null) {
			instancia = new UsuariosDAO();
		}
		return instancia;
	}
	
	//OPERACIONES DAO
	/**
	 * Obtiene por búsqueda binaria un Usuario dado su id.
	 * @param id - el idUsr de Usuario a buscar.
	 * @return - el Usuario encontrado; null si no existe.
	 */	
	@Override
	public Object obtener(String id) {

		int comparacion;
		int inicio = 0;
		int fin = datosUsuarios.size() - 1;
		int medio;
		while (inicio <= fin) {
			medio = (inicio + fin) / 2;
			comparacion = datosUsuarios.get(medio).getIdUsr().compareToIgnoreCase(id);

			if (comparacion == 0)
				return datosUsuarios.get(medio);

			if (comparacion < 0) 
				inicio = medio + 1;
			else
				fin = medio - 1;
		}
		return null;
	}

	/**
	 * @param id - la clave alternativa 
	 * @return - El idUsr equivalente
	 */
	public String obtenerEquivalencia(String id) {
		return equivalenciasId.get(id);
	}
	
	/**
	 *  Alta de un nuevo usuario en orden y sin repeticiones según el campo idUsr. 
	 *  Localiza previamente la posición que le corresponde por búsqueda binaria.
	 *	@param usr - Objeto a almacenar.
	 *  @throws DatosException - si ya existe.
	 */
	@Override
	public void alta(Object obj) throws DatosException {
		assert obj != null;
		Usuario usr = (Usuario) obj;
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
				throw new DatosException("El Usuario ya existe...");   				  
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
	 * Añade una nueva equivalencias para idUsr.
	 * @param usr
	 */
	private void registrarEquivalenciaId(Usuario usr) {	 
		assert usr != null;
		equivalenciasId.put(usr.getIdUsr(), usr.getIdUsr());
		equivalenciasId.put(usr.getNif().getTexto(), usr.getIdUsr());
		equivalenciasId.put(usr.getCorreo().getTexto(), usr.getIdUsr());	
	}

	/**
	 * Elimina el objeto, dado el id utilizado para el almacenamiento.
	 * @param id - el identificador del objeto a eliminar.
	 * @return - el Objeto eliminado.
	 * @throws AccesoDatosException - si no existe.
	 */
	@Override
	public Object baja(String id) throws DatosException {
		int comparacion;
		int inicio = 0;
		int fin = datosUsuarios.size() - 1;
		int medio;
		Usuario aux;
		while (inicio <= fin) {
			medio = (inicio + fin) / 2;
			comparacion = datosUsuarios.get(medio).getIdUsr().compareToIgnoreCase(id);
			if (comparacion == 0) {
				// Elimina
				aux = datosUsuarios.get(medio);
				datosUsuarios.remove(medio);
				equivalenciasId.remove(aux.getIdUsr(), aux.getIdUsr());
				return aux;
			}
			if (comparacion < 0) 
				inicio = medio + 1;
			else
				fin = medio - 1;
		}
		throw new DatosException("El Usuario no existe...");
	} 

	/**
	 *  Actualiza datos de un Usuario reemplazando el almacenado por el recibido. 
	 *  Localiza la posición del almacenado por búsqueda binaria.
	 *	@param obj - Usuario con los cambios.
	 *  @throws AccesoDatosException - si no existe.
	 */
	@Override
	public void actualizar(Object obj) throws DatosException {
		int comparacion;					// auxiliar para la comparación de String
		boolean existeUsuario = false;
		Usuario aux;
		Usuario u = (Usuario) obj;			// para conversión cast
		int inicio = 0;
		int fin = datosUsuarios.size()-1;
		int medio = 0;
		while (inicio <= fin) {
			medio = (inicio + fin) / 2;     // calcula posición central
			// compara los dos id. Obtiene < 0 si id va después que medio
			comparacion = datosUsuarios.get(medio).getIdUsr().compareTo(u.getIdUsr());

			if (comparacion == 0) {			// id coincide con el comparado
				existeUsuario = true;
				break;       				// ya actualizado  
			}
			if (comparacion < 0) 			// id va después alfabéticamente 
				inicio = medio + 1;
			else				 			// id va antes alfabéticamente
				fin = medio - 1;
		}
		if (existeUsuario) {
			// actualiza el usuario
			aux = datosUsuarios.get(medio);
			datosUsuarios.remove(medio); 
			datosUsuarios.add(medio, u); 	
			//actualiza equivalencias de id de acceso
			equivalenciasId.remove(aux.getIdUsr(), aux.getIdUsr());
			equivalenciasId.put(u.getIdUsr(), u.getIdUsr());
			equivalenciasId.put(u.getNif().getTexto(), u.getIdUsr());
			equivalenciasId.put(u.getCorreo().getTexto(), u.getIdUsr());
		}		
		else 
			throw new DatosException("No existe el Usuario...");
	} 

	/**
	 * Obtiene el listado de todos los usuarios almacenados.
	 * @return el texto con el volcado de datos.
	 */
	@Override
	public String listarDatos() {
		StringBuilder sb = new StringBuilder();
		for (Usuario u: datosUsuarios)
			if (u != null)
				sb.append("\n" + u);
		return sb.toString();
	}

	/**
	 * Serializa en una cadena de caracteres los datos de todos los usuarios almacenados.
	 * @return el texto
	 */
	public String datosUsuariosTexto() {
		StringBuilder aux = new StringBuilder();
		for (Usuario u: datosUsuarios) {

			if (u != null) 
				aux.append(u.toString() + ';');
		}
		return aux.toString();
	}

} //class