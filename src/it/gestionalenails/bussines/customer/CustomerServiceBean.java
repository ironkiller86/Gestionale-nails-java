package it.gestionalenails.bussines.customer;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import it.gestionalenails.data.Appuntamento;
import it.gestionalenails.data.Cliente;

/**
 * Session Bean implementation class CustomerServiceBean
 */
@Stateless
public class CustomerServiceBean implements CustomerServiceBeanRemote, CustomerServiceBeanLocal {

	@PersistenceContext(unitName="connectNails")
	EntityManager managerCustomer;

	public CustomerServiceBean() {

	}


	//////////////////CREAZIONE CLIENTE/////////////////

	public void createCustomer(Cliente cliente) {
		managerCustomer.persist(cliente);

	}

	//////////////////RICERCA CLIENTE/////////////////

	public List<Cliente> findCustomer(String field,long id) {
		Query query = null;
		if(field.equals("")) {
			query = managerCustomer.createQuery("SELECT c FROM Cliente c");
			return query.getResultList();
		}
		else if(!field.equals("")) {
			String []token = field.split("\\s");

			switch(token.length) {

			case 1:    //RICERCA PER NOME O COGNOME 
				query = managerCustomer.createQuery("SELECT c FROM Cliente c WHERE c.cognome = :t AND c.id_user = :id");
				query.setParameter("t",token[0]);
				query.setParameter("id", id);	
				if(query.getResultList().isEmpty()) {
					query = managerCustomer.createQuery("SELECT c FROM Cliente c WHERE c.nome = :t AND c.id_user = :id");
					query.setParameter("t",token[0]);
					query.setParameter("id", id);	
					break;
				}
				break;

			case 2: // RICERCA PER COGNOME CON COGNOME COMPOSTO DA DUE TOKEN
				query = managerCustomer.createQuery("SELECT c FROM Cliente c WHERE c.cognome = :t AND c.nome = :n AND c.id_user = :id");
				query.setParameter("t",token[0]);
				query.setParameter("n",token[1]);
				query.setParameter("id", id);
				if(query.getResultList().isEmpty()) {
					String cognome = token[0] + " " + token[1];
					query = managerCustomer.createQuery("SELECT c FROM Cliente c WHERE c.cognome = :t AND c.id_user = :id");
					query.setParameter("t",cognome);
					query.setParameter("id", id);
					break;
				}
				break;

			case 3: //RICERCA PER NOME E COGNOME CON COGNOME COMPOSTO DA DUE TOKEN
				String cognome = token[0] + " " + token[1];
				String nome = token[2];
				query = managerCustomer.createQuery("SELECT c FROM Cliente c WHERE c.cognome = :t AND c.nome =:n AND c.id_user = :id");
				query.setParameter("t",cognome);
				query.setParameter("n",nome);
				query.setParameter("id", id);	
				break;

			default:
				return null;
			}

			if(field.length() == 10) {   // RICERCA PER NUMERO DI TELEFONO O CELLULARE
				query = managerCustomer.createQuery("SELECT c FROM Cliente c WHERE (c.telefono_one= :t OR c.telefono_two =:t) AND c.id_user = :id");
				query.setParameter("t",field);
				query.setParameter("id", id);	
			}

		}
		return query.getResultList();
	}
	///////////////////////////TROVA CLIENTE PER ID CLEINTE///////////////////////////////////////////

	public Cliente findCustomer(long id) {
		return managerCustomer.find(Cliente.class, id); 
	}




	///////////////////AGGIORNAMENTO DATI CLIENTE///////////////////////

	public void updateCostumer(Cliente cliente) {
		managerCustomer.merge(cliente);

	}


	/////////////////  CREO APPUNTAMENTO///////////////////////////// 

	public void createAppointment(Appuntamento app) {
		managerCustomer.persist(app);
	}

	///////////////////AGGIORNO APPUNTAMENTO///////////////////////    

	public void updateAppointment(Appuntamento app) {
		managerCustomer.merge(app);
	}


