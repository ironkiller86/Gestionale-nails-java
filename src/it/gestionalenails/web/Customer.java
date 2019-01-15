package it.gestionalenails.web;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import it.gestionalenails.bussines.customer.CustomerServiceBeanLocal;
import it.gestionalenails.bussines.userbean.UserServiceBeanLocal;
import it.gestionalenails.data.Cliente;
import it.gestionalenails.data.Utente;


@WebServlet("/customer")
public class Customer extends HttpServlet {
	private static final long serialVersionUID = 1L;


	@EJB
	CustomerServiceBeanLocal customerService;

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	private boolean flag = true;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);     ///CATTURO DAL DOPOST ANCHE QUELLO CHE PASSO IN QUERYSTRING (VEDI SOTTO)
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String action = null;                             // in base al parametro in query string dalla  jsp header scelgo l'azione da fare
		action = request.getParameter("action"); 

		switch(action) {

		case "createcustomer":                     
			createCustomer(request,response);
			break;

		case "customer_search":
			customerSearch(request, response);
			break;

		case "customer_detail":
			customerDetail(request, response);
			break;

		case "updates_customer":
			updatesCustomer(request, response);
			break;	

		case "single_search":
			SingleSearch(request, response);
			break;

		default:
			System.out.println("errore");

		}	

	}




	/////////////////////////// CREAZIONE CLIENTE////////////////////////////////
	private void createCustomer(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {

		Utente utn= (Utente) request.getSession().getAttribute("userLog");
		long user_id = utn.getId();
		String cognome = request.getParameter("cognome");
		String nome = request.getParameter("nome");
		String telefono_one = request.getParameter("phone_one");
		String telefono_two = request.getParameter("phone_two");
		String email = request.getParameter("email");
		Timestamp dataCreazione = new Timestamp(new Date().getTime());



		Cliente cliente = new Cliente(user_id,cognome,nome,telefono_one,telefono_two,email,dataCreazione);
		customerService.createCustomer(cliente);
		request.setAttribute("flag_costumer", "ok");                 // metto in request ok per permettere nella jsp di avere un messaggio di corretta creaqzione
		request.getServletContext().getRequestDispatcher("/jsp/home/header.jsp").include(request, response);
		request.getServletContext().getRequestDispatcher("/jsp/costumer_created.jsp").include(request, response);
		request.getServletContext().getRequestDispatcher("/jsp/home/footer.jsp").include(request, response);


	}

	/////////////////////////////RICERCA UTENTE///////////////////////////////////////////

	private void customerSearch(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {

		Cliente cst = new Cliente();
		Utente utn= (Utente) request.getSession().getAttribute("userLog");
		long user_id = utn.getId();
		String customer = request.getParameter("customer");

		if(flag) {                                         // SE FLAG è TRUE LA RICERCA AVVIENE DALLA TOOLBAR DELL HEADER DELLA APP
			cst.setCostumer(customerService.findCustomer(customer,user_id));
		}
		else if(!flag) {                                   // SE FLAG è FALSE LA RICERCA AVVIENE DA FULLCALENDAR
			List<Cliente> list = (List<Cliente>) request.getSession().getAttribute("costumerList");
			cst.setCostumer(list);
			flag = true;
		}


		if(cst.getCostumer() != null &&!cst.getCostumer().isEmpty()) {
			request.getSession().setAttribute("costumerList", cst.getCostumer());   //metto in session la lista dei clienti per la visualizzazione nel resume
			request.getServletContext().getRequestDispatcher("/jsp/home/header.jsp").include(request, response);
			request.getServletContext().getRequestDispatcher("/jsp/costumer_sommary.jsp").include(request, response);
			request.getServletContext().getRequestDispatcher("/jsp/home/footer.jsp").include(request, response);
		}
		else if (cst.getCostumer() == null || cst.getCostumer().isEmpty()) {	
			request.getServletContext().getRequestDispatcher("/jsp/home/header.jsp").include(request, response);
			request.getServletContext().getRequestDispatcher("/jsp/costumer_created.jsp").include(request, response);
			request.getServletContext().getRequestDispatcher("/jsp/home/footer.jsp").include(request, response);
		}
	}

	//////////////////////////////DETTAGLIO CLIENTE//////////////////////////////////////	

	private void customerDetail(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException  {
		String provIndex = request.getParameter("index");
		int index = (Integer.parseInt(provIndex.trim()) - 1) ;

		List<Cliente> customerList = (List<Cliente>) request.getSession().getAttribute("costumerList"); //recupero la lista clienti in sessione
		Cliente customerDetail = customerList.get(index); //recupero il cliente interessato del dettaglio 
		request.getSession().setAttribute("customer",customerDetail);  //metto il cliente in request

		request.getServletContext().getRequestDispatcher("/jsp/home/header.jsp").include(request, response);
		request.getServletContext().getRequestDispatcher("/jsp/customer_detail.jsp").include(request, response);
		request.getServletContext().getRequestDispatcher("/jsp/home/footer.jsp").include(request, response);



	}

	//////////////////////////////////AGGIORNA DATI CLIENTE//////////////////////////////

	private void updatesCustomer(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException  {
		Cliente clt = new Cliente();
		Utente utn= (Utente) request.getSession().getAttribute("userLog");
		long user_id = utn.getId();
		clt.setId_user(user_id);
		clt.setId_cliente(Long.valueOf(request.getParameter("id")));
		clt.setCognome(request.getParameter("cognome"));
		clt.setNome(request.getParameter("nome"));
		clt.setTelefono_one(request.getParameter("phone_one"));
		clt.setTelefono_two(request.getParameter("phone_two"));
		clt.setEmail(request.getParameter("email"));

		Cliente cltOriginal = (Cliente) request.getSession().getAttribute("customer"); //recupero il cliente in sessione

		if(cltOriginal != null) {           
			if(cltOriginal.equal(clt)) {   //controllo se ho fatto modifiche al cliente confrontando i due oggetti
				request.setAttribute("message", "equal");
				request.getServletContext().getRequestDispatcher("/jsp/home/header.jsp").include(request, response);
				request.getServletContext().getRequestDispatcher("/jsp/info_update_customer.jsp").include(request, response);
				request.getServletContext().getRequestDispatcher("/jsp/home/footer.jsp").include(request, response);
			}
			else if (!cltOriginal.equal(clt)) {
				Timestamp data = new Timestamp(new Date().getTime());
				clt.setDataCreazione(data);
				customerService.updateCostumer(clt);     //aggiorno dati cliente
				request.setAttribute("message", "no_equal");
				request.getServletContext().getRequestDispatcher("/jsp/home/header.jsp").include(request, response);
				request.getServletContext().getRequestDispatcher("/jsp/info_update_customer.jsp").include(request, response);
				request.getServletContext().getRequestDispatcher("/jsp/home/footer.jsp").include(request, response);
			}
		}
	}

	
	//////////////////////////RICERCA CLIENTE DA FULLCALENDAR/////////////////////
	private void SingleSearch(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException  {
		Utente utn= (Utente) request.getSession().getAttribute("userLog");
		long user_id = utn.getId();
		String customer = null;
		JsonObject data = new Gson().fromJson(request.getReader(), JsonObject.class);     // RECUPERO jSON
		customer = data.get("name").getAsString().trim();
		List<Cliente> listclt = customerService.findCustomer(customer,user_id);
		request.getSession().setAttribute("costumerList",listclt);          //METTO IL CLIENTE IN UNA LISTA IN SESSIONE 
	    flag = false;         //FLAG PER GESTIONE RICERCA, SE FALSE LA RICERCA AVVIENE DA FULLCALENDAR

	}

}
