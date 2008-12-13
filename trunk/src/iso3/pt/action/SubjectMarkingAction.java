package iso3.pt.action;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

import iso3.pt.service.*;



/**
 * <p> Validate a user login. </p>
 */
public  class SubjectMarkingAction  extends ActionSupport implements Preparable{

	private int idasig = 0;

    
    
    public String doSubjectMarkingAction()
    {
    	
    return SUCCESS;
    }



	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("prepare subjectmarking");
		System.out.println(idasig);
	}



	public void setIdasig(int idasig) {
		this.idasig = idasig;
	}



	public int getIdasig() {
		return idasig;
	}
    }