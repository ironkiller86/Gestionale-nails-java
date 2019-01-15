package it.gestionalenails.bussines.accounting;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import it.gestionalenails.data.Appuntamento;

/**
 * Session Bean implementation class Accounting
 */
@Stateless
@Local
public class Accounting implements AccountingLocal {

	@PersistenceContext(name="connectNails")
	EntityManager manager;


	public Accounting() {

	}


	/////////////////////////LOGICA DI RECUPERO IMPORTI PER CONOSCER L'INCASSATO/////////////////////////////

	public List<Appuntamento>accountingList(String dateStart, String dateEnd, String flagCash,long id_user){
		Query query = null;

		if(!dateStart.equals("") && dateEnd.equals("") && flagCash == null) {
			query = manager.createNativeQuery("SELECT *FROM appuntamento WHERE data_appuntamento LIKE ? "
					                        + "AND appuntamento_terminato = ? AND id_user= ? ", Appuntamento.class);
			query.setParameter(1, "%" + dateStart.trim() + "%");
			query.setParameter(2, true);
			query.setParameter(3, id_user);
			return (List<Appuntamento>) query.getResultList();
		}
		else if(!dateStart.equals("") && !dateEnd.equals("") && flagCash == null) {
			query = manager.createNativeQuery("SELECT *FROM appuntamento WHERE appuntamento_terminato = ? AND"
					                        + " data_appuntamento BETWEEN ? AND ? "
					                        + " AND id_user = ? ORDER BY data_appuntamento ASC", Appuntamento.class);
			query.setParameter(1, true);
			query.setParameter(2, dateStart.trim() + " 00:00:00" );
			query.setParameter(3, dateEnd.trim() + " 23:59:59");
			query.setParameter(4, id_user);
			
			return (List<Appuntamento>) query.getResultList();
		}
		else if(dateStart.equals("") && dateEnd.equals("") && flagCash != null) {
			query = manager.createNativeQuery("SELECT *FROM appuntamento WHERE appuntamento_terminato = ? AND "
					                        + "data_appuntamento LIKE ? AND id_user = ?", Appuntamento.class);
			query.setParameter(1, true);
			query.setParameter(2, "%" + LocalDate.now().toString() + "%");
			query.setParameter(3, id_user);
			return (List<Appuntamento>) query.getResultList();
		}
		else if(dateStart.equals("") && !dateEnd.equals("") && flagCash == null) {
			
			return null;
		}
		
		return null;
	}

}
