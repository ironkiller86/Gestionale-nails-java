package it.gestionalenails.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;

import it.gestionalenails.bussines.customer.CustomerServiceBeanLocal;
import it.gestionalenails.data.Appuntamento;
import it.gestionalenails.data.Cliente;
import it.gestionalenails.data.ModelCalendar;
import it.gestionalenails.data.Utente;

/**
 * Servlet implementation class dispatcher
 */
@WebServlet("/dispatcher")
public class dispatcher extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	CustomerServiceBeanLocal customerService;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public dispatcher() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String page = request.getParameter("page");
		
		if(page.equals("loginPage")) {
			request.getServletContext().getRequestDispatcher("/jsp/LoginPage.jsp").forward(request, response);
		}


		request.getServletContext().getRequestDispatcher("/jsp/home/header.jsp").include(request, response);

		switch(page) {

		case "create_costumer":                                        //Pagina creazione Cliente
			createCostumer(request, response);
			break;

		case "home":
			goToHome(request, response);
			break; 

		case "appointment_creation":
			appointmentCreation(request, response);
			break;

		case "appointment_search":
			appointmentSearch(request, response);
			break;	

		case "appointment_modify":
			appointmentModify(request, response);
			break;		

		case "accounting":
			accounting(request, response);
			break;

		case "add_expense":
			addExpense(request, response);
			break;

		case "expense_search":
			expenseSearch(request, response);
			break;

		case "profit":
			earningResearch(request,response);    
			break;

		case "sigleApp":
			singleApp(request,response);    
			break;	

		}
		request.getServletContext().getRequestDispatcher("/jsp/home/footer.jsp").include(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {




	}


	////////////////////////////CREAZIONE CLIENTE/////////////////////////////////////////////////	

	private void createCostumer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getServletContext().getRequestDispatcher("/jsp/create_user.jsp").include(request, response);	
	}


	/////////////////////////REINDIZZIRAMENTO HOME//////////////////////////////////////////////////////	

	private void goToHome(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getServletContext().getRequestDispatcher("/jsp/home/body.jsp").include(request, response);	
	}

	///////////////////////////////////////CREAZIONE APPUNTAMENTO/////////////////////////////

	private void appointmentCreation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int index =  Integer.parseInt(request.getParameter("customerIndex".trim())) - 1;                  //recupero l'indice in request e lo trasformo in un int
		List<Cliente> listaCustomer  = (List<Cliente>) request.getSession().getAttribute("costumerList"); //recupero la lista dei clienti in sessione
		Cliente cliente = listaCustomer.get(index);                                                       //recupero il cliente 
		request.getSession().setAttribute("customerApp", cliente);                                         //metto il cliente in sessione per utilizzarlo poi nella appointment_creation.jsp
		request.getSession().setAttribute("indexCostumer", index);                                         //metto in sessione anche l 'indice del cleinte per capire per quale cleinte sto creando un appuntamento dalla servlet businessAppointment
		request.getServletContext().getRequestDispatcher("/jsp/appointment_creation.jsp").include(request, response);

	}

	////////////////////////////////////RICERCA APPUNTAMENTO///////////////////////////////////////

	private void appointmentSearch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getServletContext().getRequestDispatcher("/jsp/appointment_search.jsp").include(request, response);
	}


	/////////////////////////////////MODIFICA APPUNTAMENTO//////////////////////////////////////////////

	private void appointmentModify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int index =  Integer.parseInt(request.getParameter("rowApp".trim())) - 1;                  //recupero l'indice in request e lo trasformo in un int
		List<Appuntamento> listApp =(List<Appuntamento>) request.getSession().getAttribute("List_appointment_find");  //recupero dalla sessione la lista degli appuntamenti trovati
		Appuntamento app = listApp.get(index);             // mi recupero con l'indice l'appuntamento che mi interessa
		request.getSession().setAttribute("appointment_object",app);    // metto l'appuntamento da visuilizzare in request
		request.getServletContext().getRequestDispatcher("/jsp/modify_appointment.jsp").include(request, response);

	}

	/////////////////////////////////////MI PORTA ALLA PAGINA CONTABILITA/////////////////////////////

	private void accounting(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getServletContext().getRequestDispatcher("/jsp/accounting_view.jsp").include(request, response);
	}

	/////////////////////////////////////VENGO INDIRIZZATO ALLA PAGINA PER AGGIUNTA SPESA/////////////////////

	private void addExpense(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getServletContext().getRequestDispatcher("/jsp/expense_creation.jsp").include(request, response);
	}

	////////////////////////////////PAGINA RICERCA  ACQUISTI EFFETTUATI///////////////////////////////////////

	private void expenseSearch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getServletContext().getRequestDispatcher("/jsp/expense_search.jsp").include(request, response);
	}

	//////////////////////////////////PAGINA RICERCA UTILI///////////////////////////////

	private void  earningResearch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getServletContext().getRequestDispatcher("/jsp/earning_research.jsp").include(request, response);
	}


	///////////////////////////////////////PAGINA APPUNTAMENTO DA CON CLICK SU FULL CALENDAR///////////////////////

	private void singleApp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getServletContext().getRequestDispatcher("/jsp/appointment_summary.jsp").include(request, response);
	}

}