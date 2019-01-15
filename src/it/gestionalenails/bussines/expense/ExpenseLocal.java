package it.gestionalenails.bussines.expense;

import java.util.Calendar;
import java.util.List;

import javax.ejb.Local;

import it.gestionalenails.data.Product;

@Local
public interface ExpenseLocal {
	public void addExpense(Product prd);
	public List<Product> searchExpense(Calendar dataStart,Calendar dataEnd,String productName,long id_user);
	public void expenseUpdate(Product prd);
	public void expenseDelete(Long id);
}
