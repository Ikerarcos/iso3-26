package iso3.pt.action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

import iso3.pt.model.Alumno;
import iso3.pt.model.Asignatura;
import iso3.pt.model.Evaluacion;
import iso3.pt.service.*;



/**
 * <p> Validate a user login. </p>
 */
public  class SubjectMarkingAction  extends ActionSupport implements Preparable{

	private int idasig = 0;
	private int idalum = 0;
	private Asignatura asig = null;
	private Alumno alum = null;
	private PtDaoService DAO=null;
	private Evaluacion eval=null;
	private String concept = null;
	private int nota = 0;

    
    
    public String doSubjectMarkingAction()
    {

    /*ActionContext.getContext().getSession().remove("alumtomark");
    ActionContext.getContext().getSession().put("alumtomark",alum);
    ActionContext.getContext().getSession().remove("asigtomark");
    ActionContext.getContext().getSession().put("asigtomark",asig);*/
    return SUCCESS;
    }
    
    public String doMarking()
    {
     System.out.println("doMarking");
     eval= new Evaluacion(concept,nota,asig);
     System.out.println(eval);
     alum.addEvaluacion(eval);	
     return SUCCESS;
    }


	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("prepare subjectmarking");
		DAO=new PtDaoService();
		System.out.println(getIdalum());
		System.out.println(getIdasig());
		alum=DAO.getAlumno(getIdalum());
		asig=DAO.getAsignatura(getIdasig());
		
		
		System.out.println(getAlum());
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
		System.out.println("setIdalumno");
		this.idalum = idalumno;
	}



	public int getIdalum() {
		return idalum;
	}



	public void setAsig(Asignatura asig) {
		System.out.println("setAsig");
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
    }