package it.gestionalenails.web;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Properties;

import javax.ejb.EJB;
import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import it.gestionalenails.bussines.customer.CustomerServiceBeanLocal;
import it.gestionalenails.data.Appuntamento;
import it.gestionalenails.data.Cliente;
import it.gestionalenails.data.Utente;

/**
 * Servlet implementation class BusinessAppointment
 */
@WebServlet("/businessAppointment")
@MultipartConfig
public class BusinessAppointment extends HttpServlet {
	private static final long serialVersionUID = 1L;


	@EJB
	CustomerServiceBeanLocal customerService;



	public BusinessAppointment() {
		super();

	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String actionList = request.getParameter("action");
		if(actionList.equals("read_image")) {
			readImage(request, response);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String actionList = request.getParameter("action");

		if(actionList.equals("single_app")) {
			viewSingleApp(request, response);
		}

		request.getServletContext().getRequestDispatcher("/jsp/home/header.jsp").include(request, response);

		switch(actionList) {

		case "creation_work_appointment":
			creationWorkApp(request, response);
			break;


		case "appointment_search":
			appointmentSearch(request,response);
			break;


		case "modify_work_appointment":
			modifyWorkApp(request,response);
			break;


		case "delete_appointment":
			deleteAppointment(request, response);
			break;


		}
		request.getServletContext().getRequestDispatcher("/jsp/home/footer.jsp").include(request, response);
	}

	//////////////////////////CREAZIONE APPUNTAMENTO DI LAVORO////////////////////////////////////

	private void creationWorkApp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Cliente> listaCustomer = (List<Cliente>) request.getSession().getAttribute("costumerList");  //recupero dalla session la lista clienti

		int index =  (int) request.getSession().getAttribute("indexCostumer");                            //recupero dall session l'indice relativo al moi cliente
		Cliente cliente = listaCustomer.get(index);

		Appuntamento app = recovers(request, cliente, null,"incomplete");       // questo metodo mi gestisce la logica di creazione modifica e salvataggio appuntamento 
		customerService.createAppointment(app);
		request.setAttribute("flag_appointment", "new");


		request.getServletContext().getRequestDispatcher("/jsp/info_creation_appointment.jsp").include(request, response);
	}


	////////////////////////////////////////////RICERCA APPUNTAMENTI/////////////////////////////////////////////////////

	private void appointmentSearch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Utente utn= (Utente) request.getSession().getAttribute("userLog");
		long user_id = utn.getId();
		boolean isFinish = false;
		String nominative = request.getParameter("nominative_search");              //recupero nome per ricerca  appuntamento dalla jsp
		String dataOriginale = request.getParameter("data_app");                    // recupero la data dalla jsp per la ricercca
		String flagAppointment = request.getParameter("checkEnd");
		if(flagAppointment != null && flagAppointment.equals("finish")) {           // recupero stato ricerca appuntamento       
			isFinish = true;
		}
		String data =dataOriginale.replaceAll("-", "/");    //sostituisco i trattini della data con le sbarrette

		if(customerService.search(user_id,nominative,data,isFinish) != null) {
			request.getSession().setAttribute("List_appointment_find", customerService.search(user_id,nominative,data,isFinish));  //metto in sessione la lista degli appuntamenti trovati
			request.getSession().setAttribute("costumerList",customerService.findCustomer(nominative,user_id));      // metto in sessione i clienti per i quali cerco l'appuntamento

			request.getServletContext().getRequestDispatcher("/jsp/appointment_summary.jsp").include(request, response);
		}

