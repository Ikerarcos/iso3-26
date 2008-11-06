/**
 * 
 */
package iso3.pt.dao;



import iso3.pt.model.Alumno;
import iso3.pt.model.Asignatura;
import iso3.pt.model.Evaluacion;
import iso3.pt.model.Profesor;
import iso3.pt.model.Unidad;

import java.util.HashSet;
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
		
		
		Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        
        Asignatura asig1 = (Asignatura) session.get(Asignatura.class, idAsignatura);
        Alumno alum1 = (Alumno)session.get(Alumno.class,idAlumno);
        System.out.println(asig1);
        System.out.println(alum1);
        Evaluacion eval = new Evaluacion(concepto,nota);
        eval.setAlum(alum1);
        eval.setAsig(asig1);
        System.out.println(eval);
        asig1.addEvaluacion(eval);
        alum1.addEvaluacion(eval);
        
        
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
		Session session = factory.openSession();
        //Transaction tx = session.beginTransaction();
		Asignatura asig1 = (Asignatura) session.get(Asignatura.class, id);
		System.out.println(asig1);
		return asig1;
        
	}

	@Override
	public Set<Asignatura> getAsignaturas() {

		Session session = factory.openSession();
		Set<Asignatura> AsigSet = new HashSet<Asignatura>();
		List<Asignatura> Asignaturas = session.createQuery("from Asignatura as asig").list();
        
		for (Iterator<Asignatura> iter = Asignaturas.iterator(); iter.hasNext();) {
			Asignatura asig = iter.next();
			AsigSet.add(asig);
            System.out.println(asig);
        }
        session.close();
        return AsigSet;
	}

	@Override
	public Set<Asignatura> getAsignaturas(int idAlumno) {
		Session session = factory.openSession();
		Alumno alum = (Alumno) session.get(Alumno.class, idAlumno);
		return alum.getAsignaturas();
		
	}

	@Override
	public Set<Asignatura> getAsignaturasProfesor(int idProfesor) {
		Session session = factory.openSession();
		Profesor prof = (Profesor) session.get(Profesor.class, idProfesor);
		return prof.getAsignaturas();
        
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
	public Profesor getProfesor(int idAsignatura) 
	
	{
		
		Session session = factory.openSession();
        
		Asignatura asig = (Asignatura) session.get(Asignatura.class, idAsignatura);
		return asig.getProfesor();
	}

	@Override
	public Profesor getProfesorByDni(int dni) throws UserNotFoundException {
		Session session = factory.openSession();
        
		List<Profesor> profs= session.createQuery("from Profesor as prof where prof.Dni = "+dni).list();
		
		Profesor prof=null;
		for (Iterator<Profesor> iter = profs.iterator(); iter.hasNext();) {
			prof = iter.next();
			System.out.println("Encontrado Profesor: ");
            System.out.println(prof);
        }
		if (prof==null)
		{	System.out.println("Para saltar la exception");
			new UserNotFoundException();
		}
		return prof;
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
		Session session = factory.openSession();
		Profesor prof=null;
		List<Profesor> profs= session.createQuery("from Profesor as prof where prof.Dni = "+dni).list();
		for (Iterator<Profesor> iter = profs.iterator(); iter.hasNext();) 
		{
			prof = iter.next();
			System.out.println("Encontrado Profesor: ");
        }
		if (prof==null)
		{	
			new UserNotFoundException();
			return null;
		}
		else
		
		{	profs = null;
			prof = null;
			profs= session.createQuery("from Profesor as prof where prof.Dni = "+dni+" and prof.Password = '"+pass+"'").list();
			for (Iterator<Profesor> iter = profs.iterator(); iter.hasNext();) 
			{
				prof = iter.next();
				System.out.println("Contraseña correcta  Profesor: ");
	            	        }
			if (prof==null)
			{	
				new IncorrectPasswordException();
				return null;
			}
			else return prof;
			
		}
		
	}

	@Override
	public void matricular(int idAlumno, int idAsignatura) {
		// TODO Auto-generated method stub
		
	}
	
	//Para pruebas iniciales
	public void inserciones1(){
    	
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        
        Asignatura asig1 = new Asignatura(10,"ARQUI",5);
        Asignatura asig2 = new Asignatura(20,"ISO",5);
        //Alumno alum1 = new Alumno(45820650,"mooontx","David Montero","625703060");
        Profesor prof1 = new Profesor(45612485,"capullo","iker","12345678","ikerarcos@msn.com","despacho1");
        prof1.addAsignatura(asig1);
        asig1.setProfesor(prof1);
        
        
        
        //System.out.println(alum1);
       // alum1.addAsignatura(asig1);
        //alum1.addAsignatura(asig1);
        //asig1.addAlumno(alum1);
        //asig2.addAlumno(alum1);
        session.save(asig1);
        session.save(asig2);
        //session.save(alum1);
        session.save(prof1);
        System.out.println(prof1);
        System.out.println(asig1);
        System.out.println(asig2);
        
        /*List<Asignatura> Asignaturas = session.createQuery("from Asignatura as asig where asig.id in ('1','2')").list();
        
        for (Iterator<Asignatura> iter = Asignaturas.iterator(); iter.hasNext();) {
        	asig1 = iter.next();
            System.out.println(asig1);
        }*/
        
        
        
        tx.commit();
        session.close();
        System.out.println("Done inserciones1!");
	}
	/**
	 * @param args
	 * @throws UserNotFoundException 
	 * @throws IncorrectPasswordException 
	 */
	public static void main(String[] args) throws UserNotFoundException, IncorrectPasswordException {
		// TODO Auto-generated method stub
		PtDAO DAO = PtDAO.getInstancia();
		System.out.println("Inserciones:");
		instancia.inserciones1();
		System.out.println("");
		System.out.println("getProfesorByDni: ");
		System.out.println(instancia.getProfesorByDni(4561285));
		System.out.println("");
		System.out.println("getProfesorByDni: ");
		System.out.println(instancia.loginProfesor(45612485,"capullao"));
		System.out.println("");
		System.out.println("getProfesor por asignatura: ");
		System.out.println(instancia.getProfesor(1));
		System.out.println("");
		
		
		//instancia.addEvaluacion("concepto",10,1,11);
		//instancia.getAsignatura(1);
	}

}
