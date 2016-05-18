package modelo;

public interface Leyes {

	/**
	 * Establece la manera en que actualiza el estado de un objeto sometido a cambios 
	 * en una simulación. 
	 * responde a la regla: El tiempo transcurre y se producen cambios...
	 * @return 
	 */
	public Object actualizarEstado();
}
