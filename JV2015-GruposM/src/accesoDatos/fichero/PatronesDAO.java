package accesoDatos.fichero;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
/** 
 * Proyecto: Juego de la vida.
 *  Resuelve todos los aspectos del almacenamiento del
 *  DTO Patron utilizando un ArrayList persistente en fichero.
 *  Colabora en el patron Fachada.
 *  @since: prototipo2
 *  @source: PatronesDAO.java 
 *  @version: 1.0 - 2016/05/23 
 *  @author: ajp
 */
import java.util.ArrayList;

import modelo.Mundo;
import modelo.Patron;
import accesoDatos.DatosException;
import accesoDatos.OperacionesDAO;
import accesoDatos.test.DatosPrueba;

public class PatronesDAO implements OperacionesDAO, Persistente {

	// Requerido por el Singleton 
	private static PatronesDAO instancia = null;
	
	// Elemento de almacenamiento. 
	private ArrayList<Patron> datosPatrones;
	private File fPatrones;
	
	/**
	 * Constructor por defecto de uso interno.
	 * Sólo se ejecutará una vez.
	 */
	private PatronesDAO() {
		datosPatrones = new ArrayList<Patron>();
		fPatrones = new File("patrones.dat");
		try {
			recuperarDatos();
		} catch (DatosException e) {
		}
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
			instancia = new PatronesDAO();
		}
		return instancia;
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
	 *  Guarda el Arraylist de Patrones en fichero.
	 */
	@Override
	public void guardarDatos() {
		try {
			FileOutputStream fosPatrones = new FileOutputStream(fPatrones);
			ObjectOutputStream oosPatrones = new ObjectOutputStream(fosPatrones);
			oosPatrones.writeObject(datosPatrones);		
			oosPatrones.flush();
			oosPatrones.close();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
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
				throw new DatosException("El Patron ya existe...");
			}
			if (comparacion < 0) {			// id va después alfabéticamente 
				inicio = medio + 1;
			}
			else {				 			// id va antes alfabéticamente
				fin = medio - 1;
			}
		}
	}

	@Override
	public Object baja(String id) throws DatosException {
		
		return null;
	}

	@Override
	public void actualizar(Object obj) throws DatosException {
		
		
	}

	@Override
	public String listarDatos() {
		
		return null;
	} 
	
} //class
