package it.gestionalenails.bussines.userbean;

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
	
	public void createUser(Utente user) {
		manager.persist(user);

	}

}
