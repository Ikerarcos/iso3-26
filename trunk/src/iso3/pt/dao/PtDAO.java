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
import java.util.Map;
import java.util.HashMap;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
//import org.hibernate.mapping.List;
//import org.hibernate.*;



/**
 * @author Iker
 *
 */
public class PtDAO implements IPtDao{

	private static PtDAO instancia;
	private SessionFactory factory;
	private Session session;
	 
	//cache de asignaturas (lista indexada por id)
	private Map<Integer, Asignatura> cache;
	
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
		
		
		//Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        
        //cache
        Asignatura asig1 = cache.get(idAsignatura);
        //Asignatura asig1 = (Asignatura) session.get(Asignatura.class, idAsignatura);
        
        Alumno alum1 = (Alumno)session.get(Alumno.class,idAlumno);
        System.out.println(asig1);
        System.out.println(alum1);
        Evaluacion eval = new Evaluacion(concepto,nota,asig1);
        eval.setAlum(alum1);
        eval.setAsig(asig1);
        System.out.println(eval);
        //no se puede hacer esto
        //asig1.addEvaluacion(eval);
        alum1.addEvaluacion(eval);
        cache.remove(idAsignatura);
        cache.put(asig1.getId(), asig1);
        
        session.save(asig1);
        session.save(alum1);
        session.save(eval);
        
