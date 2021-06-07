package Auxiliar;

public class Persona {
	private String nombre;
	private String apellido;
	private int DNI;
	
	
	/**
	 * 
	 * @param nombre Nombre de la persona
	 * @param apellido Apellido de la persona
	 * @param dni DNI de la persona
	 */
	
	public Persona(String nombre, String apellido, int dni) {
		this.nombre = nombre;
		this.apellido = apellido;
		DNI = dni;
	}
	
	/**
	 * 
	 * @return Nombre de la persona
	 */
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * 
	 * @param nombre Nombre de la persona
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	/**
	 * 
	 * @return Apellido de la persona
	 */
	public String getApellido() {
		return apellido;
	}
	
	/**
	 * 
	 * @param apellido Apellido de la persona
	 */
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	/**
	 * 
	 * @return DNI de la persona
	 */
	public int getDNI() {
		return DNI;
	}
	
	/**
	 * 
	 * @param dni DNI de la persona
	 */
	public void setDNI(int dni) {
		DNI = dni;
	}
	
	/**
	 * @return Cadena con los atributos de la persona separados con espacios
	 */
	public String toString() {
		return DNI + " " + apellido + " " + nombre ;
	}

	
}
