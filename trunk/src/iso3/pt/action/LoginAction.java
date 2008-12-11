package iso3.pt.action;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;


import iso3.pt.model.*;
import iso3.pt.service.*;



/**
 * <p> Validate a user login. </p>
 */
public  class LoginAction  extends ActionSupport implements Preparable{

	private PtDaoService DAO=null;
	private Rol selectedrol = null;
	private Profesor profe = null;
	private Alumno alum =null;
	private List<Asignatura> asigs =null;
	private int numunidades = 0;
	private int numalumnos = 0;
	

    
    
    public String doLogin() throws Exception {
        System.out.println("Validating Login");
        DAO= new PtDaoService();
        /*Map session = ActionContext.getContext().getSession();
        System.out.println("Crea session");
        String full= (String)session.get("rol");//para obtener de session*/
        
        if (getUsername()==null || getPassword()==null)
        {	addActionError("Compulsory to specify both username and password");
        	return ERROR;
        }
    	else if (selectedrol.getFullName().equals("Profesor"))
    		{	
    			int usern = Integer.parseInt(username);
    			setProfe(DAO.loginProfesor(usern, password));
    			if (getProfe()!=null)
    			{	
    				System.out.println("Crea en session");
    				ActionContext.getContext().getSession().put("profe", profe);
    				//System.out.println(ActionContext.getContext().getSession().get("profe").toString());
					return "listLecturerSubjects";
    			}
				else 
				{
					addActionError("Incorrect username and password");
		    		return ERROR;
				}
    		}
    		else if (selectedrol.getFullName().equals("Alumno"))
    		{	int usern = Integer.parseInt(username);
    			setAlum(DAO.loginAlumno(usern, password));
    		
    			if (getAlum()!=null)
    	        {	System.out.println("Crea en session");
    	        	ActionContext.getContext().getSession().put("alum", getAlum());
    	        	System.out.println(ActionContext.getContext().getSession().get("alum").toString());
				
					return "listStudentSubjects";
    	        }
            	else
            	{
        			addActionError("Incorrect username and password");
	        		return ERROR;
        		}
    		
    		}
    		
    		
    	return INPUT;
    
       
	}
    
    public String doLogOut() throws Exception {
        username=null;
        password=null;
        System.out.println("logout");
        return INPUT;
        
       
	}


    // ---- Username property ----

    /**
     * <p>Field to store User username.</p>
     * <p/>
     */
    private String username = null;


    /**
     * <p>Provide User username.</p>
     *
     * @return Returns the User username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * <p>Store new User username</p>
     *
     * @param value The username to set.
     */
    public void setUsername(String value) {
    	System.out.println("setUsername");
        username = value;
    }

    // ---- Username property ----

    /**
     * <p>Field to store User password.</p>
     * <p/>
     */
    private String password = null;


    /**
     * <p>Provide User password.</p>
     *
     * @return Returns the User password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * <p>Store new User password</p>
     *
     * @param value The password to set.
     */
    public void setPassword(String value) {
    	System.out.println("setPassword");
        password = value;
    }

	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("prepare loginaction");
		
			
	}

	public void setSelectedrol(Rol selectedrol) {
		System.out.println("setSelectedrol");
		this.selectedrol = selectedrol;
	}

	public Rol getSelectedrol() {
		System.out.println("getSelectedrol");
		System.out.println(selectedrol);
		return selectedrol;
	}

	public void setProfe(Profesor profe) {
		this.profe = profe;
	}

	public Profesor getProfe() {
		return profe;
	}

	public void setAlum(Alumno alum) {
		this.alum = alum;
	}

	public Alumno getAlum() {
		return alum;
	}

	

	public void setAsigs(List<Asignatura> asigs) {
		this.asigs = asigs;
	}

	public List<Asignatura> getAsigs() {
		System.out.println("getasigs");
		
		if (asigs==null)
		{	System.out.println(profe);
			asigs = new ArrayList<Asignatura>();
			Set<Asignatura> asigset=DAO.getAsignaturasProfesor(profe.getId());
			System.out.println("Generar listado asigs de set a list");
			for (Iterator<Asignatura> iter = asigset.iterator(); iter.hasNext();) 
			{
				Asignatura asig = iter.next();
				getAsigs().add(asig);
				
				System.out.println(asig);
	    	}
		}
		return asigs;
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
	
	

	

	

	
}
