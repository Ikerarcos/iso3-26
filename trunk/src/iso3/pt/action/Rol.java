package iso3.pt.action;

import java.util.*;

// dipina: introducido implements Serializable, para poder guardar LanguageDesigner en Session
public class Rol implements java.io.Serializable, Comparable {
	private String fullName;
	
	
	public Rol(){
	}

	public Rol(String fullName) {
		super();
		this.fullName = fullName;
		
	}

	// added by dipina so that LanguageDesigner objects can be ordered
	public int compareTo(Object o) {
		Rol temp = (Rol)o;
		return this.fullName.compareTo(temp.fullName);
	}
	
	public String getFullName() {
		//System.out.println("getfullname en Rol");
		//System.out.println(fullName);
		return fullName;
	}

	public void setFullName(String fullName) {
		//System.out.println("setFullName");
		//System.out.println(fullName);
		this.fullName = fullName;
	}

	

	
	public String toString(){
		return "(" + this.fullName + ")";
	}
}
