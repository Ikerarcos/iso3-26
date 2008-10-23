/**
 * 
 */
package iso3.pt.dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


/**
 * @author Iker
 *
 */
public class PtDAO {

	private static PtDAO instancia;
	private SessionFactory factory;
	//cache de asignaturas (lista indexada por id)
	
	private PtDAO(){
		factory = new Configuration().configure().buildSessionFactory();
	}
	
	// Es un SINGLETON
	public static PtDAO getInstancia(){
		if (instancia == null) {
				instancia = new PtDAO();
		}
		return instancia;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
