package iso3.pt.model;


import java.util.*;

// dipina: introducido implements Serializable, para poder guardar LanguageDesigner en Session
public class Subject implements java.io.Serializable, Comparable {
	
	private String name;
	private int id = 0;
	
	
	public Subject(String name, int id) {
		super();
		this.name = name;
		this.id = id;
	}



	public Subject(){
	}

		

	// added by dipina so that LanguageDesigner objects can be ordered
	public int compareTo(Object o) {
		Subject temp = (Subject)o;
		return this.getName().compareTo(temp.getName());
	}
	
	
	

	
	public String toString(){
		return "(" + this.name +" | "+this.id + ")";
	}







	public void setName(String name) {
		this.name = name;
	}



	public String getName() {
		return name;
	}



	public void setId(int id) {
		this.id = id;
	}



	public int getId() {
		return id;
	}
}
