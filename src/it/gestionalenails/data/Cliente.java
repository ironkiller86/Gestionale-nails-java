package it.gestionalenails.data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import it.corso.java.testInterface.Action;



@Entity
@Table(name="cliente")
public class Cliente implements Serializable {
	private static final long serialVersionUID = 10714766525799268L;
	private long id_user;
	private long id_cliente;
	private String cognome;
	private String nome;
	private String telefono_one;
	private String telefono_two;
	private String email;
	private Timestamp dataCreazione;
	private List<Cliente> costumer;
	
	
	
	public Cliente() {
		
	}
	
	
	public Cliente(long id_user, String cognome,String nome,String telefono_one,String telefono_Two,String email,Timestamp dateToday){
		setId_user(id_user);
		setCognome(cognome);
		setNome(nome);
		setTelefono_one(telefono_one);
		setTelefono_two(telefono_Two);
		setEmail(email);
		setDataCreazione( dateToday);
	}
	
	
	@Column(name="id_user",nullable=false)
	public long getId_user() {
		return id_user;
	}
	
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_cliente",nullable=false)
	public long getId_cliente() {
		return id_cliente;
	}
	
	
	


	
	
	public void setId_user(long id_user) {
		this.id_user = id_user;
	}


	public void setId_cliente(long id_cliente) {
		this.id_cliente = id_cliente;
	}
	
	
	@Column(name="cognome",nullable=false)
	public String getCognome() {
		return cognome;
	}
	
	
	
	public void setCognome(String cognome) {
		Action act = new Action();
		this.cognome = act.upperWord(cognome).trim();
		
	}
	
	
	@Column(name="nome",nullable=false)
	public String getNome() {
		return nome;
	}
	
	
	
	public void setNome(String nome) {
		Action act = new Action();
		
		this.nome =act.upperWord(nome).trim();
	}
	
	
	@Column(name="telefono_one",nullable=false)
	public String getTelefono_one() {
		return telefono_one;
	}
	
	
	
	public void setTelefono_one(String telefono_one) {
		this.telefono_one = telefono_one.trim();
	}
	
	
	@Column(name="telefono_two",nullable=true)
	public String getTelefono_two() {
		return telefono_two;
	}
	
	
	
	public void setTelefono_two(String telefono_two) {
		this.telefono_two = ((telefono_two.trim() != null ) ? telefono_two : "");
	}
	
	
	@Column(name="email",nullable=true)
	public String getEmail() {
		return email;
	}
	
	
	
	public void setEmail(String email) {
		this.email = ((email.trim() != null ) ? email : "");
	}
	
	
	
	@Column(name="data_creazione",nullable=false)
	public Timestamp getDataCreazione() {
		return dataCreazione;
	}
	
	
	
	public void setDataCreazione(Timestamp dataCreazione) {
		this.dataCreazione = dataCreazione;
	}
   
	
	
    @Transient
	public List<Cliente> getCostumer() {
		return costumer;
	}


	public void setCostumer(List<Cliente> costumer) {
		this.costumer = costumer;
	}
	
	
	@Transient
	 public boolean equal(Cliente c) {
		return this.getId_user() == c.id_user && this.getCognome().equals(c.cognome) && this.getNome().equals(c.getNome()) &&
				  this.getTelefono_one().equals(c.telefono_one) && this.getTelefono_two().equals(c.getTelefono_two()) &&
				  this.getEmail().equals(c.getEmail());
	}
	
	
	
	
	
	
}
