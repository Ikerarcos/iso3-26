/**
 * 
 */
package iso3.pt.model;

/**
 * @author Iker
 *
 */
public class Evaluacion {

	//no hay evaluacion sin asignatura ni alumno
	
	private int Id;//autonumerico
	private String Concepto;//not null
	private float Nota;//not null
	
	private Alumno Alumno;
	private Asignatura Asignatura;
	
	public Evaluacion(String concepto, float nota, Asignatura asig) {
		super();
		Concepto = concepto;
		Nota = nota;
		Asignatura = asig;
	}
	public Evaluacion(){
		
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
	 * @param concepto the concepto to set
	 */
	public void setConcepto(String concepto) {
		Concepto = concepto;
	}
	/**
	 * @return the concepto
	 */
	public String getConcepto() {
		return Concepto;
	}
	/**
	 * @param nota the nota to set
	 */
	public void setNota(float nota) {
		Nota = nota;
	}
	/**
	 * @return the nota
	 */
	public float getNota() {
		return Nota;
	}
	/**
	 * @param alum the alum to set
	 */
	public void setAlum(Alumno alum) {
		Alumno = alum;
	}
	/**
	 * @return the alum
	 */
	public Alumno getAlum() {
		return Alumno;
	}
	/**
	 * @param asig the asig to set
	 */
	public void setAsig(Asignatura asig) {
		Asignatura = asig;
	}
	/**
	 * @return the asig
	 */
	public Asignatura getAsig() {
		return Asignatura;
	}
	
	public String toString()
	{	return "(" + this.Id + "|" + this.Concepto + "|" + this.Nota + "|" + this.Alumno + "|"+ this.Asignatura + ")";
	}
	
}
