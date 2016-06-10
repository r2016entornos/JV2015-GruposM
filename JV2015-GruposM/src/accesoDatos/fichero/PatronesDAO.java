package accesoDatos.fichero;
/** 
 * Proyecto: Juego de la vida.
 *  Resuelve todos los aspectos del almacenamiento del
 *  DTO Patron utilizando un ArrayList y un Hashtable
 *  persistentes en ficheros.
 *  Colabora en el patron Fachada.
 *  @since: prototipo2.1
 *  @source: PatronesDAO.java 
 *  @version: 1.2 - 2016/06/05 
 *  @author: ajp
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import accesoDatos.DatosException;
import accesoDatos.OperacionesDAO;
import config.Configuracion;
import modelo.Mundo;
import modelo.Patron;
import modelo.Posicion;

public class PatronesDAO implements OperacionesDAO, Persistente {

	// Requerido por el Singleton 
	private static PatronesDAO instancia = null;
	
	// Elemento de almacenamiento. 
	private static ArrayList<Patron> datosPatrones;
	private static File fPatrones;
	
	/**
	 * Constructor por defecto de uso interno.
	 * Sólo se ejecutará una vez.
	 * @throws DatosException 
	 */
	private PatronesDAO() throws DatosException {
		datosPatrones = new ArrayList<Patron>();
		fPatrones = new File(Configuracion.get().getProperty("patrones.nombreFichero"));
		recuperarDatos();
	}

	/**
	 *  Método estático de acceso a la instancia única.
	 *  Si no existe la crea invocando al constructor interno.
	 *  Utiliza inicialización diferida.
	 *  Sólo se crea una vez; instancia única -patrón singleton-
	 *  @return instancia
	 */
	public static PatronesDAO getInstancia() {
		if (instancia == null) {
			try {
				instancia = new PatronesDAO();
			} catch (DatosException e) {
				// No hay datos.
				cargarPredeterminados();
			}
		}
		return instancia;
	}
	
	/**
	 *  Método para generar datos predeterminados.
	 */
	private static void cargarPredeterminados() {
		byte[][] esquemaDemo =  new byte[][]{ 
			{ 0, 0, 0, 0 }, 
			{ 1, 0, 1, 0 }, 
			{ 0, 0, 0, 1 }, 
			{ 0, 1, 1, 1 }, 
			{ 0, 0, 0, 0 }
		};
		Patron patronDemo = new Patron("Demo0", esquemaDemo);
		datosPatrones.add(patronDemo);
		guardarDatos(datosPatrones);
	}
	
	//OPERACIONES DE PERSISTENCIA.
	/**
	 *  Recupera el Arraylist datosPatrones almacenados en fichero. 
	 * @throws DatosException 
	 */
	@Override
	public void recuperarDatos() throws DatosException {
		try {
			if (fPatrones.exists()) {
				FileInputStream fisPatrones = new FileInputStream(fPatrones);
				ObjectInputStream oisPatrones = new ObjectInputStream(fisPatrones);
				datosPatrones = (ArrayList<Patron>) oisPatrones.readObject();
				oisPatrones.close();
			}
			else {
				throw new DatosException("El fichero de datos no existe...");
			}
		} 
		catch (ClassNotFoundException e) {}
		catch (IOException e) {}
	}
	
	/**
	 *  Cierra datos.
	 */
	@Override
	public void cerrar() {
		guardarDatos();
	}
	
	/**
	 *  Guarda el Arraylist de Patrones en fichero.
	 */
	@Override
	public void guardarDatos() {
		guardarDatos(datosPatrones);
	}
	
	/**
	 *  Guarda la lista recibida en el fichero de datos.
	 */
	private static void guardarDatos(List<Patron> listaPatrones) {
		try {
			FileOutputStream fosPatrones = new FileOutputStream(fPatrones);
			ObjectOutputStream oosPatrones = new ObjectOutputStream(fosPatrones);
			oosPatrones.writeObject(datosPatrones);		
			oosPatrones.flush();
			oosPatrones.close();
		} 
		catch (IOException e) {}	
	}
	
	//OPERACIONES DAO
	/**
	 * Búsqueda binaria de un Patron dado su nombre.
	 * @param nombre - el nombre del Patron a buscar.
	 * @return - el Patron encontrado; null si no existe.
	 */	
	@Override
	public Patron obtener(String nombre) {
		int inicio = 0;
		int fin = datosPatrones.size() - 1;
		int medio;
		int comparacion;					// auxiliar para la comparación de String
		while (inicio <= fin) {
			medio = (inicio + fin) / 2;
			comparacion = datosPatrones.get(medio).getNombre().compareToIgnoreCase(nombre);
			if (comparacion == 0) {
				return datosPatrones.get(medio);
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
	 * Búsqueda de Patron dado un objeto, reenvía al método que utiliza nombre.
	 * @param obj - el Patron a buscar.
	 * @return - el Patron encontrado; null si no existe.
	 */
	@Override
	public Patron obtener(Object obj)  {
		return this.obtener(((Patron) obj).getNombre());
	}
	
	/**
	 *  Alta de un nuevo Patron en orden y sin repeticiones según el campo nombre. 
	 *  Busca previamente la posición que le corresponde por búsqueda binaria.
	 * @param obj - Patron a almacenar.
	 * @throws DatosException - si ya existe.
	 */
	@Override
	public void alta(Object obj) throws DatosException {
		assert obj != null;
		Patron patron = (Patron) obj;
		int inicio = 0;
		int fin = datosPatrones.size()-1;
		int medio;
		int comparacion;					// auxiliar para la comparación de String
		while (inicio <= fin) {
			medio = (inicio + fin) / 2;     // calcula posición central
			// compara los dos id. Obtiene < 0 si id va después que medio
			comparacion = datosPatrones.get(medio).getNombre().compareTo(patron.getNombre());
			if (comparacion == 0) {			// id coincide con el comparado
				throw new DatosException("ALTA: El Patron ya existe...");
			}
			if (comparacion < 0) {			// id va después alfabéticamente 
				inicio = medio + 1;
			}
			else {				 			// id va antes alfabéticamente
				fin = medio - 1;
			}
		}
	}

	/**
	 * Elimina el objeto, dado el id utilizado para el almacenamiento.
	 * @param nombre - el nombre del Patron a eliminar.
	 * @return - el Patron eliminado.
	 * @throws DatosException - si no existe.
	 */
	@Override
	public Patron baja(String nombre) throws DatosException {
		Patron patron = obtener(nombre);
		if (patron != null) {
			// Elimina el Mundo del almacen de datos.
			datosPatrones.remove(patron);
		}	
		else {
			throw new DatosException("BAJA: El Patron no existe...");
		}
		return patron;
	}
	
	/**
	 *  Actualiza datos de un Mundo reemplazando el almacenado por el recibido.
	 *	@param obj - Patron con las modificaciones.
	 *  @throws DatosException - si no existe.
	 */
	@Override
	public void actualizar(Object obj) throws DatosException {
		Patron patron = (Patron) obj;
		Patron patronAux = obtener(patron.getNombre());
		if (patronAux != null) {	
			patronAux.setEsquema(patron.getEsquema());	
			// Actualización
			datosPatrones.set(datosPatrones.indexOf(patron), patronAux);
		}	
		else {
			throw new DatosException("ACTUALIZAR: El Patron no existe...");
		}
	}

	/**
	 * Obtiene el listado de todos los objetos Patron almacenados.
	 * @return el texto con el volcado de datos.
	 */
	@Override
	public String listarDatos() {
		StringBuilder listado = new StringBuilder();
		for (Patron patron: datosPatrones) {
			if (patron != null) {
				listado.append("\n" + patron); 
			}
		}
		return listado.toString();
	}
	
} //class