        tx.commit();
        //session.close();
        System.out.println("Done addEvaluacion!");
		
	}

	@Override
	public void desmatricular(int idAlumno, int idAsignatura) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Alumno getAlumno(int id) {
		// TODO Auto-generated method stub
		Alumno alum1 = (Alumno)session.get(Alumno.class,id);
		return alum1;
	}

	@Override
	public Set<Alumno> getAlumnos(int idAsignatura) {
		// TODO Auto-generated method stub
		Asignatura asig1 = cache.get(idAsignatura);
		return asig1.getAlumnos();
	}

	@Override
	public Asignatura getAsignatura(int id) {
		// TODO Auto-generated method stub
		//Session session = factory.openSession();
        //Transaction tx = session.beginTransaction();
		//Asignatura asig1 = (Asignatura) session.get(Asignatura.class, id);
		//System.out.println(asig1);
		return cache.get(id);
        
	}

	@Override
	public Set<Asignatura> getAsignaturas() {

		//Session session = factory.openSession();
		Set<Asignatura> AsigSet = new HashSet<Asignatura>();
		List<Asignatura> Asignaturas = session.createQuery("from Asignatura as asig").list();
        
		for (Iterator<Asignatura> iter = Asignaturas.iterator(); iter.hasNext();) {
			Asignatura asig = iter.next();
			AsigSet.add(asig);
            System.out.println(asig);
        }
        //session.close();
        return AsigSet;
	}

	@Override
	public Set<Asignatura> getAsignaturas(int idAlumno) {
		//Session session = factory.openSession();
		Alumno alum = (Alumno) session.get(Alumno.class, idAlumno);
		return alum.getAsignaturas();
		
	}

	@Override
	public Set<Asignatura> getAsignaturasProfesor(int idProfesor) {
		//Session session = factory.openSession();
		Profesor prof = (Profesor) session.get(Profesor.class, idProfesor);
		//no se puede hacer esto, hay que recorrer las asignaturas
		//prof.getAsignaturas();
		return null;
        
	}

	@Override
	public Set<Evaluacion> getEvaluaciones(int idAsignatura, int idAlumno) {
		// TODO Auto-generated method stub
		//Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        
        
       
        Set<Evaluacion> Evaluaciones = (Set)session.createQuery("from Evaluacion as eval where eval.asig = "+idAsignatura+" and eval.alum = "+idAlumno).list();
        
        /*for (Iterator<Evaluacion> iter = Evaluaciones.iterator(); iter.hasNext();) {
        	Evaluacion emp1 = iter.next();
            System.out.println(emp1);
        }*/
        tx.commit();
        //session.close();
        return Evaluaciones;
	}

	@Override
	public List<Evaluacion> getEvaluacionesAsignatura(int idAsignatura) {
		// TODO Auto-generated method stub
		//Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        
        
        
        List<Evaluacion> Evaluaciones = session.createQuery("from Evaluacion as eval where eval.asig = "+idAsignatura).list();
        
        for (Iterator<Evaluacion> iter = Evaluaciones.iterator(); iter.hasNext();) {
        	Evaluacion eval = iter.next();
            System.out.println(eval);
        }
        //session.close();
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
		
		//Session session = factory.openSession();
        
		Asignatura asig = (Asignatura) session.get(Asignatura.class, idAsignatura);
		return asig.getProfesor();
	}

	@Override
	public Profesor getProfesorByDni(int dni) throws UserNotFoundException {
		//Session session = factory.openSession();
        
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
		//Session session = factory.openSession();
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
				System.out.println("Contrase�a correcta  Profesor: ");
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
    	
        //Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        
        //Asignatura asig2 = new Asignatura(10,"ARQUI",5);
        Asignatura asig1 = new Asignatura(20,"ISO",5);
        Alumno alum1 = new Alumno(45820650,"soy gay","David Montero","625703060");
        //esto es matricular:
        alum1.addAsignatura(asig1);
        asig1.addAlumno(alum1); 
        //hasta aqu�
        Unidad unid1 = new Unidad("Tema1","la homosexualidad de montxo","montxo es gay");
        asig1.addUnidad(unid1);
        Profesor prof1 = new Profesor(45612485,"capullo","iker","12345678","ikerarcos@msn.com","despacho1");
        asig1.setProfesor(prof1);
        
        Evaluacion eval1 = new Evaluacion("Cantidad de homesexualidad", 10, asig1);
        alum1.addEvaluacion(eval1);
        eval1.setAsig(asig1);
        eval1.setAlum(alum1);
        
        //PODR�A HABER UNIDADES SIN ASIGNATURA
        //MAL!!! (EN LA BD TB PODR�A?)
               
        //LOS PROFESORES NO CONOCEN SUS ASIGNATURAS
        //prof1.addAsignatura(asig1);mal!!!   
        
        session.save(asig1);        
        session.save(alum1);
        session.save(unid1);
        session.save(prof1);
        session.save(eval1);        
        System.out.println(asig1);
        System.out.println(alum1);
        System.out.println(unid1);
        System.out.println(eval1);
        System.out.println(prof1);
        
        /*List<Asignatura> Asignaturas = session.createQuery("from Asignatura as asig where asig.id in ('1','2')").list();
        
        for (Iterator<Asignatura> iter = Asignaturas.iterator(); iter.hasNext();) {
        	asig1 = iter.next();
            System.out.println(asig1);
        }*/
        
        
        
        tx.commit();
        //session.close();        
	}
	/**
	 * @param args
	 * @throws UserNotFoundException 
	 * @throws IncorrectPasswordException 
	 */
	public static void main(String[] args) throws UserNotFoundException, IncorrectPasswordException {
		// TODO Auto-generated method stub
		PtDAO.getInstancia();
		instancia.session = instancia.factory.openSession();
		System.out.println("");
		System.out.println("Inserciones:");
		instancia.inserciones1();
		System.out.println("Done inserciones1!");
		System.out.println("");
		System.out.println("Llenando cache...");
		instancia.cache = new HashMap<Integer, Asignatura>();
		Set<Asignatura> AsigSet = new HashSet<Asignatura>();
		Asignatura asig;
		AsigSet = instancia.getAsignaturas();
		for(Iterator<Asignatura> iter = AsigSet.iterator(); iter.hasNext();){
			asig = iter.next();
			instancia.cache.put(asig.getId(), asig);
		}
		AsigSet.clear();
        System.out.println("Done cache!");
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
		
		//instancia.getEvaluaciones(1, idAlumno)
		/*for (Iterator<Evaluacion> iter = Evaluaciones.iterator(); iter.hasNext();) {
    	Evaluacion emp1 = iter.next();
        System.out.println(emp1);
    	}*/
		
		//instancia.addEvaluacion("concepto",10,1,11);
		//instancia.getAsignatura(1);
		
		instancia.session.close();
	}

}
