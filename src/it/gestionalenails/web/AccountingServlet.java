package it.gestionalenails.web;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.DeviceCmyk;
import com.itextpdf.kernel.colors.DeviceGray;
import com.itextpdf.kernel.colors.DeviceN;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;

import it.gestionalenails.bussines.accounting.AccountingLocal;
import it.gestionalenails.bussines.customer.CustomerServiceBeanLocal;
import it.gestionalenails.bussines.expense.ExpenseLocal;
import it.gestionalenails.data.Appuntamento;
import it.gestionalenails.data.Cliente;
import it.gestionalenails.data.Product;
import it.gestionalenails.data.Utente;


/**
 * Servlet implementation class AccountingServlet
 */
@WebServlet("/accountingServlet")
public class AccountingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;



	@EJB
	AccountingLocal accounting;
	@EJB
	CustomerServiceBeanLocal customerService;

	@EJB
	ExpenseLocal expense;
	public AccountingServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}





	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String operationType = request.getParameter("operation");


		switch(operationType) {

		case "resume":

			ServletContext sc = request.getServletContext();
			RequestDispatcher rd = sc.getRequestDispatcher("/jsp/home/header.jsp");
			rd.include(request, response);
			resume(request,response);
			request.getServletContext().getRequestDispatcher("/jsp/home/footer.jsp").include(request, response);
			break;

		case "total_amount":
			totalAmountPdf(request,response);
			break;	

		case "total_expense_pdf":
			totalExpensePdf(request,response);
			break;

		case "total_profit_pdf":
			totalProfitPdf(request,response);
			break;	

		case "expense":
			expenseManagement(request,response);
			break;

		case "search":
			search(request,response);
			break;	

		case  "deleteExpense":
			deleteExpense(request,response);
			break;	

		case  "expense_modification":
			expenseModification(request,response);
			break;		

		case "expense_update":	
			expenseUpdate(request,response);
			break;

		case "earning_calculation":	
			earningCalculation(request,response);
			break;	
		}
	}



	private void resume(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        
		Utente utn= (Utente) request.getSession().getAttribute("userLog");
		long user_id = utn.getId();
		String dataStart =(String) request.getParameter("data_start");
		String dataEnd =(String)  request.getParameter("data_end");
		String flagCash = (String) request.getParameter("checkEnd");
		String[] textString = {dataStart,dataEnd,flagCash};                   //metto in array di String i tre campi di ricerca dei conteggi
		request.getSession().setAttribute("textPdf", textString);             //metto l'array in sessione per visualizzare i suddetti campi nel pdf
		int empyField = 0;

		if(dataStart.equals("")) {                                           //controllo campi di ricerca per convalida
			empyField ++;
		}
		if(dataEnd.equals("")) {
			empyField ++;
		}
		if(flagCash == null) {
			empyField ++;
		}

		if(empyField < 3) {
			List<Appuntamento> accountingList = accounting.accountingList(dataStart,dataEnd,flagCash, user_id); // recupero dal db la lista degli appuntamenti in base al conteggio selezionato
			if(accountingList != null && !accountingList.isEmpty()) {
				List<Cliente> clientiTotal = customerService.findCustomer("",user_id);          // recupero dal db tutti i clienti presenti
				request.setAttribute("accounting_appointment", accountingList);        //metto request la lista degli appuntameti e dei clienti
				request.setAttribute("customer_accounting", clientiTotal);
				float total = 0;
				for(Appuntamento apt : accountingList) {                               //sommo il totale del guadagno in base agli appuntamenti selezionati e metto l'importo in request
					total += apt.getPrezzo();
				}
				request.getSession().setAttribute("totalMoney",total);
				request.getServletContext().getRequestDispatcher("/jsp/accounting_summary.jsp").include(request, response);
			}
			else {
				request.getServletContext().getRequestDispatcher("/jsp/info_search_accounting.jsp").include(request, response);
			}
		}
		else {
			request.getServletContext().getRequestDispatcher("/jsp/info_search_accounting.jsp").include(request, response);
		}
	}

	///////////////////////////////////CREAZIONE PDF CONTEGGIO TOTALE IN BASE ALLE DATE////////////////////////////

	private void totalAmountPdf(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Appuntamento> appointmentList = (List<Appuntamento>) request.getSession().getAttribute("appointmentList");
		List<Cliente> customerList =(List<Cliente>) request.getSession().getAttribute("costumerList");
		String []textpdf =  (String[]) request.getSession().getAttribute("textPdf");         //
		SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy kk:mm");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM yyyy");
		Utente utr = (Utente) request.getSession().getAttribute("userLog");          //recupero dalla sessione il nome utente
		float total = (float) request.getSession().getAttribute("totalMoney");       //recupero il totale dalla sessione per visulaizzarlo nel pdf

		PdfWriter pdf = new PdfWriter(response.getOutputStream());
		PdfDocument doc = new PdfDocument(pdf);
		Document document = new Document(doc);

		Text text = new Text("Documento creato il " +  fmt.format(new Date().getTime()) + " da " +  utr.getUsername());
		text.setFontSize(9);
		text.setRelativePosition(300,0, 0,0);
		Paragraph prg1 = new Paragraph();
		prg1.add(text);
		document.add(prg1);
		document.add(new Paragraph("\n"));

		Paragraph prg = new Paragraph();
		prg.setTextAlignment(TextAlignment.CENTER);
		prg.setFontSize(15);


		if(textpdf[0].equals("") && textpdf[1].equals("") && textpdf[2] != null) {       //totale app della giornata corrette
			prg.add("TOTALE INCASSO DEL GIORNO " + LocalDate.now().format(formatter));
		}
		if(!textpdf[0].equals("") && textpdf[1].equals("") && textpdf[2] == null) {     //ricerca per appuntamenti di una specirfica giornata
			LocalDate data =  LocalDate.parse(textpdf[0]);
			prg.add("INCASSO APPUNTAMENTI DEL "  + data.format(formatter));
		}
		if(!textpdf[0].equals("") && !textpdf[1].equals("") && textpdf[2] == null) {   // ricerca app dal xxxx al xxxx
			LocalDate dataStart =  LocalDate.parse(textpdf[0]);
			LocalDate dataEnd =  LocalDate.parse(textpdf[1]);
			prg.add("INCASSO APPUNTAMENTI DAL " + dataStart.format(formatter) + " AL " + dataEnd.format(formatter));
		}

		document.add(prg);

		float[] columnWidths = {120,80,80,130,60};                                    //setto la larghezza di ogni singola colonna
		Table table = new Table(columnWidths);
		table.setHorizontalAlignment(HorizontalAlignment.CENTER);                    // centra la tabella 
		table.addHeaderCell("Data").setTextAlignment(TextAlignment.CENTER).setFontColor(DeviceGray.BLACK);       //centro il testo nelle colonne
		table.addHeaderCell("Cognome");
		table.addHeaderCell("Nome");
		table.addHeaderCell("Descrizione");
		table.addHeaderCell("Importo");

		for(Appuntamento apt :appointmentList) {
			for(Cliente clt :customerList) {
				if(clt.getId_cliente() == apt.getIdClienteApp()) {
					table.addCell( fmt.format(apt.getDataAppuntamento().getTime())); 
					table.addCell(clt.getCognome());
					table.addCell(clt.getNome());

				}	
			}
			table.addCell(apt.getDescrizioneLavoro());
			table.addCell("€ " + String.format("%.2f", apt.getPrezzo()));
		}

		Text textTotal = new Text("Totale    €  " +  String.format("%.2f" ,total));
		textTotal.setFontSize(12);
		textTotal.setRelativePosition(180,0,0,0);
		Cell cell = new Cell(1,5);
		cell.add(new Paragraph().add(textTotal).setFontColor(DeviceRgb.GREEN));  
		table.addCell(cell);
		document.add(table);
		document.close();
		response.setContentType("application/pdf");
		response.setHeader("Content-disposition", "attachment; filename=entrate.pdf");
	}

	//////////////////////////CREAZIONE PDF SPESE EFFETTUATE///////////////////////////

	private void totalExpensePdf(HttpServletRequest request ,HttpServletResponse response) throws ServletException, IOException {
		SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy kk:mm");
		SimpleDateFormat fmt2 = new SimpleDateFormat("dd/MM/yyyy");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM yyyy");
		List<Product> expenseList =(List<Product>) request.getSession().getAttribute("expenseList");
		String[]field =(String[]) request.getSession().getAttribute("field_expense");
		Utente utr = (Utente) request.getSession().getAttribute("userLog");          //recupero dalla sessione il nome utente
		float total = (float) request.getSession().getAttribute("totalExpense"); 

		PdfWriter pdf = new PdfWriter(response.getOutputStream());
		PdfDocument doc = new PdfDocument(pdf);
		Document document = new Document(doc);
		Text text = new Text("Documento creato il " +  fmt.format(new Date().getTime()) + " da " +  utr.getUsername());
		text.setFontSize(9);
		text.setRelativePosition(300,0, 0,0);
		Paragraph prg1 = new Paragraph();
		prg1.add(text);
		document.add(prg1);
		document.add(new Paragraph("\n"));

		Paragraph prg = new Paragraph();
		prg.setTextAlignment(TextAlignment.CENTER);
		prg.setFontSize(15);

		if(!field[0].equals("") && field[1].equals("") && field[2].equals("")) {       // totale spese per un determinato prodotto
			prg.add("TOTALE COMPLESSIVO SPESO PER PRODOTTO: " + field[0]);
		}
		else if(field[0].equals("") && !field[1].equals("") && field[2].equals("")) {     // Totael speso per singola giornata specificata in field
			LocalDate dataStart =  LocalDate.parse(field[1]);
			prg.add("TOTALE USCITE DEL "  + dataStart.format(formatter));
		}
		else if(field[0].equals("") && !field[1].equals("") && !field[2].equals("")) {   // ricerca speso  dal xxxx al xxxx
			LocalDate dataStart =  LocalDate.parse(field[1]);
			LocalDate dataEnd =  LocalDate.parse(field[2]);
			prg.add("TOTALE USCITE DAL  " + dataStart.format(formatter) + " AL " + dataEnd.format(formatter));
		}

		document.add(prg);
		float[] columnWidths = {90,50,180,70};                                    //setto la larghezza di ogni singola colonna
		Table table = new Table(columnWidths);
		table.setHorizontalAlignment(HorizontalAlignment.CENTER);                    // centra la tabella 
		table.addHeaderCell("Data").setTextAlignment(TextAlignment.CENTER).setFontColor(DeviceGray.BLACK);       //centro il testo nelle colonne
		table.addHeaderCell("Qty");
		table.addHeaderCell("Descrizione");
		table.addHeaderCell("Importo");

		for(Product prd : expenseList) {
			table.addCell(fmt2.format(prd.getDate().getTime()));
			table.addCell(String.valueOf(prd.getQuantity()));
			table.addCell(prd.getProductName());
			table.addCell(String.format("%.2f", prd.getPrice()));
		}
		Text textTotal = new Text("Totale    €  " +  String.format("%.2f" ,total));
		textTotal.setFontSize(12);
		textTotal.setRelativePosition(130,0,0,0);
		Cell cell = new Cell(1,4);
		cell.add(new Paragraph().add(textTotal).setFontColor(DeviceRgb.RED));  
		table.addCell(cell);
		document.add(table);

		document.close();
		response.setContentType("application/pdf");
		response.setHeader("Content-disposition", "attachment; filename=spese.pdf");
	}

	//////////////////////////CREAZIONE PDF UTILI////////////////////////////////////

	private void totalProfitPdf(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy kk:mm");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM yyyy");
		double entry =(double) request.getSession().getAttribute("entry");                 //recupero entrate totali
		double expense =(double) request.getSession().getAttribute("expense");             // recupero uscite spese totali
		double profit =(double) request.getSession().getAttribute("profit");               // recupero utili
		String[] field = (String[]) request.getSession().getAttribute("field_profit");     // recupero i camopi di ricerca dal form di ricerca utili
		Utente utr = (Utente) request.getSession().getAttribute("userLog");                //recupero dalla sessione il nome utente

		PdfWriter pdf = new PdfWriter(response.getOutputStream());
		PdfDocument doc = new PdfDocument(pdf);
		Document document = new Document(doc);
		Text text = new Text("Documento creato il " +  fmt.format(new Date().getTime()) + " da " +  utr.getUsername());
		text.setFontSize(9);
		text.setRelativePosition(300,0, 0,0);
		Paragraph prg1 = new Paragraph();
		prg1.add(text);
		document.add(prg1);
		document.add(new Paragraph("\n"));

		Paragraph prg = new Paragraph();
		prg.setTextAlignment(TextAlignment.CENTER);
		prg.setFontSize(15);
		LocalDate dataStart =  LocalDate.parse(field[0]);
		LocalDate dataEnd =  LocalDate.parse(field[1]);
		prg.add("RIEPILOGO UTILI DAL " +  dataStart.format(formatter) + " AL " +  dataEnd.format(formatter));
		document.add(prg);

		float[] columnWidths = {300,300}; 
		Table table = new Table(columnWidths);
		table.setHorizontalAlignment(HorizontalAlignment.CENTER);                    // centra la tabella 
		table.addHeaderCell("ENTARTE TOTALI").setTextAlignment(TextAlignment.CENTER).setFontColor(DeviceGray.BLACK);       //centro il testo nelle colonne
		table.addHeaderCell("USCITE TOTALI");
		table.addCell(String.format("%.2f", entry));
		table.addCell(String.format("%.2f", expense));
		Text textTotal = new Text("UTILE    €  " +  String.format("%.2f" ,profit));
		textTotal.setFontSize(12);
		textTotal.setRelativePosition(0,0,0,0);
		Cell cell = new Cell(1,2);
		if(profit > 0) {
			cell.add(new Paragraph().add(textTotal).setFontColor(DeviceRgb.GREEN));
		}
		else {
			cell.add(new Paragraph().add(textTotal).setFontColor(DeviceRgb.RED));
		}	

		table.addCell(cell);
		document.add(table);
		document.close();
		response.setContentType("application/pdf");
		response.setHeader("Content-disposition", "attachment; filename=spese.pdf");
	}


	/////////////////////////SEZIONE CREAZIONE SPESE/ATTIVITA/////////////////////////

	private void expenseManagement(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Object> obj = this.fieldBuilder(request);
		Calendar dt =(Calendar) obj.get(0);
		Utente utn= (Utente) request.getSession().getAttribute("userLog");
		long user_id = utn.getId();
		int quantitaPrd =(int) obj.get(1);
		String productName = (String) obj.get(2);
		float price =(float) obj.get(3);
		Product prd = new Product(user_id,dt,quantitaPrd,productName,price);
		expense.addExpense(prd);                               //aggiungo la spesa nel db

		request.setAttribute("product",prd);                  
		request.getServletContext().getRequestDispatcher("/jsp/home/header.jsp").include(request, response);
		request.getServletContext().getRequestDispatcher("/jsp/info_insert_expense.jsp").include(request, response);
		request.getServletContext().getRequestDispatcher("/jsp/home/footer.jsp").include(request, response);

	}

	////////////////////////////////MODIFICA SPESA////////////////////////////////

	private void expenseUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Utente utn= (Utente) request.getSession().getAttribute("userLog");
		long user_id = utn.getId();
		List<Object> obj = this.fieldBuilder(request);           //recupero tramite questo metodo tutti o campi dal form modifica spesa
		Calendar dt =(Calendar) obj.get(0);
		int quantitaPrd =(int) obj.get(1);
		String productName =(String) obj.get(2);
		float price =(float) obj.get(3);
		Long idPrd =(Long) obj.get(4);
		Product prd = new Product(user_id,dt,quantitaPrd,productName,price,idPrd); 
		Product prdMem = (Product) request.getSession().getAttribute("product");    //recupero dalla sessione la spesa che sto per modificare

		if(prdMem != null) {
			if(!prdMem.equals(prd)) {                                                // se la spesa creata non è uguale a quella recuperata dalla sessione 
				expense.expenseUpdate(prd);                                          // significa che  ho effetuato modifiche..quindi faccio l'update
				request.setAttribute("product",prd);
				request.setAttribute("update", true);                  // in base al valore della chiave update nella jsp di aggiornamneto visualizzo o meno le informazioni
				request.getServletContext().getRequestDispatcher("/jsp/home/header.jsp").include(request, response);
				request.getServletContext().getRequestDispatcher("/jsp/info_expense_update.jsp").include(request, response);
				request.getServletContext().getRequestDispatcher("/jsp/home/footer.jsp").include(request, response);
			}
			else if (prdMem.equals(prd)) {                             // se gli oggetti sono uguali, non faccio l'update                             
				request.setAttribute("update", false);                 // in base al valore della chiave update nella jsp di aggiornamneto visualizzo o meno le informazioni
				request.getServletContext().getRequestDispatcher("/jsp/home/header.jsp").include(request, response);
				request.getServletContext().getRequestDispatcher("/jsp/info_expense_update.jsp").include(request, response);
				request.getServletContext().getRequestDispatcher("/jsp/home/footer.jsp").include(request, response);
			}
		}
	}

	//////////////////////////RICERCA SPESA PER NOME O PERIODO DI INSERIMENTO////////////////////////	

	private void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Utente utn= (Utente) request.getSession().getAttribute("userLog");
		long user_id = utn.getId();
		String productName = request.getParameter("product");      //recupero i dati dal form
		String dateSt = request.getParameter("dataInsert");
		String dateEn = request.getParameter("dataEnd");
		String[] arrayfield = {productName,dateSt,dateEn};
		request.getSession().setAttribute("field_expense",arrayfield);   // metto in sessione i campi di ricerca per visulizzarli successivamente nel pdf del resume spese
		Calendar dateStart = null;
		Calendar dateEnd = null;
		if(!dateSt.equals("")) {
			String dataStringArray[] = dateSt.split("-");  
			dateStart = dateBuilder(dataStringArray);                   // metodo che costruisce un oggetto data di tipo Calendar
		}
		if(!dateEn.equals("")) {
			String dataStringArray[] = dateEn.split("-");  
			dateEnd = dateBuilder(dataStringArray);
		}
		List<Product> expenseResult = expense.searchExpense(dateStart,dateEnd ,productName,user_id);   //ricerco le spese secondo i criteri di ricerca

		if(expenseResult != null) {
			if(!expenseResult.isEmpty()) {
				float totalExpense = 0;
				for(Product prd : expenseResult) {
					totalExpense += prd.getPrice();                           //calcolo il totale speso in base ai prodotti acquistati
				}
				request.getSession().setAttribute("totalExpense",totalExpense);   //metto in sessione il totale speso
				request.getSession().setAttribute("expenseList",expenseResult); //metto in sessione la lista dei prodotti acquistati per visualiazzarli nel tabella resume

				request.getServletContext().getRequestDispatcher("/jsp/home/header.jsp").include(request, response);
				request.getServletContext().getRequestDispatcher("/jsp/expense_summary.jsp").include(request, response);
				request.getServletContext().getRequestDispatcher("/jsp/home/footer.jsp").include(request, response);
			}

			else {
				request.getServletContext().getRequestDispatcher("/jsp/home/header.jsp").include(request, response);
				request.getServletContext().getRequestDispatcher("/jsp/info_search_expense_notFound.jsp").include(request, response);
				request.getServletContext().getRequestDispatcher("/jsp/home/footer.jsp").include(request, response);
			}
		}
		else if(expenseResult == null) {
			request.getServletContext().getRequestDispatcher("/jsp/home/header.jsp").include(request, response);
			request.getServletContext().getRequestDispatcher("/jsp/info_search_expense_notFound.jsp").include(request, response);
			request.getServletContext().getRequestDispatcher("/jsp/home/footer.jsp").include(request, response);
		}
	}

	///////////////////////////////////////ELIMINA SPESA/////////////////////////////////

	private void deleteExpense(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] checkBox = request.getParameterValues("my_check"); 
		List<Product> productList = (List<Product>)request.getSession().getAttribute("expenseList");
		for(int index = 0; index < checkBox.length; index++) {
			Product prdToDelete = productList.get(Integer.parseInt(checkBox[index]) - 1);
			expense.expenseDelete(prdToDelete.getIdProduct());
		}
		request.getServletContext().getRequestDispatcher("/jsp/home/header.jsp").include(request, response);
		request.getServletContext().getRequestDispatcher("/jsp/info_delete_expense.jsp").include(request, response);
		request.getServletContext().getRequestDispatcher("/jsp/home/footer.jsp").include(request, response);
	}

	//////////////////////////////////////LINK A PAGINA MODIFICA SPESA/////////////////////////////

	private void expenseModification(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int index =  Integer.parseInt(request.getParameter("row".trim())) - 1; 
		List<Product> products = (List<Product>) request.getSession().getAttribute("expenseList");
		Product prd = products.get(index);
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy/MM/dd");
		String date = fmt.format(prd.getDate().getTime());
		String date_ok = date.replaceAll("/", "-");
		request.getSession().setAttribute("product", prd);
		request.setAttribute("data", date_ok);
		request.getServletContext().getRequestDispatcher("/jsp/home/header.jsp").include(request, response);
		request.getServletContext().getRequestDispatcher("/jsp/expense_modification.jsp").include(request, response);
		request.getServletContext().getRequestDispatcher("/jsp/home/footer.jsp").include(request, response);
	}

	///////////////////////////////CALCOLA UTILI IN BASE AI CRITERI DI RICERCA///////////////////////////////

	private void earningCalculation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Utente utn= (Utente) request.getSession().getAttribute("userLog");
		long user_id = utn.getId();
		String data1 = request.getParameter("data_start");
		String data2 = request.getParameter("data_end");
		String[] arrayfield = {data1,data2};
		request.getSession().setAttribute("field_profit",arrayfield);            // metto in session i campi di ricerca per visulaizzarli nel pdf
		Calendar dataStart = dateBuilder(data1.split("-"));
		Calendar dataEnd = dateBuilder(data2.split("-"));
		List<Appuntamento> appointmentList = accounting.accountingList(data1, data2, null,user_id);    //estraggo dal db tutti gli appuntamenti del periodo 
		List<Product> productList = expense.searchExpense(dataStart, dataEnd, "",user_id);             // estraggo dal db tutti gli acquisti del periodo
		double totalEntry = 0,totalExpense= 0;

		    for(Appuntamento apt : appointmentList) {
				totalEntry += apt.getPrezzo();                         //calcolo le entrate di tutti gli appunatemnti del periodo  
			}
			for(Product prd : productList) {
				totalExpense += prd.getPrice();                       // calcolo le uscite per  tutti i prodotti acquistati del periodo 
			}

			double totalProfit = totalEntry - totalExpense;           //calcolo gli utili
			request.getSession().setAttribute("entry", totalEntry);
			request.getSession().setAttribute("expense",totalExpense); 
			request.getSession().setAttribute("profit", totalProfit);
			request.getServletContext().getRequestDispatcher("/jsp/home/header.jsp").include(request, response);
			request.getServletContext().getRequestDispatcher("/jsp/profit_summary.jsp").include(request, response);
			request.getServletContext().getRequestDispatcher("/jsp/home/footer.jsp").include(request, response);
		
	}



	////////////////////////metodo che dal campo field date del form mi costruisce un oggetto Calendar///////////
	public static Calendar dateBuilder(String[]dataStringArray) {
		Calendar dt = new GregorianCalendar();
		dt.set(Calendar.YEAR, Integer.parseInt(dataStringArray[0]));
		dt.set(Calendar.MONTH, Integer.parseInt(dataStringArray[1]) - 1);
		dt.set(Calendar.DATE, Integer.parseInt(dataStringArray[2]));
		return dt;
	}

	////////////////////////RECUPERA DATI DAL FORM SPESE///////////////////////////
	private List<Object> fieldBuilder(HttpServletRequest request) {
		Long idPrd = null;
		if(request.getParameter("id_product") != null) {
			idPrd = Long.valueOf(request.getParameter("id_product"));
		}
		String date = request.getParameter("data_acquisto");
		int quantitaPrd = Integer.parseInt(request.getParameter("qt"));
		String dataStringArray[] = date.split("-");  
		Calendar dt = dateBuilder(dataStringArray);
		String productName = request.getParameter("nome_prodotto");
		float price = Float.parseFloat(request.getParameter("totale"));
		List<Object> parameters = new ArrayList<Object>(5);
		parameters.add(dt);
		parameters.add(quantitaPrd);
		parameters.add(productName);
		parameters.add(price);
		parameters.add(idPrd);

		return parameters;
	}
}
