package iso3.pt.action;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

import iso3.pt.model.Alumno;
import iso3.pt.model.Asignatura;
import iso3.pt.model.Evaluacion;
import iso3.pt.model.Profesor;
import iso3.pt.service.*;



/**
 * <p> Validate a user login. </p>
 */
public  class SubjectAction  extends ActionSupport implements Preparable{

	private PtDaoService DAO=null;
	private Asignatura asig = null;
	private Alumno alum = null;
	private int idalumno=0;
	private int idasig = 0;
	private Set<Evaluacion >setnotas =null;
	
	@Override
	public void prepare() throws Exception {
		System.out.println("prepare subject");
		DAO =new PtDaoService();
		
		
	}


	
	public int listUnits(String id)
	{	System.out.println("listUnits");
		return DAO.getUnidades(Integer.parseInt(id)).size();
		
		
		
	}
	
	public String notas()
	{	setAlum(DAO.getAlumno(idalumno));
		setAsig(DAO.getAsignatura(idasig));
		setSetnotas(DAO.getEvaluaciones(idasig, idalumno));
		return SUCCESS;
		
	}

    
    
    
	
	


	



	public void setIdalumno(int idalumno) {
		this.idalumno = idalumno;
	}

	public int getIdalumno() {
		return idalumno;
	}

	public void setIdasig(int idasig) {
		this.idasig = idasig;
	}

	public int getIdasig() {
		return idasig;
	}

	public void setAsig(Asignatura asig) {
		this.asig = asig;
	}

	public Asignatura getAsig() {
		return asig;
	}

	public void setAlum(Alumno alum) {
		this.alum = alum;
	}

	public Alumno getAlum() {
		return alum;
	}



	


	public void setSetnotas(Set<Evaluacion > setnotas) {
		this.setnotas = setnotas;
	}



	public Set<Evaluacion > getSetnotas() {
		System.out.println("getSetnotas");
		return setnotas;
	}
}