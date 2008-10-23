/**
 * 
 */
package iso3.pt.model;

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
	
	private Profesor Profe;
	private Set<Alumno> Alumnos;//carga Lazy
	private Set<Unidad> Unidades;//no hay unidades sin asignatura
								//carga Lazy y tratamiento en cascada
	protected Asignatura(){
		
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
	 * @param profe the profe to set
	 */
	public void setProfe(Profesor profe) {
		Profe = profe;
	}

	/**
	 * @return the profe
	 */
	public Profesor getProfe() {
		return Profe;
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
	
}
