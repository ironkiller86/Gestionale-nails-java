package it.gestionalenails.web;

import java.io.IOException;
import java.util.Calendar;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import it.gestionalenails.bussines.userbean.UserServiceBeanLocal;
import it.gestionalenails.data.Utente;

/**
 * Servlet implementation class AdminController
 */
@WebServlet("/adminController")
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	private UserServiceBeanLocal userServiceBean;
	@EJB
	private UserServiceBeanLocal userService;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminController() {
		super();

	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("page");

		switch(action) {

		case "login":
			ServletContext context	= request.getServletContext();
			RequestDispatcher req = context.getRequestDispatcher("/jsp/admin/loginAdminPage.jsp");
			req.forward(request, response);
			break;

		case "homePage":
			request.getServletContext().getRequestDispatcher("/jsp/admin/header.jsp").include(request, response);
			request.getServletContext().getRequestDispatcher("/jsp/admin/nav.jsp").include(request, response);
			request.getServletContext().getRequestDispatcher("/jsp/admin/main.jsp").include(request, response);
			break;

		case "logOut":
			logOut(request, response);
			break;

		case "userCreation":
			request.getServletContext().getRequestDispatcher("/jsp/admin/header.jsp").include(request, response);
			request.getServletContext().getRequestDispatcher("/jsp/admin/nav.jsp").include(request, response);
			request.getServletContext().getRequestDispatcher("/jsp/admin/userCreationPage.jsp").include(request, response);
			break;	

		}


	}


	/////////////////////////////////////////logOut///////////////////////////////////////////////////////
	private void logOut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Cookie cookie = new Cookie("cookie","");
		cookie.setMaxAge(0);
		response.addCookie(cookie);
		session.invalidate();
		request.getServletContext().getRequestDispatcher("/jsp/admin/loginAdminPage.jsp").forward(request, response); 

	}




	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String action = request.getParameter("action");

		switch(action) {

		case "loginControll":
			loginControll(request,response);
			break;

		case "userSave":
			userSave(request,response);
			break;

		}

	}

	private void loginControll(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {

		JsonObject data = new Gson().fromJson(request.getReader(), JsonObject.class); 
		String usr = data.get("utente").getAsString().trim();
		String password = data.get("password").getAsString().trim();
		if(userServiceBean.userValidation(usr,password) != null) {
			Utente utn = userServiceBean.userValidation(usr,password);
			Utente user = new Utente();  
			user.setId(utn.getId());
			user.setPassword(utn.getPassword());
			user.setUsername(utn.getUsername());
			user.setRole(utn.isRole());
			if(user.isRole()) {                                          //caso admin
				Cookie cookie = new Cookie("cookie","2");
				response.addCookie(cookie);
				request.getSession().setAttribute("userLogAdmin", user);
				//System.out.println(user.getUsername() + " " + user.getPassword() + " " + user.isRole());
				String message = "admin";
				response.setContentType("text/html");
				response.setCharacterEncoding("UTF-8");
				try {
					response.getWriter().write(message);
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
			else if(!user.isRole() ) {                               // se non admin
				String message = "noAdmin";
				response.setContentType("text/html");
				response.setCharacterEncoding("UTF-8");
				try {
					response.getWriter().write(message);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		else {
			String message = "noMatch";                          //nome utente o password non combaciano
			response.setContentType("html/html");
			response.setCharacterEncoding("UTF-8");
			try {
				response.getWriter().write(message);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	////////////Convalidazione e Salvataggio Utente in db/////////////////

	private void userSave(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		String cognome = request.getParameter("cognome");
		String nome = request.getParameter("nome");
		String codFiscale = request.getParameter("cod_fiscale");
		String []arrydataDiNascita = request.getParameter("dataNascita").split("-");
		Calendar dataDiNascita =  AccountingServlet.dateBuilder(arrydataDiNascita);
		String cittaNascita = request.getParameter("cittaNascita");
		String indirizzo  = request.getParameter("indirizzo");
		String cittaResidenza = request.getParameter("cittaResidenza");
		String cap = request.getParameter("cap");
		String telefono = request.getParameter("telefono");
		String email = request.getParameter("email");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String []arrayDataAttiv = request.getParameter("dataAttivazione").split("-");
		Calendar dataAttiv =  AccountingServlet.dateBuilder(arrayDataAttiv);
		String []arrayDataScad = request.getParameter("dataScadenza").split("-");
		Calendar dataScad = AccountingServlet.dateBuilder(arrayDataScad);
		String role = request.getParameter("role");


		Utente utn = new Utente();
		utn.setCognome(cognome);
		utn.setNome(nome);
		utn.setCodFiscale(codFiscale);
		utn.setDataDiNascita(dataDiNascita);
		utn.setCittaDiNascita(cittaNascita);
		utn.setIndirizzo(indirizzo);
		utn.setCittaResidenza(cittaResidenza);
		utn.setCap(cap);
		utn.setTelefono(telefono);
		utn.setEmail(email);
		utn.setUsername(username);
		utn.setPassword(password);
		utn.setDataAttiv(dataAttiv);
		utn.setDataScad(dataScad);
		if(role.equals("admin")) {
           utn.setRole(true);
		}
		else {
			utn.setRole(false);
		}
		
       userService.createUser(utn);
      
	}

}
