package it.gestionalenails.bussines.userbean;

import java.util.List;

import it.gestionalenails.data.Utente;

public interface UserService {

	public Utente userValidation(String UserName, String Password);
	public boolean createUser(Utente user);
	public List<Utente> allUser();
	public boolean userControll(String nomeUser,long id);
	public void updateUsers(Utente user);
	public void deleteUsers(long id_app);
	public List<Utente> ricercaUtente(String utente);
}