		//indirizzamnto ad appuntamento non trovato
		else {
			request.getServletContext().getRequestDispatcher("/jsp/info_search.jsp").include(request, response);
		}

	}


	//////////////////////////////////MODIFICA O COMPLETA APPUNTAMENTO DI LAVORO///////////////////////////////////////

	private void modifyWorkApp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String workStatus = request.getParameter("radiob");
		Cliente clt = (Cliente) request.getSession().getAttribute("customer");                      //recupero dalla jsp modify_Appointment il cliente in questione 
		Appuntamento app = (Appuntamento) request.getSession().getAttribute("appointment_object");  //recupero dalla sessione l'appuntamneto in questione
		Appuntamento appointment = null;

		appointment = recovers(request,clt,app,workStatus);          // questo metodo mi gestisce la logica di creazione modifica e salvataggio appuntamento 
		customerService.updateAppointment(appointment);

		request.getServletContext().getRequestDispatcher("/jsp/info_modify_appointment.jsp").include(request, response);

	}


	/**
	 *  
	 * @param request
	 * @param cliente
	 * @param appuntamento
	 * @param workStatus
	 * @return
	 */
	private Appuntamento recovers(HttpServletRequest request,Cliente cliente,Appuntamento appuntamento,String workStatus ) {
		double totalAmount = 0; 
		String dataAppointment = request.getParameter("data_app");
		String appointmentTime = request.getParameter("orarioApp");
		String jobDescription = request.getParameter("job_description");
		Calendar data = new GregorianCalendar();
		String dataStringArray[] = dataAppointment.split("-");                                 //recupero data e sotto orario appuntamento
		String oraStringArray[] = appointmentTime.split(":");
		data.set(Calendar.YEAR, Integer.parseInt(dataStringArray[0]));
		data.set(Calendar.MONTH, Integer.parseInt(dataStringArray[1]) - 1);
		data.set(Calendar.DATE, Integer.parseInt(dataStringArray[2]));
		data.set(Calendar.HOUR_OF_DAY, Integer.parseInt(oraStringArray[0]));
		data.set(Calendar.MINUTE, Integer.parseInt(oraStringArray[1]));
		Appuntamento appMod = new Appuntamento();
		if(appuntamento != null) {
			appMod.setId(appuntamento.getId());

		}
		if(workStatus.equals("incomplete")) {                     //lavoro non completo
			appMod.setStatoAppuntamento(false);
			appMod.setId_user(cliente.getId_user());
		}
		else if(workStatus.equals("end_work")) {                               // se il lavoro  è completo recupero gli altrio dati dal form e li setto nel bean
			appMod.setStatoAppuntamento(true);
			appMod.setId_user(cliente.getId_user());
			totalAmount = Double.parseDouble(request.getParameter("amount"));
			Part filePart  = null;
			String imgName = null;
			InputStream fileContent = null;
			FileOutputStream out = null;
			try {
				filePart =  request.getPart("fname");
				imgName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); 
				fileContent = filePart.getInputStream();
                int cursor;
                FileReader reader = new FileReader( "C://gestionle-nails-config//"+ "config.properties");
				Properties pro = new Properties();
				pro.load(reader);
				String path = pro.getProperty("path");
                out = new FileOutputStream( path + imgName);
				while((cursor = fileContent.read()) != -1) {
					out.write(cursor);

				}

			} catch (IOException | ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				if(fileContent != null){
					try {
						fileContent.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if(out != null){
					try {
						out.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}



			appMod.setPathImage(imgName);              // aggiungo il percorso per visulizzare l'immagine in questione
			appMod.setPrezzo(totalAmount);
		}
		appMod.setIdClienteApp(cliente.getId_cliente());
		appMod.setDataAppuntamento(data);
		appMod.setDescrizioneLavoro(jobDescription);

		return appMod;
	}


	///////////////////////////////////////CANCELLA APPUNTAMENTI SELEZIONATI///////////////////////////////

	private void deleteAppointment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String []checkBox = request.getParameterValues("my_check");                             //recupero il contenuto delle checkbox della tabella degli appuntamenti ricercati
		List<Appuntamento> appointment_list = (List<Appuntamento>) request.getSession().getAttribute("List_appointment_find");  //recupero dalla sessione la lista degli app presenti in tabelle
		for(int index = 0; index < checkBox.length; index++) {                                  //elimino gli appuntamenti ciclando il numero delle checkbox con il numero degli appuntamenti
			Appuntamento app = appointment_list.get(Integer.parseInt(checkBox[index]) - 1);     //in questo modo posso eliminare piu appuntamenti contemporaneamente
			customerService.deleteAppointment(app.getId());
		}

		request.getServletContext().getRequestDispatcher("/jsp/info_appointment_delete.jsp").include(request, response);
	}


	////////////////////////////////////////////////////////////////////////////

	private void readImage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fileName = request.getParameter("path");
		response.setContentType("image/jpg");
		int width = 2000;    //width of the image
		int height = 2000;   //height of the image
		BufferedImage img = null;
		File f = null;
		try{
			f = new File("C:\\Users\\DONATO\\Desktop\\foto\\" + fileName); //image file path
			img = new BufferedImage(1000, 1000, BufferedImage.TYPE_INT_ARGB);
			img = ImageIO.read(f);
			OutputStream out = response.getOutputStream();
			ImageIO.write(img, "jpg", out);
			System.out.println("Reading complete." + " " + img.getWidth());
		}catch(IOException e){
			System.out.println("Error: "+e.getMessage());
		}
	}

	//////////////////////////////// RECUPERO DA FULLCALENDAR DEI DATI PER VISULAIZZARE APPUNTAMENTO////////////////////
	private void viewSingleApp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JsonObject data = new Gson().fromJson(request.getReader(), JsonObject.class);     // RECUPERO jSON
		long id_app = Long.parseLong(data.get("id").toString().trim());    //recupero id app
		Appuntamento app = null;
		Cliente clt = null;
		app =  customerService.singleAppointmentSearch(id_app);          //query per object Appuntamento da DB
		clt = customerService.findCustomer(app.getIdClienteApp());        //query per object Cliente da DB
		List<Appuntamento> listApp = new ArrayList<Appuntamento>(1);
		List<Cliente> listClt = new ArrayList<Cliente>(1);
		listApp.add(app);
		listClt.add(clt);
		request.getSession().setAttribute("List_appointment_find", listApp); 
		request.getSession().setAttribute("costumerList", listClt);

	}


}
