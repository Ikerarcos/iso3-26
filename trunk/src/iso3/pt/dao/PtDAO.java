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
        Asignatura asig1 = instancia.getAsignatura(idAsignatura);
        Alumno alum1 = (Alumno)instancia.session.get(Alumno.class,idAlumno);
        Evaluacion eval = new Evaluacion(concepto,nota,asig1);
        if ((alum1==null) || (asig1==null))
        	System.out.println("IDAsignatura o IDAlumno Incorrecto: no se añade la evaluacion");
        else
        	{eval.setAlum(alum1);
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
	        
	        System.out.println("Done addEvaluacion!");
        	}
        tx.commit();
	}

	@Override
	public void desmatricular(int idAlumno, int idAsignatura) {
		// TODO Auto-generated method stub
		System.out.println("");
		System.out.println("desmatricular...");
		Transaction tx = instancia.session.beginTransaction();
		Alumno alum1 = (Alumno)instancia.session.get(Alumno.class,idAlumno);
		Asignatura asig1 = instancia.getAsignatura(idAsignatura);
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
		Asignatura asig1 = instancia.getAsignatura(idAsignatura);
		if (asig1!=null)
			return asig1.getAlumnos();
		else return null;
	}

	@Override
	public Asignatura getAsignatura(int id) {
		// TODO Auto-generated method stub
		Asignatura asig1 = null;
		if (cache.containsKey(id))
        	asig1 = cache.get(id);
        else{        	
        	asig1 = (Asignatura) instancia.session.get(Asignatura.class, id);
        	if (asig1!=null)
        		cache.put(id, asig1);
        }
		return asig1;
        
	}

	@Override
	public Set<Asignatura> getAsignaturas() {
		//COMO HACERLO CON CACHE?
		//LA PRIMERA PARTE ESTÁ BIEN, EL PROBLEMA ESTÁ CUANDO HAY Q RECORRER SOLO LA CACHÉ
		
		Set<Asignatura> AsigSet=null;
		if (!cacheLlena)
		{	cacheLlena=true;
			cache.clear();
			AsigSet = new HashSet<Asignatura>();
			//AsigSet = (Set)instancia.session.createQuery("from Asignatura as asig").list(); NO SE PUEDE HACER CAST ASI
			List<Asignatura> Asigs = instancia.session.createQuery("from Asignatura as asig").list();
			Asignatura asig = null;
			
			for (Iterator<Asignatura> iter = Asigs.iterator(); iter.hasNext();) {
				asig = iter.next();
				
				if (!cache.containsKey(asig.getId())){        	
		        	asig = (Asignatura) instancia.session.get(Asignatura.class, asig.getId());
		        	cache.put(asig.getId(), asig);
		        }
				AsigSet.add(asig);//Carga del List al Set
				System.out.println(asig);
	        }
			return AsigSet;
		}
		else 
			
		{	return null;
			// NO SE KMO RECORRER LA CACHÉ
        }
		
	}

	@Override
	public Set<Asignatura> getAsignaturas(int idAlumno) {
		Alumno alum = (Alumno) instancia.session.get(Alumno.class, idAlumno);
		return alum.getAsignaturas();
		
	}

	@Override
	public Set<Asignatura> getAsignaturasProfesor(int idProfesor) {
		
		Set<Asignatura> asigsProf = new HashSet<Asignatura>();
		Set<Asignatura> asigs = new HashSet<Asignatura>();
		asigs =instancia.getAsignaturas();
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
		
		List<Evaluacion> EvaluacionesList = instancia.session.createQuery("from Evaluacion as eval where eval.Asig = "+idAsignatura+" and eval.Alum = "+idAlumno).list();
		Set<Evaluacion> Evaluaciones = new HashSet<Evaluacion>();
        
        for (Iterator<Evaluacion> iter = EvaluacionesList.iterator(); iter.hasNext();) {
        	Evaluaciones.add(iter.next());
        }
        return Evaluaciones;
	}
	
	@Override
	public List<Evaluacion> getEvaluacionesAsignatura(int idAsignatura) {
		// TODO Auto-generated method stub 
        List<Evaluacion> Evaluaciones = instancia.session.createQuery("from Evaluacion as eval where eval.Asig = "+idAsignatura).list();      
        return Evaluaciones;
	}

	@Override
	public List<Evaluacion> getEvaluacionesOrderedByAsignatura(int idAlumno) {
		
		List<Evaluacion> Evaluaciones = instancia.session.createQuery("from Evaluacion as eval where eval.Alum = "+idAlumno+"order by eval.Asig").list();
		
        return Evaluaciones;
	}

	@Override
	public Profesor getProfesor(int idAsignatura) 	{
		Asignatura asig1 = instancia.getAsignatura(idAsignatura);
		if (asig1!=null)
			return asig1.getProfesor();
		else return null;
	}

	@Override
	public Profesor getProfesorByDni(int dni) throws UserNotFoundException {        
		List<Profesor> profs= instancia.session.createQuery("from Profesor as prof where prof.Dni = "+dni).list();
		Profesor prof=null;
		
		
		if (profs.isEmpty()){
			prof=null;
		}
		else prof = profs.get(0);//Obtiene el primero, segun está puede haber mas de un profe con el mismo dni, xo no tiene sentido
		return prof;
	}

	@Override
	public Set<Unidad> getUnidades(int idAsignatura) {
		
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
		
		
		Alumno alum1  = (Alumno) instancia.session.get(Alumno.class, dni);
		if (alum1 != null)
			if (alum1.getPassword().equals(pass))
			{	
				
				return alum1;
			}
			else 
				{new IncorrectPasswordException();
				return null;
					
				}
		else
			{
			new UserNotFoundException(); 
			return null;
			}
	}
			
	

	@Override
	public Profesor loginProfesor(int dni, String pass)
			throws UserNotFoundException, IncorrectPasswordException {
		
		List<Profesor> prof = instancia.session.createQuery("from Profesor as prof where prof.Dni = "+dni).list();
		if (prof.isEmpty()){	
			new UserNotFoundException();
			return null;
		}
		else{	
			Profesor prof1 = prof.get(0);
			if (prof1.getPassword().equals(pass))
				return prof1;
			else 
			{
				new IncorrectPasswordException();
				return null;
				
			}
		}		
	}

	@Override
	public void matricular(int idAlumno, int idAsignatura) {
		
		System.out.println("");
		System.out.println("matricular...");
		Transaction tx = instancia.session.beginTransaction();
		Alumno alum1 = (Alumno)instancia.session.get(Alumno.class,idAlumno);
		Asignatura asig1 = instancia.getAsignatura(1);//Usa la funcion ya creada, busta en cache, y si no está busca y actualiza
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
		
		//instancia.inserciones1(); YA NO HACE FALTA, NO SE BORRAN UNA VEZ QUE SE CARGAN LA PRIMERA VEZ
		
		instancia.getAsignaturas();
		//instancia.getAsignaturas();
		
		System.out.println(instancia.getAsignatura(1));
		
		
				
		System.out.println("");
		System.out.println("getProfesorByDni: ");
		System.out.println(instancia.getProfesorByDni(45612485));
		System.out.println("");
		System.out.println("loginProfesor: ");
		System.out.println(instancia.loginProfesor(45612485,"capullo"));
		System.out.println("");
		System.out.println("loginAlumno: ");
		System.out.println(instancia.loginAlumno(45820650,"soy gay"));
		System.out.println("");
		System.out.println("getProfesor por asignatura: ");
		System.out.println(instancia.getProfesor(1));
		System.out.println("");
		
		//System.out.println("Prueba matricular: ");
		instancia.matricular(45820650, 1);
		//Alumnos para asignatura 1
		Set<Alumno> alum=instancia.getAsignatura(1).getAlumnos();
		for (Iterator<Alumno> iter = alum.iterator(); iter.hasNext();) {
			Alumno alum1 = iter.next();
	        System.out.println(alum1);
	        
	    	}
		System.out.println("");
		//asignaturas para alumno
		Set<Asignatura> asig=instancia.getAlumno(45820650).getAsignaturas();
		for (Iterator<Asignatura> iter = asig.iterator(); iter.hasNext();) {
			Asignatura alum1 = iter.next();
	        System.out.println(alum1);
	        
	    	}
		System.out.println("");
		System.out.println("getUnidades por asignatura: ");
		System.out.println(instancia.getUnidades(1));
		System.out.println("");
		
		System.out.println("getProfesor por asignatura: ");
		System.out.println(instancia.getProfesor(1));
		System.out.println("");
		
		System.out.println("getEvaluacionesOrderedByAsignatura: ");
		
		List<Evaluacion> eval=instancia.getEvaluacionesOrderedByAsignatura(45820650); 
		for (Iterator<Evaluacion> iter = eval.iterator(); iter.hasNext();) {
			Evaluacion alum1 = iter.next();
	        System.out.println(alum1);
	        
	    	}
		System.out.println("");
		
		System.out.println("getEvaluacionesAsignatura: ");
		
		List<Evaluacion> eval1=instancia.getEvaluacionesAsignatura(1); 
		for (Iterator<Evaluacion> iter = eval1.iterator(); iter.hasNext();) {
			Evaluacion alum1 = iter.next();
	        System.out.println(alum1);
	        
	    	}
		System.out.println("");
		
		
		
		System.out.println("getEvaluaciones por asignatura y alumno: ");
		Set<Evaluacion> evals=instancia.getEvaluaciones(1, 45820650);
		
		for (Iterator<Evaluacion> iter = evals.iterator(); iter.hasNext();) 
		{
    	Evaluacion emp1 = iter.next();
        System.out.println(emp1);
    	}
		System.out.println("");
		
		
		
		/*System.out.println("getAsignaturasProfesor por ID de profesor: ");
		Set<Asignatura> asigs=instancia.getAsignaturasProfesor(1);
		
		for (Iterator<Asignatura> iter = asigs.iterator(); iter.hasNext();) 
		{
			Asignatura emp1 = iter.next();
			System.out.println(emp1);
    	}
		*/
		
		System.out.println("");
		
		
		
		System.out.println("getAsignaturas por ID alumno: ");
		Set<Asignatura> asigs1 = instancia.getAsignaturas(45820650);
		for (Iterator<Asignatura> iter = asigs1.iterator(); iter.hasNext();) 
		{
			Asignatura emp1 = iter.next();
			System.out.println(emp1);
    	}
		
		System.out.println("");
		
		
		
		System.out.println("getAlumnos por ID asig: ");
		Set<Alumno> alumns = new HashSet<Alumno>();
		alumns = instancia.getAlumnos(1);
		if (alumns!=null)
		
			for (Iterator<Alumno> iter = alumns.iterator() ; iter.hasNext() ;) 
			{
				Alumno emp1 = iter.next();
				System.out.println(emp1);
	    	}
		
		System.out.println("");
		
		
		
		System.out.println("getAlumno por ID alumno: ");
		System.out.println(instancia.getAlumno(45820650));
		
		System.out.println("");
		
		
		
		System.out.println("desmatricular/matricular: ");
		//instancia.desmatricular(45820650, 1);
		instancia.matricular(45820650, 1);
		
		System.out.println("getAsignaturas por ID alumno: ");
		asigs1 = instancia.getAsignaturas(45820650);
		for (Iterator<Asignatura> iter = asigs1.iterator(); iter.hasNext();) 
		{
			Asignatura emp1 = iter.next();
			System.out.println(emp1);
    	}
		
		System.out.println("");
		
		System.out.println("addEvaluacion por ID alumno: ");
		instancia.addEvaluacion("Afinidad de Iker con el sexo anal",10,1,45820650);
		
		System.out.println("");
		System.out.println("getAsignaturasProfesor: ");
		asigs1 = instancia.getAsignaturasProfesor(1);
		for (Iterator<Asignatura> iter = asigs1.iterator(); iter.hasNext();) 
		{
			Asignatura emp1 = iter.next();
			System.out.println(emp1);
    	}
		
		
		
		
		
		instancia.session.close();
	}

}
