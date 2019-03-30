package it.gestionalenails.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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

		case "searchAllUsers":
			searchAllUsers(request,response);
			break;

		case "visualAllUser":
			request.getServletContext().getRequestDispatcher("/jsp/admin/header.jsp").include(request, response);
			request.getServletContext().getRequestDispatcher("/jsp/admin/nav.jsp").include(request, response);
			request.getServletContext().getRequestDispatcher("/jsp/admin/visualUser.jsp").include(request, response);
			break;


		case "userDetail":
			userDetail(request, response);
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


	////////////////////////////////////VISUALIZZA TUTTI UTENTI///////////////////////////////////

	private void searchAllUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Utente> allUser ;
		allUser =  (List<Utente>) userServiceBean.allUser();
		if(allUser != null) {
			request.getSession().setAttribute("listaAllUser", allUser);

		}

	}

	////////////////////////////////////////DETTAGLIO UTENTE/////////////////////////////////////////

	private void userDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int index = Integer.parseInt(request.getParameter("rowApp".trim())) - 1;
		List<Utente> utnList =  (List<Utente>) request.getSession().getAttribute("listaAllUser");
		request.setAttribute("userDetail", utnList.get(index));
		request.getServletContext().getRequestDispatcher("/jsp/admin/header.jsp").include(request, response);
		request.getServletContext().getRequestDispatcher("/jsp/admin/nav.jsp").include(request, response);
		request.getServletContext().getRequestDispatcher("/jsp/admin/userCreationPage.jsp").include(request, response);
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

		case "controllUserName":
			controllUserName(request,response);
			break;

		case "delete_users":
			deleteUsers(request, response);
			break;	

		case "searchUser":
			searchUser(request, response);
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
			response.setContentType("text/html");
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

		JsonObject data = new Gson().fromJson(request.getReader(), JsonObject.class);  //recupero json con dati
		Utente utn = new Utente();
		if(data.get("id") != null) {
			utn.setId(Long.valueOf(data.get("id").getAsString().trim()));
		}
		utn.setDataCreazione(Calendar.getInstance());
		utn.setCognome(data.get("cognome").getAsString());
		utn.setNome(data.get("nome").getAsString());
		utn.setCodFiscale(data.get("codFisc").getAsString());
		String []arrydataDiNascita = data.get("dataNascita").getAsString().split("-");
		Calendar dataDiNascita =  AccountingServlet.dateBuilder(arrydataDiNascita);
		utn.setDataDiNascita(dataDiNascita);
		utn.setCittaDiNascita(data.get("luogoNascita").getAsString());
		utn.setIndirizzo(data.get("indirizzo").getAsString());
		utn.setCittaResidenza(data.get("citta").getAsString());
		utn.setCap(data.get("cap").getAsString());
		utn.setTelefono(data.get("telefono").getAsString());
		utn.setEmail(data.get("email").getAsString());
		utn.setUsername(data.get("username").getAsString());
		utn.setPassword(data.get("password").getAsString());
		String []arrayDataAttiv = data.get("dataAttiv").getAsString().split("-");
		Calendar dataAttiv =  AccountingServlet.dateBuilder(arrayDataAttiv);
		utn.setDataAttiv(dataAttiv);
		String []arrayDataScad = data.get("dataScad").getAsString().split("-");
		Calendar dataScad = AccountingServlet.dateBuilder(arrayDataScad);
		utn.setDataScad(dataScad);
		String role =data.get("ruolo").getAsString();
		if(role.equals("admin")) {
			utn.setRole(true);
		}
		else {
			utn.setRole(false);
		}


		if(data.get("id") == null) {
			if(userService.createUser(utn)) { //salavatggio user in db se il nome utente non è gia in uso
				searchAllUsers(request,response);
				try {
					response.getWriter().write("create");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			else {
				try {
					response.getWriter().write("existing");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		else if(data.get("id") != null) {
			userServiceBean.updateUsers(utn);
			searchAllUsers(request, response);
			try {
				response.getWriter().write("modify");
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}

	//////////////////////////////////controllo se nome utente è gia in uso//////////////////////////////
	private void controllUserName(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		JsonObject data = new Gson().fromJson(request.getReader(), JsonObject.class);  //recupero json con dati
		long id = 0;
		String nomeUtente = data.get("usr").getAsString();
		if(data.get("idUser") != null) {
			id = Long.valueOf(data.get("idUser").getAsString().trim());
		}


		if(userServiceBean.userControll(nomeUtente,id)) {
			try {
				response.getWriter().write("disp");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else if(!userServiceBean.userControll(nomeUtente,id)) {
			try {
				response.getWriter().write("inUso");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	//////////////////////////////////////DELETE USERS////////////////////////////////////////////////////
	private void deleteUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String []checkBox = request.getParameterValues("my_check");
		List<Utente> listUsers = (List<Utente>) request.getSession().getAttribute("listaAllUser");
		for(int index = 0; index < checkBox.length; index++) { 
			Utente utn = listUsers.get(Integer.parseInt(checkBox[index])- 1);
			userServiceBean.deleteUsers(utn.getId());
		}
		searchAllUsers(request,response);
		request.setAttribute("flag", "delete");
		request.getServletContext().getRequestDispatcher("/jsp/admin/header.jsp").include(request, response);
		request.getServletContext().getRequestDispatcher("/jsp/admin/nav.jsp").include(request, response);
		request.getServletContext().getRequestDispatcher("/jsp/admin/visualUser.jsp").include(request, response);
	}


	///////////////////////////////////RICERCA UTENTE///////////////////////////////
	private void searchUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName = request.getParameter("field".trim());
        List<Utente> userList =  userServiceBean.ricercaUtente(userName);
		if(!userList.isEmpty()) {
			request.getSession().setAttribute("listaAllUser", userList);
			request.getServletContext().getRequestDispatcher("/jsp/admin/header.jsp").include(request, response);
			request.getServletContext().getRequestDispatcher("/jsp/admin/nav.jsp").include(request, response);
			request.getServletContext().getRequestDispatcher("/jsp/admin/visualUser.jsp").include(request, response);
		}
		else if(userList.isEmpty()) {
			request.setAttribute("flag", "empy");
			request.getServletContext().getRequestDispatcher("/jsp/admin/header.jsp").include(request, response);
			request.getServletContext().getRequestDispatcher("/jsp/admin/nav.jsp").include(request, response);
			request.getServletContext().getRequestDispatcher("/jsp/admin/main.jsp").include(request, response);
		}
		

	}

}
