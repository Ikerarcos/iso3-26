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
	private Profesor profe =null;
	private Set<Asignatura> asigs =null;
	private Set<Alumno> alumnos =null;
	private String idasig = null;
	private Asignatura asig = null;

    
    
   
    
    
    
    public void prepare() throws Exception
    {	
    	System.out.println("prepare lecturerAction");    	
    	profe = (Profesor) ActionContext.getContext().getSession().get("profe");
    	DAO= new PtDaoService();
    	
    }
    
    public void setAsigs(Set<Asignatura> asigs) {
		this.asigs = asigs;
	}

	public String doListing() {
		System.out.println("doListing EN PROFE");
		
		if (getAsigs()==null)
		{				
			asigs=DAO.getAsignaturasProfesor(profe.getId());			
		}
		return SUCCESS;
	}



	public int getNumunidades(int id) {
		
	
		return DAO.getUnidades(id).size();
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

	
	
	
	public String listAlumnos()
	{
		System.out.println("lista alumnos EN PROFE");
		asig = DAO.getAsignatura(Integer.parseInt(idasig));
		alumnos=asig.getAlumnos();		
		return "studentslist";
		
	}

	public void setIdasig(String idasig) {
		this.idasig = idasig;
	}

	public String getIdasig() {
		return idasig;
	}

	public void setAsig(Asignatura asig) {
		this.asig = asig;
	}

	public Asignatura getAsig() {
		return asig;
	}

	
	
}
