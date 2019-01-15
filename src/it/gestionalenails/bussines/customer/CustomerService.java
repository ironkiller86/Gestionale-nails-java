package it.gestionalenails.bussines.customer;

import java.util.List;

import it.gestionalenails.data.Appuntamento;
import it.gestionalenails.data.Cliente;

public interface CustomerService   {

	public void createCustomer(Cliente cliente);
	public List<Cliente> findCustomer(String costumer,long id);
	public void updateCostumer(Cliente cliente);
	public void createAppointment(Appuntamento app);
	public void updateAppointment(Appuntamento app);
	public List<Appuntamento> appointmentSearch(boolean isFinish,long id_user);
	public List<Appuntamento> appointmentSearch(long id_user);
	public Appuntamento singleAppointmentSearch(long id_user);
	public List<Appuntamento> search(long user_Id,String nominative ,String data, boolean isFinish);
	public Cliente findCustomer(long id);
	public void deleteAppointment(long id_app);
	
}
