package it.gestionalenails.data;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;




@Entity
@Table(name="appuntamento")
public class Appuntamento implements Serializable {
	private static final long serialVersionUID = -964076129542573936L;
	private long id_user;
	private Long id;
	private Long idClienteApp;
	private Calendar dataAppuntamento;
	private String descrizioneLavoro;
	private double prezzo;
	private boolean statoAppuntamento;
	private String pathImage;
	private List<Appuntamento> ListAppointment  = new ArrayList<Appuntamento>();

    
	public Appuntamento() {
		
	}
	
	
	

	




	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_appuntamento",nullable=false)
	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}
	
	
	
	@Column(name="id_user",nullable=false)
	public long getId_user() {
		return id_user;
	}




	public void setId_user(long id_user) {
		this.id_user = id_user;
	}


  
	@Column(name="data_appuntamento",nullable=false)
	public Calendar getDataAppuntamento() {
		return dataAppuntamento;
	}



	
	public void setDataAppuntamento(Calendar dataAppuntamento) {
		this.dataAppuntamento = dataAppuntamento;
	}

 
	@Column(name="descrizione_lavoro",nullable=false)
	public String getDescrizioneLavoro() {
		return descrizioneLavoro;
	}




	public void setDescrizioneLavoro(String descrizioneLavoro) {
		this.descrizioneLavoro = descrizioneLavoro.trim();
	}



	@Column(name="prezzo")
	public double getPrezzo() {
		return prezzo;
	}





	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}



	@Column(name="appuntamento_terminato")
	public boolean isStatoAppuntamento() {
		return statoAppuntamento;
	}




	public void setStatoAppuntamento(boolean statoAppuntamento) {
		this.statoAppuntamento =  statoAppuntamento;
	}

	@Column(name="image")
	public String getPathImage() {
		return  pathImage;
	}



	public void setPathImage(String image) {
		this.pathImage =  image;                        //"http://127.0.0.1:8887/"
	}


    
	@Column(name="id_cliente",nullable=false)
	public Long getIdClienteApp() {
		return idClienteApp;
	}



	public void setIdClienteApp(Long idClienteApp) {
		this.idClienteApp = idClienteApp;
	}


	@Transient
	public String readImage(String fileName) {
		//String path ="C:\\Users\\DONATO\\Desktop\\image";
		int width = 2000;    //width of the image
		int height = 2000;   //height of the image
		BufferedImage img = null;
		File f = null;
		try{
			f = new File("C:\\Users\\DONATO\\Desktop\\img" , fileName); //image file path
			
			img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

			img = ImageIO.read(f);
			System.out.println("Reading complete." + " " + img.getWidth());
		}catch(IOException e){
			System.out.println("Error: "+e);
		}
		return f.getAbsolutePath();
	}

	@Transient
	public List<Appuntamento> getListAppointment() {
		return ListAppointment;
	}


    
	public void setListAppointment(List<Appuntamento> listAppointment) {
		ListAppointment = listAppointment;
	}








}
