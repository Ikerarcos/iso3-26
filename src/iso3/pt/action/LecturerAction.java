package iso3.pt.action;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

import iso3.pt.service.*;
import iso3.pt.model.*;



/**
 * <p> Validate a user login. </p>
 */
public  class LecturerAction  extends ActionSupport implements Preparable{

	private PtDaoService DAO=null;
	private PtService service = null;
	private Profesor profe =null;
	private List<Asignatura> asigs =null;
	private List<Alumno> alumnos =null;
	private String idasig = null;
	private int numunidades = 0;
	private int numalumnos = 0;

    
    
   
    
    
    
    public void prepare() throws Exception
    {	System.out.println("prepare profe");
    	
    	profe = (Profesor) ActionContext.getContext().getSession().get("profe");
    	System.out.println(profe);
    	DAO= new PtDaoService();
    	
    }
    
    public void setAsigs(List<Asignatura> asigs) {
		this.asigs = asigs;
	}

	public String doListing() {
		System.out.println("doListing EN PROFE");
		
		if (getAsigs()==null)
		{	System.out.println(profe);
			setAsigs(new ArrayList<Asignatura>());
			Set<Asignatura> asigset=DAO.getAsignaturasProfesor(profe.getId());
			System.out.println("Generar listado asigs de set a list");
			for (Iterator<Asignatura> iter = asigset.iterator(); iter.hasNext();) 
			{
				Asignatura asig = iter.next();
				getAsigs().add(asig);
				
				System.out.println(asig);
	    	}
		}
		return SUCCESS;
	}

	public void setNumunidades(int numunidades) {
		this.numunidades = numunidades;
	}

	public int getNumunidades(int id) {
		
	
		return DAO.getUnidades(id).size();
	}

	public void setnumalumnos(int numalumnos) {
		this.numalumnos = numalumnos;
	}

	public int getnumalumnos() {
		return numalumnos;
	}
	
	public void setProfe(Profesor profe) {
		this.profe = profe;
	}

	public Profesor getProfe() {
		return profe;
	}

	public List<Asignatura> getAsigs() {
		return asigs;
	}

	public void setAlumnos(List<Alumno> alumnos) {
		this.alumnos = alumnos;
	}

	public List<Alumno> getAlumnos() {
		return alumnos;
	}

	
	
	
	public String listAlumnos()//PROVISIONAL
	{
		System.out.println("listalumnos");
		if (DAO==null) System.out.println("dao null");
		setAlumnos(new ArrayList<Alumno>());
		Set<Alumno> alumset=DAO.getAlumnos(Integer.parseInt(idasig));
		for (Iterator<Alumno> iter = alumset.iterator(); iter.hasNext();) 
		{
			Alumno alumno = iter.next();
			getAlumnos().add(alumno);
			
			System.out.println(alumno);
    	}
		System.out.println("students");
		return "studentslist";
		
	}

	public void setIdasig(String idasig) {
		this.idasig = idasig;
	}

	public String getIdasig() {
		return idasig;
	}

	
	
}
