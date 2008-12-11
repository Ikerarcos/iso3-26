/**
 * 
 */
package iso3.pt.model;

/**
 * @author Iker
 *
 */
public class Unidad {

	//no hay unidades sin asignatura
	
	private int Id;//autonumerico
	private String Acronimo;//unique y not null
	private String Titulo;//not null
	private String Contenido;
	
	public Unidad(String acr, String tit, String cont){
		super();
		Acronimo = acr;
		Titulo = tit;
		Contenido = cont;
	}
	
	protected Unidad(){
		
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
	 * @param acronimo the acronimo to set
	 */
	public void setAcronimo(String acronimo) {
		Acronimo = acronimo;
	}
	/**
	 * @return the acronimo
	 */
	public String getAcronimo() {
		return Acronimo;
	}
	/**
	 * @param titulo the titulo to set
	 */
	public void setTitulo(String titulo) {
		Titulo = titulo;
	}
	/**
	 * @return the titulo
	 */
	public String getTitulo() {
		return Titulo;
	}
	/**
	 * @param contenido the contenido to set
	 */
	public void setContenido(String contenido) {
		Contenido = contenido;
	}
	/**
	 * @return the contenido
	 */
	public String getContenido() {
		return Contenido;
	}
	
	public String toString(){
		return "(" + this.Id + "|" + this.Acronimo + "|" + this.Titulo + "|" + this.Contenido + ")";
	}
	
}
