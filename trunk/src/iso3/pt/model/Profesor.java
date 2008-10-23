/**
 * 
 */
package iso3.pt.model;

/**
 * @author Iker
 *
 */
public class Profesor {

	private int Id;//autonumerico
	private int Dni;
	private String Password;
	private String Nombre;//unique y not null
	private String Telefono;
	private String Email;
	private String Despacho;
	
	protected Profesor(){
		
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
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		Email = email;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return Email;
	}
	/**
	 * @param despacho the despacho to set
	 */
	public void setDespacho(String despacho) {
		Despacho = despacho;
	}
	/**
	 * @return the despacho
	 */
	public String getDespacho() {
		return Despacho;
	}
	
}
