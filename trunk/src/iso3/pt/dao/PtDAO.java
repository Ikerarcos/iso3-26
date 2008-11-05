/**
 * 
 */
package iso3.pt.dao;

import iso3.pt.model.Alumno;
import iso3.pt.model.Asignatura;
import iso3.pt.model.Evaluacion;
import iso3.pt.model.Profesor;
import iso3.pt.model.Unidad;

import java.util.Set;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.mapping.List;


/**
 * @author Iker
 *
 */
public class PtDAO implements IPtDao{

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

	@Override
	public void addEvaluacion(String concepto, float nota, int idAsignatura,
			int idAlumno) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void desmatricular(int idAlumno, int idAsignatura) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Alumno getAlumno(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Alumno> getAlumnos(int idAsignatura) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Asignatura getAsignatura(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Asignatura> getAsignaturas() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Asignatura> getAsignaturas(int idAlumno) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Asignatura> getAsignaturasProfesor(int idProfesor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Evaluacion> getEvaluaciones(int idAsignatura, int idAlumno) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List getEvaluacionesAsignatura(int idAsignatura) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List getEvaluacionesOrderedByAsignatura(int idAlumno) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Profesor getProfesor(int idAsignatura) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Profesor getProfesorByDni(int dni) throws UserNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Unidad> getUnidades(int idAsignatura) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Alumno loginAlumno(int dni, String pass)
			throws UserNotFoundException, IncorrectPasswordException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Profesor loginProfesor(int dni, String pass)
			throws UserNotFoundException, IncorrectPasswordException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void matricular(int idAlumno, int idAsignatura) {
		// TODO Auto-generated method stub
		
	}

}