	//////////////////////////////RICERCA APPUNTAMENTO per id_cliente_App////////////////////////////////////   

	public Appuntamento singleAppointmentSearch(long id_app){
		return  managerCustomer.find(Appuntamento.class, id_app); 
	}

	///////////////////////////TROVA TUTTI GLI APPUNTAMENTI IN BASE ALLO STATO DI LAVORAZIONE ////////////////////////////////////////////////////


	public List<Appuntamento> appointmentSearch(boolean isFinish,long id_user) {
		Query query = null;
		if(isFinish == false) {
			query = managerCustomer.createNativeQuery("SELECT *FROM appuntamento WHERE appuntamento_terminato = ? AND id_user = ?",Appuntamento.class);
			query.setParameter(1, false);
			query.setParameter(2, id_user);
			return (List<Appuntamento>) query.getResultList();
		}
		else if (isFinish == true) {
			query = managerCustomer.createNativeQuery("SELECT *FROM appuntamento WHERE appuntamento_terminato = ?  AND id_user = ?", Appuntamento.class);
			query.setParameter(1, true);
			query.setParameter(2, id_user);
			return (List<Appuntamento>) query.getResultList();
		}
		return null;
	}


	public List<Appuntamento> appointmentSearch(long id_user) {
		Query query = null;
		query = managerCustomer.createQuery("SELECT app FROM Appuntamento app WHERE app.id_user = :a ");
		query.setParameter("a", id_user);
		return  (List<Appuntamento>) query.getResultList() ;
	}



	//////////////////////////RICERCA PER NOMINATIVO APPUNTAMENTO//////////////////////

	public List<Appuntamento> search(long id_user,String nominative ,String data, boolean isFinish) {
        
		List <Appuntamento> totalApp = null;
		totalApp = appointmentSearch(isFinish,id_user); //recupero dal db tutti gli appuntamenti o terminati o in corso
		Appuntamento app  = new Appuntamento();
		
		if(!nominative.equals("") && data.equals(""))	{
			List<Cliente> clienti = findCustomer(nominative,id_user);   //recupero dal db tutti i clienti che si chiamano con un certo nome
			app.getListAppointment().clear();
			for(int index_app = 0; index_app < totalApp.size(); index_app++) {
				for(int index = 0; index < clienti.size(); index++) {              
					if(totalApp.get(index_app).getIdClienteApp().equals(clienti.get(index).getId_cliente())) { 
						app.getListAppointment().add(totalApp.get(index_app));  //controllo se ogni appuntamento contiene il cliente in questione se positovo aggiungo l'appuntamento alla listApp
					}
				}	
			}
			if(!app.getListAppointment().isEmpty()) {
				return app.getListAppointment();
			}
			else {
				return null;
			}
		}

		/////RICERCA APPUNTAMENTI SOLO IN BASE ALLO STATO/////////////////////////////////////
		if(nominative.equals("") && data.equals("")) {
			return totalApp;
		}


		/////////////////////RICERCA PER DATA APPUNTAMENTO///////////////////////////////////////////////		

		if(nominative.equals("") && !data.equals(""))	{
			SimpleDateFormat format = new  SimpleDateFormat("yyyy/MM/dd");    
			for(int index_app = 0; index_app < totalApp.size(); index_app++) {
				String data_App = format.format(totalApp.get(index_app).getDataAppuntamento().getTime());  
				if(data_App.equals(data.trim())) {                              //controllo se la data di qualche appuntamento è uguale alla data che abbiamo inserito
					app.getListAppointment().add(totalApp.get(index_app));
				}
			}
			totalApp.clear();
		}
		if(!app.getListAppointment().isEmpty()) {
			return app.getListAppointment();
		} 
		else {
			return null;
		}
	}

	/////////////////////////////////DELETE APPUNTAMENTO//////////////////

	public void deleteAppointment(long id_app) {
		Appuntamento app = managerCustomer.find(Appuntamento.class, id_app); 
		managerCustomer.remove(app);
	}



}
