package it.gestionalenails.bussines.userbean;

import java.util.Calendar;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import it.gestionalenails.data.Cliente;
import it.gestionalenails.data.Utente;






/**
 * Session Bean implementation class UserServiceBean
 */
@Stateless
public class UserServiceBean implements UserServiceBeanRemote, UserServiceBeanLocal {

	@PersistenceContext(unitName="connectNails")
	EntityManager manager;



	public UserServiceBean() {

	}

	/////     ////////////////// accesso dashborad/////////////////////////

	public Utente userValidation(String userName, String Password) {

		Query query = manager.createQuery("SELECT t FROM Utente t WHERE t.username = :c AND t.password = :p ");
		query.setParameter("c", userName);
		query.setParameter("p", Password);

		try {
			if(query.getSingleResult() != null) {
				return (Utente) query.getSingleResult();
			}
		}catch(NoResultException e) {
			return null;
		}
		return null;
	}



	///////////////controllo se nome uetente gia in uso /////////////////////////

	public boolean userControll(String nomeUser,long id) {
		Query query = null;
		if(id == 0) {
			if(controlloUser(query, nomeUser)) {  //controllo solo campo username
				return true;
			}
		}
		if(id > 0) {
			query  = manager.createQuery("SELECT usr FROM Utente usr WHERE usr.username = :field AND usr.id = :field2");
			query.setParameter("field", nomeUser);
			query.setParameter("field2", id);
			if(!query.getResultList().isEmpty()) {   
				return true;
			}
			if(query.getResultList().isEmpty()) {
				if(controlloUser(query, nomeUser)) {     // //controllo solo campo username 
					return true;
				}
			}
		}
		return false;
	}

	///query solo controllo solo username/////

	private boolean controlloUser(Query query, String nomeUser) {
		query  = manager.createQuery("SELECT usr FROM Utente usr WHERE usr.username = :field");
		query.setParameter("field", nomeUser);
		if(query.getResultList().isEmpty()) {
			return true;
		}
		return false;
	}


	////////////////////creazione nuovo utente//////////////////////////

	public boolean createUser(Utente user) {
		if(userControll(user.getUsername(),user.getId())) {
			manager.persist(user);
			return true;
		}
		else if(!userControll(user.getUsername(),user.getId())) {
			return false;
		}
		return false;
	}

	///////////////ricerca tutti gli utenti////////////////////

	public List<Utente> allUser() {
		Query query  = manager.createQuery("SELECT usr FROM Utente usr");
		return query.getResultList();
	}


	/////////UPEDATE USERS/////////////////////////////

	public void updateUsers(Utente user) {
		manager.merge(user);
	}

	//////////////////delete Users////////////////////

	public void deleteUsers(long id_app) {
		Utente utn = manager.find(Utente.class, id_app);
		manager.remove(utn);
	}


	//// ricerca utente///////////////////

	public List<Utente> ricercaUtente(String field) {
		Query query = manager.createQuery("SELECT utn FROM Utente utn WHERE utn.cognome = :campo "
				                        + "OR utn.codFiscale = :campo OR utn.username = :campo");
		query.setParameter("campo", field);
		return query.getResultList();
	}
	
	
	

}
