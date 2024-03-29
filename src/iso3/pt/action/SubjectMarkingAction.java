package iso3.pt.action;
import java.util.HashSet;
import java.util.Set;



import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

import iso3.pt.model.Alumno;
import iso3.pt.model.Asignatura;
import iso3.pt.model.Evaluacion;
import iso3.pt.model.Profesor;
import iso3.pt.service.*;
import java.util.Iterator;



/**
 * <p> Validate a user login. </p>
 */
public  class SubjectMarkingAction  extends ActionSupport implements Preparable{

	private int idasig = 0;
	private int idalum = 0;
	private Set<Evaluacion> eval;
	private Profesor profesor;
	private Asignatura asig = null;
	private Alumno alum = null;
	private PtDaoService DAO=null;
	private String concept = null;
	private int nota = 0;

    
    
    public String doSubjectMarkingAction()
    {
    return SUCCESS;
    }
    
    public String doMarking()
    {
     DAO.addEvaluacion(concept, nota, idasig, idalum);
     eval=DAO.getEvaluaciones(idasig,idalum);
     for (Iterator<Evaluacion> iter = eval.iterator(); iter.hasNext();) 
		{
    	 Evaluacion emp1 = iter.next();
 	}
     return "evalasignada";
    }


	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		DAO=new PtDaoService();
		setProfesor((Profesor)ActionContext.getContext().getSession().get("profe"));
		alum=DAO.getAlumno(getIdalum());
		asig=DAO.getAsignatura(getIdasig());
		seteval(new HashSet<Evaluacion>());
		eval=DAO.getEvaluaciones(idasig,idalum);
	}


	public void setIdasig(int idasig) {
		this.idasig = idasig;
	}



	public int getIdasig() {
		return idasig;
	}



	public void setAlum(Alumno alum) {
		this.alum = alum;
	}



	public Alumno getAlum() {
		return alum;
	}



	public void setIdalum(int idalumno) {
		this.idalum = idalumno;
	}



	public int getIdalum() {
		return idalum;
	}



	public void setAsig(Asignatura asig) {
		this.asig = asig;
	}



	public Asignatura getAsig() {
		return asig;
	}

	public void setConcept(String concept) {
		this.concept = concept;
	}

	public String getConcept() {
		return concept;
	}

	public void setNota(int nota) {
		this.nota = nota;
	}

	public int getNota() {
		return nota;
	}

	/**
	 * @param profesor the profesor to set
	 */
	private void setProfesor(Profesor profesor) {
		this.profesor = profesor;
	}

	/**
	 * @return the profesor
	 */
	private Profesor getProfesor() {
		return profesor;
	}

	/**
	 * @param eval the eval to set
	 */
	public void seteval(Set<Evaluacion> eval) {
		this.eval = eval;
	}

	/**
	 * @return the eval
	 */
	public Set<Evaluacion> geteval() {
		return eval;
	}
    }