package iso3.pt.action;
import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;



import iso3.pt.model.Rol;
import iso3.pt.service.*;



/**
 * <p> Validate a user login. </p>
 */
public  class ShowLoginAction  extends ActionSupport implements Preparable{

	private PtDaoService DAO=null;
	private List<Rol> roles = null;
	private Rol selectedrol = null;

    
    
    
	
    
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

	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("prepare show login");
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

	public void setRoles(List<Rol> roles) {
		this.roles = roles;
	}

	public List<Rol> getRoles() {
		System.out.println("getRoles");
		for (int i=0;i<roles.size();i++)
			System.out.println(roles.get(i));
		return roles;
	}

	public void setSelectedrol(Rol selectedrol) {
		//System.out.println("setSelectedrol");
		//System.out.println(selectedrol);
		this.selectedrol = selectedrol;
	}

	public Rol getSelectedrol() {
		//System.out.println("getSelectedrol");
		//System.out.println(selectedrol);
		return selectedrol;
	}

	

	
}
