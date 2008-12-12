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
	private List<Asignatura> asiglistmatric =null;
	private String idasig = null;
	private Asignatura asigmatric = null;
	private Subject subject =null;

	@Override
	public void prepare() throws Exception {
		System.out.println("prepare en student");
		DAO=new PtDaoService();
		setAlum((Alumno)ActionContext.getContext().getSession().get("alum"));
		Set<Asignatura> alumset = getAlum().getAsignaturas();
		setAsiglist(new ArrayList<Asignatura>());
		for (Iterator<Asignatura> iter = alumset.iterator(); iter.hasNext();) 
		{
			Asignatura asig = iter.next();
			getAsiglist().add(asig);
			
			System.out.println(asig);
    	}
	}
	
	
	public String doListing()
	
	{
		System.out.println("dolisting en student");
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
    	System.out.println("desmatricular");
    	DAO.desmatricular(alum.getDni(), Integer.parseInt(idasig));
    	try {
			prepare();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	System.out.println(idasig);
    	return SUCCESS;
    	
    }
    
    public String listaMatricular()
    {
    	System.out.println("listaMatricular");
    	//SOLO METE EN LA LISTA LAS ASIGS EN LAS Q NO STA MATRIC
    	Set<Asignatura> asigset= DAO.getAsignaturas();
    	asiglistmatric = new ArrayList<Asignatura>();
    	System.out.println("obtiene lista");
    	boolean matric=false;
    	for (Iterator<Asignatura> iter = asigset.iterator(); iter.hasNext();) 
    	
    	{	matric=false;
    		Asignatura asig = iter.next();
    		for(int i=0;i<asiglist.size();i++)
			{
				
				if (asiglist.get(i).getId()== asig.getId())
				{ 	System.out.println("coincide:");
					System.out.println(asiglist.get(i));
					matric=true;
					break;
				}
					
	    	}
	    	if (!matric)
	    	{	
	    		System.out.println("no matriculado");
	    		asiglistmatric.add(asig);
	    		System.out.println(asig);
	    	}
    	}
    	if (this.getSubject() != null ) {
			for (int i=0;i< asiglistmatric.size();i++) {
				if (asiglistmatric.get(i).getNombre().equals(this.subject.getAsig().getNombre())) {
					this.subject.setAsig(asiglistmatric.get(i));
					break;
				}
			}
		}
    	return "listaasignaturasmatricular";
    }
    
    public String matricular()
    {
    	System.out.println("matricular");
    	System.out.println(getSubject().getAsig());
    	DAO.matricular(alum.getDni(), getSubject().getAsig().getId());
    	System.out.println("matriculado");
    	try {
			prepare();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return "SUCCESS";
    }


	public void setAsiglistmatric(List<Asignatura> asiglistmatric) {
		this.asiglistmatric = asiglistmatric;
	}


	public List<Asignatura> getAsiglistmatric() {
		System.out.println("getAsiglistmatric");
		for (int i=0;i<asiglistmatric.size();i++)
			System.out.println(asiglistmatric.get(i));
			System.out.println(getSubject().getAsig());
		
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
