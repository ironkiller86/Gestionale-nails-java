package it.gestionalenails.bussines.expense;

import java.util.Calendar;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;

import it.gestionalenails.data.Product;

/**
 * Session Bean implementation class Expense
 */
@Stateless
@Local
public class Expense implements ExpenseLocal {

	@PersistenceContext(name="connectNails")
	EntityManager manager;

	public Expense() {

	}



	////////////INSERIMENTO SPESA NEL DB//////////////////////

	public void addExpense(Product prd) {
		manager.persist(prd);
	}

	////////////   UPDATE SPESA NEL DB//////////////////////

	public void expenseUpdate(Product prd) {
		manager.merge(prd);
	}

	//////////DELETE EXPENSE//////////

	public void expenseDelete(Long id) {
		Product prd = manager.find(Product.class, id);
		manager.remove(prd);
	}


	/////////////////////////RICERCA SPESA I BASE Ai  VARI CAMPI/////////////////////////////
	public List<Product> searchExpense(Calendar dataStart,Calendar dataEnd, String productName,long id_user) {
		Query query = null;
		if(dataStart == null && dataEnd == null && !productName.equals("")) {                        //ricerca solo per nome prodotto
			query = manager.createQuery("SELECT p FROM Product p WHERE p.productName LIKE :prd AND p.id_user = :usr");
			query.setParameter("prd", "%" + productName + "%" );
			query.setParameter("usr",id_user);
			return (List<Product>) query.getResultList();
		}
		if(dataStart != null && productName.equals("") && dataEnd == null) {                        // ricerca solo per data inserimento 
			query = manager.createQuery("SELECT p FROM Product p WHERE p.date =:date_St AND p.id_user =:usr");
			query.setParameter("date_St", dataStart, TemporalType.DATE);
			query.setParameter("usr",id_user);
			return (List<Product>) query.getResultList();
		}

		if(dataStart != null && dataEnd != null && productName.equals("")) {                    // ricerca solo compresa  tra date
			query = manager.createQuery("SELECT p FROM Product p WHERE p.date BETWEEN :dataSt "
					       + "AND :dataEn AND p.id_user =:usr");
			query.setParameter("dataSt", dataStart, TemporalType.DATE);
			query.setParameter("dataEn", dataEnd, TemporalType.DATE);
			query.setParameter("usr", id_user);
			return (List<Product>) query.getResultList();
		}

		if(dataStart != null && dataEnd != null && !productName.equals("")) {                   // ricerca compresa per nome e compresa tra data
			query = manager.createQuery("SELECT p FROM Product p WHERE p.productName LIKE :prd AND "
				                      + "p.date BETWEEN :dataSt AND :dataEn AND p.id_user =:usr");  
			query.setParameter("prd", "%" + productName + "%" );
			query.setParameter("dataSt", dataStart, TemporalType.DATE);
			query.setParameter("dataEn", dataEnd, TemporalType.DATE);
			query.setParameter("usr",id_user);
			return (List<Product>) query.getResultList();
		}

		if(dataStart == null && dataEnd == null && productName.equals("")) {              
			return null;
		}

		return null;
	}

}
