package it.gestionalenails.web;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import it.gestionalenails.bussines.customer.CustomerServiceBeanLocal;
import it.gestionalenails.data.Appuntamento;
import it.gestionalenails.data.Cliente;
import it.gestionalenails.data.ModelCalendar;
import it.gestionalenails.data.Utente;

/**
 * Servlet implementation class UpdateApp
 */
@WebServlet("/updateApp")
public class UpdateApp extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	CustomerServiceBeanLocal customerService;


	public UpdateApp() {
		super();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
		Utente utn= (Utente) request.getSession().getAttribute("userLog");
		long user_id = utn.getId();
		List<Appuntamento> appointmentList = customerService.appointmentSearch(user_id); //customerService.appointmentSearch(false);
		Cliente clt = null;

		List<ModelCalendar> calendarList = new ArrayList<ModelCalendar>();


		for(Appuntamento app :appointmentList) {
			ModelCalendar calendar = new ModelCalendar();
			clt = customerService.findCustomer(app.getIdClienteApp()); 
			calendar.setTitle(clt.getCognome() + " " + clt.getNome());
			if(app.isStatoAppuntamento() == true) {
				calendar.setColor("#9ACD32");
			}
			else if(app.isStatoAppuntamento() == false) {
				calendar.setColor("pink");
			}
			calendar.setId(app.getId());
			calendar.setStart(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(app.getDataAppuntamento().getTime()));
			calendarList.add(calendar);

		}

		Gson gson = new Gson();
		String jsonAppointment = gson.toJson(calendarList);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		try {
			response.getWriter().write(jsonAppointment);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
