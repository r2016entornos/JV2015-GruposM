package accesoDatos.fichero;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import accesoDatos.DatosException;
import accesoDatos.OperacionesDAO;
import accesoDatos.test.DatosPrueba;
import modelo.Mundo;
import modelo.SesionUsuario;

public class MundosDAO implements OperacionesDAO, Persistente {

	// Requerido por el patrón Singleton
	private static MundosDAO instancia;

	// Elementos de almacenamiento.
	private ArrayList<Mundo> datosMundos;
	private File fMundos;

	/**
	 * Constructor por defecto de uso interno.
	 * Sólo se ejecutará una vez.
	 */
	private MundosDAO() {
		datosMundos = new ArrayList<Mundo>();
		fMundos = new File("mundos.dat");
		try {
			recuperarDatos();
		} catch (DatosException e) {}
	}

	/**
	 *  Método estático de acceso a la instancia única.
	 *  Si no existe la crea invocando al constructor interno.
	 *  Utiliza inicialización diferida.
	 *  Sólo se crea una vez; instancia única -patrón singleton-
	 *  @return instancia
	 */
	public static MundosDAO getInstancia() {
		if (instancia == null) {
			instancia = new MundosDAO();
		}
		return instancia;
	}

	//OPERACIONES DE PERSISTENCIA.
	/**
	 *  Recupera el Arraylist datosMundos almacenados en fichero. 
	 * @throws DatosException 
	 */
	@Override
	public void recuperarDatos() throws DatosException {
		try {
			if (fMundos.exists()) {
				FileInputStream fisMundos = new FileInputStream(fMundos);
				ObjectInputStream oisMundos = new ObjectInputStream(fisMundos);
				datosMundos = (ArrayList<Mundo>) oisMundos.readObject();
				oisMundos.close();
			}
			else {
				throw new DatosException("El fichero de datos no existe...");
			}
		} 
		catch (ClassNotFoundException e) {} 
		catch (IOException e) {}
	}
	
	/**
	 *  Guarda el Arraylist de mundos en fichero.
	 */
	@Override
	public void guardarDatos() {
		try {
			FileOutputStream fosMundos = new FileOutputStream(fMundos);
			ObjectOutputStream oosSesiones = new ObjectOutputStream(fosMundos);
			oosSesiones.writeObject(datosMundos);		
			oosSesiones.flush();
			oosSesiones.close();
		} 
		catch (IOException e) {}
	}

	//OPERACIONES DAO
	/**
	 * Obtiene el objeto dado el id utilizado para el almacenamiento.
	 * @param id - el mundo de Mundo a obtener.
	 * @return - el Mundo encontrado; null si no existe.
	 */	
	@Override
	public Mundo obtener(String nombreMundo) {
		assert nombreMundo != null;
		int comparacion;
		int inicio = 0;
		int fin = datosMundos.size() - 1;
		int medio;
		while (inicio <= fin) {
			medio = (inicio + fin) / 2;
			comparacion = datosMundos.get(medio).getNombre()
					.compareToIgnoreCase(nombreMundo);
			if (comparacion == 0) {
				return datosMundos.get(medio);
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
	 * Búsqueda de Usuario dado un objeto, reenvía al método que utiliza nombre.
	 * @param obj - el Mundo a buscar.
	 * @return - el Mundo encontrado; null si no existe.
	 */
	@Override
	public Mundo obtener(Object obj)  {
		return this.obtener(((Mundo) obj).getNombre());
	}

	/**
	 * Búsqueda de Mundo.
	 * @param mundo - el Mundo a buscar.
	 * @return - el Mundo encontrado o null si no existe.
	 */
	public Mundo buscarMundo(Mundo mundo) {
		assert mundo != null;
		return this.obtener(mundo.getNombre());				
	}

	/**
	 *  Alta de un objeto en el almacén de datos, 
	 *  sin repeticiones, según el campo id previsto. 
	 *	@param obj - Objeto a almacenar.
	 *  @throws DatosException - si ya existe.
	 */
	@Override
	public void alta(Object obj) throws DatosException {
		assert obj != null;
		Mundo mundo = (Mundo) obj; 
		int comparacion;
		int inicio = 0;
		int fin = datosMundos.size() - 1;
		int medio = 0;
		while (inicio <= fin) {
			medio = (inicio + fin) / 2;			// Calcula posición central.
			// compara los dos id. Obtiene < 0 si id va después que medio.
			comparacion = datosMundos.get(medio).getNombre()
					.compareToIgnoreCase(mundo.getNombre());
			if (comparacion == 0) {			
				throw new DatosException("El Mundo ya existe...");   				  
			}		
			if (comparacion < 0) {
				inicio = medio + 1;
			}			
			else {
				fin = medio - 1;
			}
		}	
		datosMundos.add(inicio, mundo); 	// Inserta el mundo en orden.		
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
		// TODO Auto-generated method stub
		return null;
	}

} // class
