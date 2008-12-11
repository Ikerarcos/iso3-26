/**
 * 
 */
package iso3.pt.model;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Iker
 *
 */
public class Alumno {

	private int Dni;//asignado por usuario
	private String Password;
	private String Nombre;//not null
	private String Telefono;
	
	private Set<Asignatura> Asignaturas;//carga lazy
	private Set<Evaluacion> Evaluaciones;//carga lazy y tratamiento cascada
	
	public Alumno(int dni, String password, String nombre, String telefono) {
		super();
		Dni = dni;
		Password = password;
		setNombre(nombre);
		Telefono = telefono;
		Asignaturas = new HashSet<Asignatura>();
		Evaluaciones = new HashSet<Evaluacion>();
	}
	protected Alumno(){
		Asignaturas = new HashSet<Asignatura>();
		Evaluaciones = new HashSet<Evaluacion>();
	}
	/**
	 * @param dni the dni to set
	 */
	public void setDni(int dni) {
		Dni = dni;
	}
	/**
	 * @return the dni
	 */
	public int getDni() {
		return Dni;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		Password = password;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return Password;
	}
	/**
	 * @param nombre the nombre to set
	 */
	private void setNombre(String nombre) {
		Nombre = nombre;
	}
	/**
	 * @return the nombre
	 */
	private String getNombre() {
		System.out.println("getnombre en alumno");
		System.out.println(Nombre);
		return Nombre;
	}
	/**
	 * @param telefono the telefono to set
	 */
	public void setTelefono(String telefono) {
		Telefono = telefono;
	}
	/**
	 * @return the telefono
	 */
	public String getTelefono() {
		return Telefono;
	}
	/**
	 * @param asignaturas the asignaturas to set
	 */
	public void setAsignaturas(Set<Asignatura> asignaturas) {
		Asignaturas = asignaturas;
	}
	/**
	 * @return the asignaturas
	 */
	public Set<Asignatura> getAsignaturas() {
		return Asignaturas;
	}
	
	public void addAsignatura(Asignatura as){
		Asignaturas.add(as);
	}
	
	public void removeAsignatura(Asignatura as){
		Asignaturas.remove(as);
	}
	
	/**
	 * @param evaluaciones the evaluaciones to set
	 */
	public void setEvaluaciones(Set<Evaluacion> evaluaciones) {
		Evaluaciones = evaluaciones;
	}
	/**
	 * @return the evaluaciones
	 */
	public Set<Evaluacion> getEvaluaciones() {
		return Evaluaciones;
	}
	
	public void addEvaluacion(Evaluacion ev){
		Evaluaciones.add(ev);
	}
	
	public void removeEvaluacion(Evaluacion ev){
		Evaluaciones.remove(ev);
	}
	
	public String toString(){
		return "(" + this.Dni + "|" + this.Password + "|" + this.getNombre() + "|" + this.Telefono + ")";
	}
}
