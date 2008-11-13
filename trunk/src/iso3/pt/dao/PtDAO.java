/**
 * 
 */
package iso3.pt.dao;



import iso3.pt.model.Alumno;
import iso3.pt.model.Asignatura;
import iso3.pt.model.Evaluacion;
import iso3.pt.model.Profesor;
import iso3.pt.model.Unidad;

import java.util.Collections;
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
	private boolean cacheLlena;
	
	private PtDAO(){
		factory = new Configuration().configure().buildSessionFactory();
		cache = new HashMap<Integer, Asignatura>();
		cacheLlena = false;
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
		System.out.println("");
		System.out.println("addEvaluacion...");
		Transaction tx = instancia.session.beginTransaction();
        Asignatura asig1 = null;
        //acceso cache
        if (cache.containsKey(idAsignatura))
        	asig1 = cache.get(idAsignatura);
        else{        	
        	asig1 = (Asignatura) instancia.session.get(Asignatura.class, idAsignatura);
        	cache.put(idAsignatura, asig1);
        }
        Alumno alum1 = (Alumno)instancia.session.get(Alumno.class,idAlumno);
        Evaluacion eval = new Evaluacion(concepto,nota,asig1);
        eval.setAlum(alum1);
        eval.setAsig(asig1);
        //no se puede hacer esto
        //asig1.addEvaluacion(eval);
        alum1.addEvaluacion(eval);
        //actualización cache
        cache.remove(idAsignatura);
        cache.put(idAsignatura, asig1);       
        instancia.session.save(asig1);
        instancia.session.save(alum1);
        instancia.session.save(eval);
        tx.commit();
        System.out.println("Done addEvaluacion!");
	}

	@Override
	public void desmatricular(int idAlumno, int idAsignatura) {
		// TODO Auto-generated method stub
		System.out.println("");
		System.out.println("desmatricular...");
		Transaction tx = instancia.session.beginTransaction();
		Alumno alum1 = (Alumno)instancia.session.get(Alumno.class,idAlumno);
		Asignatura asig1 = null;
		if (cache.containsKey(idAsignatura))
        	asig1 = cache.get(idAsignatura);
        else{        	
        	asig1 = (Asignatura) instancia.session.get(Asignatura.class, idAsignatura);
        	cache.put(idAsignatura, asig1);
        }
		alum1.removeAsignatura(asig1);
        asig1.removeAlumno(alum1);
        cache.remove(idAsignatura);
        cache.put(idAsignatura, asig1);       
        instancia.session.save(asig1);
        instancia.session.save(alum1);
        tx.commit();
        System.out.println("Done desmatricular!");
	}

	@Override
	public Alumno getAlumno(int id) {
		// TODO Auto-generated method stub		
		Alumno alum1 = (Alumno)instancia.session.get(Alumno.class,id);
		return alum1;
	}

	@Override
	public Set<Alumno> getAlumnos(int idAsignatura) {
		// TODO Auto-generated method stub
		Asignatura asig1 = null;
		if (cache.containsKey(idAsignatura))
        	asig1 = cache.get(idAsignatura);
        else{        	
        	asig1 = (Asignatura) instancia.session.get(Asignatura.class, idAsignatura);
        	cache.put(idAsignatura, asig1);
        }
		return asig1.getAlumnos();
	}

	@Override
	public Asignatura getAsignatura(int id) {
		// TODO Auto-generated method stub
		Asignatura asig1 = null;
		if (cache.containsKey(id))
        	asig1 = cache.get(id);
        else{        	
        	asig1 = (Asignatura) instancia.session.get(Asignatura.class, id);
        	cache.put(id, asig1);
        }
		return asig1;
        
	}

	@Override
	public Set<Asignatura> getAsignaturas() {
		//NO ES OPTIMO, HACE SIEMPRE LA QUERY... COMO HACERLO CON CACHE?		
		if(!cacheLlena){
			Set<Asignatura> AsigSet = new HashSet<Asignatura>();
			AsigSet = (Set)instancia.session.createQuery("from Asignatura as asig").list();
			Asignatura asig = null;
			int id;
			for (Iterator<Asignatura> iter = AsigSet.iterator(); iter.hasNext();) {
				asig = iter.next();
				id = asig.getId();
				if (!cache.containsKey(id)){        	
		        	asig = (Asignatura) instancia.session.get(Asignatura.class, id);
		        	cache.put(id, asig);
		        }
	        }
			return AsigSet;
		}
		else{
			return (Set)cache.values();
		}        
	}

	@Override
	public Set<Asignatura> getAsignaturas(int idAlumno) {
		Alumno alum = (Alumno) instancia.session.get(Alumno.class, idAlumno);
		return alum.getAsignaturas();
		
	}

	@Override
	public Set<Asignatura> getAsignaturasProfesor(int idProfesor) {
		//no se puede hacer esto, hay que recorrer las asignaturas		
		//Profesor prof = (Profesor) session.get(Profesor.class, idProfesor);
		//prof.getAsignaturas();
		Set<Asignatura> asigsProf = new HashSet<Asignatura>();
		Set<Asignatura> asigs = new HashSet<Asignatura>();
		instancia.getAsignaturas();
		asigs = (Set)cache.values();
		Asignatura asig = null;
		for(Iterator<Asignatura> iter = asigs.iterator(); iter.hasNext();){
			asig = iter.next();
			if(asig.getProfesor().getDni() == idProfesor)
				asigsProf.add(asig);
		}
		return asigsProf;
        
	}

	@Override
	public Set<Evaluacion> getEvaluaciones(int idAsignatura, int idAlumno) {
		// TODO Auto-generated method stub
		Asignatura asig1 = null;
		if (!cache.containsKey(idAsignatura)){        	
        	asig1 = (Asignatura) instancia.session.get(Asignatura.class, idAsignatura);
        	cache.put(idAsignatura, asig1);
        }
		//PROBAR SI ESTO FUNCIONA!!!!
		Set<Evaluacion> Evaluaciones = (Set)instancia.session.createQuery("from Evaluacion as eval where eval.asig = "+idAsignatura+" and eval.alum = "+idAlumno).list();
        
        /*for (Iterator<Evaluacion> iter = Evaluaciones.iterator(); iter.hasNext();) {
        	Evaluacion emp1 = iter.next();
        }*/
        return Evaluaciones;
	}
	
	@Override
	public List<Evaluacion> getEvaluacionesAsignatura(int idAsignatura) {
		// TODO Auto-generated method stub 
        //PROBAR SI FUNCIONA!!!
		List<Evaluacion> Evaluaciones = instancia.session.createQuery("from Evaluacion as eval where eval.asig = "+idAsignatura).list();      
        /*for (Iterator<Evaluacion> iter = Evaluaciones.iterator(); iter.hasNext();) {
        	Evaluacion eval = iter.next();
        }*/
        return Evaluaciones;
	}

	@Override
	public List<Evaluacion> getEvaluacionesOrderedByAsignatura(int idAlumno) {
		// TODO Auto-generated method stub
		//PROBAR SI FUNCIONA!!!
		List<Evaluacion> Evaluaciones = instancia.session.createQuery("from Evaluacion as eval where eval.alum = "+idAlumno+" order by Asignatura").list();      
        /*for (Iterator<Evaluacion> iter = Evaluaciones.iterator(); iter.hasNext();) {
        	Evaluacion eval = iter.next();
        }*/
        return Evaluaciones;
	}

	@Override
	public Profesor getProfesor(int idAsignatura) 	{
		Asignatura asig1 = null;
		if (cache.containsKey(idAsignatura))
        	asig1 = cache.get(idAsignatura);
        else{        	
        	asig1 = (Asignatura) instancia.session.get(Asignatura.class, idAsignatura);
        	cache.put(idAsignatura, asig1);
        }
		return asig1.getProfesor();
	}

	@Override
	public Profesor getProfesorByDni(int dni) throws UserNotFoundException {        
		Profesor prof= (Profesor)instancia.session.createQuery("from Profesor as prof where prof.Dni = "+dni);
		if (prof == null){
			new UserNotFoundException();
		}
		return prof;
	}

	@Override
	public Set<Unidad> getUnidades(int idAsignatura) {
		// TODO Auto-generated method stub
		Asignatura asig1 = null;
		if (cache.containsKey(idAsignatura))
        	asig1 = cache.get(idAsignatura);
        else{        	
        	asig1 = (Asignatura) instancia.session.get(Asignatura.class, idAsignatura);
        	cache.put(idAsignatura, asig1);
        }
		return asig1.getUnidades();
	}

	@Override
	public Alumno loginAlumno(int dni, String pass)
			throws UserNotFoundException, IncorrectPasswordException {
		// TODO Auto-generated method stub
		Alumno alum = (Alumno)instancia.session.createQuery("from Alumno as alum where alum.Dni = "+dni);
		if (alum == null){	
			new UserNotFoundException();
			return null;
		}
		else{	
			alum = null;
			alum = (Alumno)instancia.session.createQuery("from Alumno as alum where alum.Dni = "+dni+" and alum.Password = '"+pass+"'");			
			if (alum == null)
			{	
				new IncorrectPasswordException();
				return null;
			}
			else return alum;			
		}		
	}

	@Override
	public Profesor loginProfesor(int dni, String pass)
			throws UserNotFoundException, IncorrectPasswordException {
		Profesor prof = (Profesor)instancia.session.createQuery("from Profesor as prof where prof.Dni = "+dni);
		if (prof == null){	
			new UserNotFoundException();
			return null;
		}
		else{	
			prof = null;
			prof = (Profesor)instancia.session.createQuery("from Profesor as prof where prof.Dni = "+dni+" and prof.Password = '"+pass+"'");			
			if (prof == null)
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
		System.out.println("");
		System.out.println("matricular...");
		Transaction tx = instancia.session.beginTransaction();
		Alumno alum1 = (Alumno)instancia.session.get(Alumno.class,idAlumno);
		Asignatura asig1 = null;
		if (cache.containsKey(idAsignatura))
        	asig1 = cache.get(idAsignatura);
        else{        	
        	asig1 = (Asignatura) instancia.session.get(Asignatura.class, idAsignatura);
        	cache.put(idAsignatura, asig1);
        }
		alum1.addAsignatura(asig1);
        asig1.addAlumno(alum1);
        cache.remove(idAsignatura);
        cache.put(idAsignatura, asig1);       
        instancia.session.save(asig1);
        instancia.session.save(alum1);
        tx.commit();
        System.out.println("Done matricular!");
	}
	
	//Para pruebas iniciales
	public void inserciones1(){
		System.out.println("");
		System.out.println("Inserciones:");
		
		Transaction tx = instancia.session.beginTransaction();
        Asignatura asig1 = new Asignatura(20,"ISO",5);
        Alumno alum1 = new Alumno(45820650,"soy gay","David Montero","625703060");
        instancia.session.save(asig1);
        instancia.session.save(alum1);
        tx.commit();
        
        instancia.matricular(alum1.getDni(), asig1.getId()); 
        
        tx = instancia.session.beginTransaction();
        Unidad unid1 = new Unidad("Tema1","la homosexualidad de montxo","montxo es gay");
        asig1.addUnidad(unid1);
        Profesor prof1 = new Profesor(45612485,"capullo","iker","12345678","ikerarcos@msn.com","despacho1");
        asig1.setProfesor(prof1);
        
        //PODRÍA HABER UNIDADES SIN ASIGNATURA
        //MAL!!! (EN LA BD TB PODRÍA?)
        instancia.session.save(unid1);
        instancia.session.save(prof1);
        tx.commit();
        
        instancia.addEvaluacion("Cantidad de homesexualidad", 10, asig1.getId(), alum1.getDni());                            
               
        //LOS PROFESORES NO CONOCEN SUS ASIGNATURAS
        //prof1.addAsignatura(asig1);mal!!!   
        
        instancia.desmatricular(alum1.getDni(), asig1.getId());
        
        System.out.println("");
        System.out.println("Done inserciones1!");
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
		
		instancia.inserciones1();
		
		/*System.out.println("");
		System.out.println("Llenando cache...");
		Set<Asignatura> AsigSet = new HashSet<Asignatura>();
		Asignatura asig;
		AsigSet = instancia.getAsignaturas();
		for(Iterator<Asignatura> iter = AsigSet.iterator(); iter.hasNext();){
			asig = iter.next();
			instancia.cache.put(asig.getId(), asig);
		}
		AsigSet.clear();
        System.out.println("Done cache!");*/
		
		/*System.out.println("");
		System.out.println("getProfesorByDni: ");
		System.out.println(instancia.getProfesorByDni(4561285));
		System.out.println("");
		System.out.println("loginProfesor: ");
		System.out.println(instancia.loginProfesor(45612485,"capullo"));
		System.out.println("");
		System.out.println("getProfesor por asignatura: ");
		System.out.println(instancia.getProfesor(1));
		System.out.println("");*/
		
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
