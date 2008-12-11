package iso3.pt.action;
import com.opensymphony.xwork2.ActionSupport;
import iso3.pt.service.*;



/**
 * <p> Validate a user login. </p>
 */
public  class SubjectMarkingAction  extends ActionSupport {

	private PtDaoService DAO=null;

    
    
    public String doLogin() throws Exception {
        System.out.println("Validating Login");
        DAO= new PtDaoService();
        
        if (getUsername()==null || getPassword()==null)
        {	addActionError("Compulsory to specify both username and password");
        	return ERROR;
        }
        	else
        	{	
        		
        		int usern = Integer.parseInt(username);
        		if (DAO.loginAlumno(usern, password)!=null)
		        {	return "listStudentSubjects";
		        }
	        	else
	        	{
	        		if (DAO.loginProfesor(usern, password)!=null) 
	        			return "listLecturerSubjects";
	        		else 
	        			addActionError("Incorrect username and password");
		        		return ERROR;
	        	}
        		
        		
        	}
        
       
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
        password = value;
    }

	
}
