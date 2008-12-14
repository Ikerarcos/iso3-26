package iso3.pt.action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

import iso3.pt.model.Alumno;
import iso3.pt.model.Asignatura;
import iso3.pt.service.*;



/**
 * <p> Validate a user login. </p>
 */
public  class SubjectMarkingAction  extends ActionSupport implements Preparable{

	private int idasig = 0;
	private String idalumno = null;
	private Asignatura asig = null;
	private Alumno alum = null;
	private PtDaoService DAO=null;

    
    
    public String doSubjectMarkingAction()
    {
    	
    return SUCCESS;
    }



	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("prepare subjectmarking");
		DAO=new PtDaoService();
		System.out.println(getIdalumno());
		System.out.println(getIdasig());
		alum=DAO.getAlumno(Integer.parseInt(getIdalumno()));
		asig=DAO.getAsignatura(getIdasig());
		
		
		System.out.println(getAlum());
	}
	
	public String execute()
	{
		return SUCCESS;
		
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



	public void setIdalumno(String idalumno) {
		this.idalumno = idalumno;
	}



	public String getIdalumno() {
		return idalumno;
	}



	public void setAsig(Asignatura asig) {
		this.asig = asig;
	}



	public Asignatura getAsig() {
		return asig;
	}
    }