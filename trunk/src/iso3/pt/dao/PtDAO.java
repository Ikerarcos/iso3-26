/**
 * 
 */
package iso3.pt.dao;



import iso3.pt.model.Alumno;
import iso3.pt.model.Asignatura;
import iso3.pt.model.Evaluacion;
import iso3.pt.model.Profesor;
import iso3.pt.model.Unidad;

import java.util.Iterator;
import java.util.Set;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
//import org.hibernate.mapping.List;
import org.hibernate.*;



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
	
	

	@Override
	public void addEvaluacion(String concepto, float nota, int idAsignatura,
			int idAlumno) {
		// TODO Auto-generated method stub
		
		Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        
        Asignatura asig1 = (Asignatura) session.get(Asignatura.class, idAsignatura);
        Alumno alum1 = (Alumno)session.get(Alumno.class,idAlumno);
        System.out.println(asig1);
        System.out.println(alum1);
        Evaluacion eval = new Evaluacion(concepto,nota);
        asig1.addEvaluacion(eval);
        alum1.addEvaluacion(eval);
        eval.setAlum(alum1);
        eval.setAsig(asig1);
        
        session.save(asig1);
        session.save(alum1);
        session.save(eval);
        
        tx.commit();
        session.close();
        System.out.println("Done addEvaluacion!");
		
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
		return null;/*Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        
        
       
        List<Evaluacion> Evaluaciones = session.createQuery("from Evaluacion as eval where eval.asig = "+idAsignatura).list();
        
        for (Iterator<Evaluacion> iter = Evaluaciones.iterator(); iter.hasNext();) {
        	Evaluacion emp1 = iter.next();
            System.out.println(emp1);
        }
        session.close();
        return Evaluaciones;*/
	}

	@Override
	public List<Evaluacion> getEvaluacionesAsignatura(int idAsignatura) {
		// TODO Auto-generated method stub
		Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        
        
        
        List<Evaluacion> Evaluaciones = session.createQuery("from Evaluacion as eval where eval.asig = "+idAsignatura).list();
        
        for (Iterator<Evaluacion> iter = Evaluaciones.iterator(); iter.hasNext();) {
        	Evaluacion eval = iter.next();
            System.out.println(eval);
        }
        session.close();
        return Evaluaciones;
	}

	@Override
	public List<Evaluacion> getEvaluacionesOrderedByAsignatura(int idAlumno) {
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
	
	//Para pruebas iniciales
	public void inserciones1(){
    	
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        
        Asignatura asig1 = new Asignatura(1,"ARQUI",5);
        Asignatura asig2 = new Asignatura(2,"ISO",5);
        session.save(asig1);
        session.save(asig2);
        
        List<Asignatura> Asignaturas = session.createQuery("from Asignatura as asig where asig.id in ('1','2')").list();
        
        for (Iterator<Asignatura> iter = Asignaturas.iterator(); iter.hasNext();) {
        	Asignatura emp1 = iter.next();
            System.out.println(emp1);
        }
        
        
        
        tx.commit();
        session.close();
        System.out.println("Done inserciones1!");
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PtDAO DAO = PtDAO.getInstancia();
		DAO.inserciones1();
		//DA0.addEvaluacion("concepto",10,11,11);
	}

}
