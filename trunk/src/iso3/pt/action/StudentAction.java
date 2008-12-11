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
	private String idasig = null;

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
    
    
    
    
	
}
