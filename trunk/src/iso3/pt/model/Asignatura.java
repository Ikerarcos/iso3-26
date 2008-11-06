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
public class Asignatura {

	private int Id;//autonumerico
	private int Codigo;//unique y not null
	private String Nombre;//not null
	private float Creditos;
	
	private Profesor Profesor;
	private Set<Alumno> Alumnos;//carga Lazy
	private Set<Unidad> Unidades;//no hay unidades sin asignatura
								//carga Lazy y tratamiento en cascada
	private Set<Evaluacion> Evaluaciones;//carga Lazy
	public Asignatura(int codigo, String nombre, float creditos) {
		super();
		Codigo = codigo;
		Nombre = nombre;
		Creditos = creditos;
		Alumnos = new HashSet<Alumno>();
		Unidades = new HashSet<Unidad>();
		Evaluaciones = new HashSet<Evaluacion>();
	}

	protected Asignatura(){
		Alumnos = new HashSet<Alumno>();
		Unidades = new HashSet<Unidad>();
		Evaluaciones = new HashSet<Evaluacion>();
	}
	
	private boolean estaMatriculado(Alumno al){
		if (Alumnos.contains(al))
			return true;
		else
			return false;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		Id = id;
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return Id;
	}
	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(int codigo) {
		Codigo = codigo;
	}
	/**
	 * @return the codigo
	 */
	public int getCodigo() {
		return Codigo;
	}
	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return Nombre;
	}
	/**
	 * @param creditos the creditos to set
	 */
	public void setCreditos(float creditos) {
		Creditos = creditos;
	}
	/**
	 * @return the creditos
	 */
	public float getCreditos() {
		return Creditos;
	}

	

	/**
	 * @param alumnos the alumnos to set
	 */
	public void setAlumnos(Set<Alumno> alumnos) {
		Alumnos = alumnos;
	}

	/**
	 * @return the alumnos
	 */
	public Set<Alumno> getAlumnos() {
		return Alumnos;
	}
	
	public void addAlumno(Alumno al){
		Alumnos.add(al);
	}
	
	public void removeAlumno(Alumno al){
		Alumnos.remove(al);
	}
////////////////////////////Unidades
	/**
	 * @param unidades the unidades to set
	 */
	public void setUnidades(Set<Unidad> unidades) {
		Unidades = unidades;
	}

	/**
	 * @return the unidades
	 */
	public Set<Unidad> getUnidades() {
		return Unidades;
	}
	
	public void addUnidad(Unidad un){
		Unidades.add(un);
	}
	
	public void removeUnidad(Unidad un){
		Unidades.remove(un);
	}

	
	
	
	
	
	////////////////////////////Evaluaciones
	public void setEvaluaciones(Set<Evaluacion> evaluaciones) {
		Evaluaciones = evaluaciones;
	}

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
		return "(" + this.Id + "|" + this.Codigo + "|" + this.Nombre + "|" + this.Creditos + ")";
	}

	public void setProfesor(Profesor profesor) {
		Profesor = profesor;
	}

	public Profesor getProfesor() {
		return Profesor;
	}
}
