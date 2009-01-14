package iso3.pt.action;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;



import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

import iso3.pt.service.*;
import iso3.pt.dao.*;
import iso3.pt.model.*;




/**
 * <p> Validate a user login. </p>
 */
public  class StudentAction  extends ActionSupport implements Preparable{

	private PtDaoService DAO=null;
	private Alumno alum=null;
	private List<Asignatura> asiglist =null;
	private List<Subject> asiglistmatric =null;
	private String idasig = null;
	private Asignatura asigmatric = null;
	private Subject subject =null;

	@Override
	public void prepare() throws Exception {
		DAO=new PtDaoService();
		setAlum((Alumno)ActionContext.getContext().getSession().get("alum"));
		Set<Asignatura> alumset = getAlum().getAsignaturas();
		setAsiglist(new ArrayList<Asignatura>());
		for (Iterator<Asignatura> iter = alumset.iterator(); iter.hasNext();) 
		{
			Asignatura asig = iter.next();
			getAsiglist().add(asig);
			
    	}
	}
	
	
	public String doListing()
	
	{
		return SUCCESS;
		
	}


	public void setAsiglist(List<Asignatura> asiglist) {
		this.asiglist = asiglist;
	}


	public List<Asignatura> getAsiglist() {
		return asiglist;
	}


	public void setAlum(Alumno alum) {
		this.alum = alum;
	}


	public Alumno getAlum() {
		return alum;
	}


	public void setIdasig(String idasig) {
		this.idasig = idasig;
	}


	public String getIdasig() {
		return idasig;
	}

    public String desmatricular()
    {
    	DAO.desmatricular(alum.getDni(), Integer.parseInt(idasig));
    	try {
			prepare();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return SUCCESS;
    	
    }
    
    public String doListaMatricular()
    {
    	Set<Asignatura> asigset= DAO.getAsignaturas();
    	asiglistmatric = new ArrayList<Subject>();
    	boolean matric=false;
    	for (Iterator<Asignatura> iter = asigset.iterator(); iter.hasNext();) 
    	
    	{	matric=false;
    		Asignatura asig = iter.next();
    		for(int i=0;i<asiglist.size();i++)
			{
				
				if (asiglist.get(i).getId()== asig.getId())
				{ 	
					matric=true;
					break;
				}
					
	    	}
	    	if (!matric)
	    	{	
	    		asiglistmatric.add(new Subject(asig.getNombre(),asig.getId()));
	    	}
    	}
    	if (this.getSubject() != null ) {
			for (int i=0;i< asiglistmatric.size();i++) {
				if (asiglistmatric.get(i).getName().equals(this.subject.getName())) {
					this.subject.setName(asiglistmatric.get(i).getName());
					this.subject.setId(asiglistmatric.get(i).getId());
					break;
				}
			}
		}
    	return "listaasignaturasmatricular";
    }
    
    public String doMatricularse()
    {
   	
    	DAO.matricular(alum.getDni(), Integer.parseInt(idasig));
    	ActionContext.getContext().getSession().remove("alum");
    	alum = (Alumno)DAO.getAlumno(alum.getDni());
    	ActionContext.getContext().getSession().put("alum",alum);
    	try {
			prepare();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return SUCCESS;
    }


	public void setAsiglistmatric(List<Subject> asiglistmatric) {
		this.asiglistmatric = asiglistmatric;
	}


	public List<Subject> getAsiglistmatric() {
		
		return asiglistmatric;
	}


	public void setAsigmatric(Asignatura asigmatric) {
		this.asigmatric = asigmatric;
	}


	public Asignatura getAsigmatric() {
		return asigmatric;
	}


	public void setSubject(Subject subject) {
		this.subject = subject;
	}


	public Subject getSubject() {
		return subject;
	}


	
    
    
	
}
