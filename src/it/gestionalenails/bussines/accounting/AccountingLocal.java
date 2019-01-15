package it.gestionalenails.bussines.accounting;

import java.util.Calendar;
import java.util.List;

import javax.ejb.Local;

import it.gestionalenails.data.Appuntamento;

@Local
public interface AccountingLocal {
	public List<Appuntamento>accountingList(String dateStart, String dateEnd, String flagCash, long id_user );
	 
}
