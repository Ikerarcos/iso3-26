package iso3.pt.dao;

public class UserNotFoundException extends Exception {
	public UserNotFoundException()
	{
		System.out.println("Se ha producido una excepci�n por usuario no encontrado");
		
	}
	
}
