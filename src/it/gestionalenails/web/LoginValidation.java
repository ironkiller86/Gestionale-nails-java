package it.gestionalenails.web;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Properties;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.gestionalenails.bussines.userbean.UserServiceBeanLocal;
import it.gestionalenails.data.Utente;


/**
 * Servlet implementation class LoginValidation
 */
@WebServlet("/loginValidation")
public class LoginValidation extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginValidation() {
		super();

	}




	@Override
	public void init() throws ServletException {
		String path = "C://gestionle-nails-config//";
		File folder = new File(path);

		if(!folder.exists()) {
			folder.mkdirs();
		}
		File configfile = null;
		configfile = new File(path + "config.properties");
		if(!configfile.exists()) {
			try {
				configfile.createNewFile();

				Properties properties = new Properties();
				properties.setProperty("Utente", "Di Carlo Rossella Amore Mio");
				properties.setProperty("project-name", "Gestionale-NAils");
				properties.setProperty("Version", "V1.0 del 13/10/2018");
				properties.setProperty("path", "default");

				FileWriter writer = null;
				writer = new FileWriter(configfile);
				properties.store(writer, "Tuzzoliono Donato");
				writer.close();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
	}




	@EJB
	UserServiceBeanLocal userService;



	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}




	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String userName = request.getParameter("username".trim());
		String password = request.getParameter("password".trim());
		if(userService.userValidation(userName,password) != null) {
			Utente utn = userService.userValidation(userName,password);
			Utente user = new Utente(); 
			user.setId(utn.getId());
			user.setPassword(utn.getPassword());
			user.setUsername(utn.getUsername());
			user.setCognome(utn.getCognome());
			user.setNome(utn.getNome());
			user.setIndirizzo(utn.getIndirizzo());
			user.setCittaResidenza(utn.getCittaResidenza());
			user.setCap(utn.getCap());
			user.setEmail(utn.getEmail());
			user.setTelefono(utn.getTelefono());
			user.setDataAttiv(utn.getDataAttiv());
			user.setDataScad(utn.getDataScad());
			user.setRole(utn.isRole());
            if(user.getDataScad().after(Calendar.getInstance())) {
				Cookie cookie = new Cookie("cookie","1");
				response.addCookie(cookie);
				request.getSession().setAttribute("userLog", user);
				System.out.println(user.getCognome() + " aggiunto");
				
				response.sendRedirect("dispatcher?page=home");
			}
			else if (user.getDataScad().before(Calendar.getInstance())) {
				request.getSession().setAttribute("flag", "endLicenze");
				response.sendRedirect("/gestionale-nails");
			}
		}
		else {
			response.sendRedirect("/gestionale-nails?message=error/");
		}
	}

}
