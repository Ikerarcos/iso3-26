package iso3.pt.action;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import iso3.pt.action.Rol;
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
	private List<Rol> roles = null;
	private Rol selectedrol = null;
	private Profesor profe = null;
	private Alumno alum =null;
	private List<Asignatura> asigs =null;
	private int numunidades = 0;
	private int numalumnos = 0;
	private String username = null;
	private String password = null;
	
    
    public String doLogin() throws Exception {
        System.out.println("Validating Login");
        DAO= new PtDaoService();        
        if (getUsername()==null || getPassword()==null)
        {	
        	addActionError("Compulsory to specify both username and password");
        	return ERROR;
        }
    	else if (selectedrol.getFullName().equals("Profesor"))
    		{		
    			int usern = Integer.parseInt(username);
    			setProfe(DAO.loginProfesor(usern, password));
    			if (getProfe()!=null)
    			{	
    				ActionContext.getContext().getSession().put("profe", getProfe());
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
    	        {	    			
    	        	ActionContext.getContext().getSession().put("alum", getAlum());    	        				
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
    	System.out.println("logout");
    	username=null;
        password=null;
        ActionContext.getContext().getSession().clear();        
        return INPUT;
        
       
	}

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
    	//System.out.println("setUsername");
        username = value;
    }

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
    	//System.out.println("setPassword");
        password = value;
    }

	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("prepare loginaction");
		roles = new ArrayList<Rol>();
		roles.add(new Rol("Profesor"));
		roles.add(new Rol("Alumno"));
		if (this.getSelectedrol() != null ) {
			for (Rol rol: this.getRoles()) {
				if (rol.equals(this.getSelectedrol())) {
					this.setSelectedrol(rol);
					break;
				}
			}
		}	
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
		{	
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
	
	public void setRoles(List<Rol> roles) {
		this.roles = roles;
	}

	public List<Rol> getRoles() {
		System.out.println("getRoles");
		for (int i=0;i<roles.size();i++)
			System.out.println(roles.get(i));
		return roles;
	}	
}
