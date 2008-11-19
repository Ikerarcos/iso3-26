package iso3.pt.dao;

public class UserNotFoundException extends Exception {
	public UserNotFoundException()
	{
		System.out.println("Se ha producido una excepción por usuario no encontrado");
		
	}
	
}
