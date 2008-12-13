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
	private Set<Asignatura> asigs =null;
	private Set<Alumno> alumnos =null;
	private String idasig = null;
	private int numunidades = 0;
	private int numalumnos = 0;

    
    
   
    
    
    
    public void prepare() throws Exception
    {	System.out.println("prepare profe");
    	
    	profe = (Profesor) ActionContext.getContext().getSession().get("profe");
    	System.out.println(profe);
    	DAO= new PtDaoService();
    	
    }
    
    public void setAsigs(Set<Asignatura> asigs) {
		this.asigs = asigs;
	}

	public String doListing() {
		System.out.println("doListing EN PROFE");
		
		if (getAsigs()==null)
		{	System.out.println(profe);
			
			asigs=DAO.getAsignaturasProfesor(profe.getId());
			
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

	public Set<Asignatura> getAsigs() {
		return asigs;
	}

	public void setAlumnos(Set<Alumno> alumnos) {
		this.alumnos = alumnos;
	}

	public Set<Alumno> getAlumnos() {
		return alumnos;
	}

	
	
	
	public String listAlumnos()//PROVISIONAL
	{
		System.out.println("listalumnos");
		System.out.println(idasig);
		alumnos=DAO.getAlumnos(Integer.parseInt(idasig));
		System.out.println("listado");
		for (Iterator<Alumno> iterator = alumnos.iterator(); iterator.hasNext(); )
		{	Alumno alum=iterator.next();
			System.out.println(alum);
		}
		
		
		return "studentslist";
		
	}

	public void setIdasig(String idasig) {
		this.idasig = idasig;
	}

	public String getIdasig() {
		return idasig;
	}

	
	
}
