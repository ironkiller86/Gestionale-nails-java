package it.gestionalenails.bussines.userbean;

import it.gestionalenails.data.Utente;

public interface UserService {

	public Utente userValidation(String UserName, String Password);
	public void createUser(Utente user);
	
}
