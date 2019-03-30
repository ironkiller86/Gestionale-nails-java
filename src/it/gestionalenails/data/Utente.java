package it.gestionalenails.data;



import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name="utente")
public class Utente implements Serializable {
    
	private static final long serialVersionUID = -6262147715134625429L;
	private long id;
	private Calendar dataCreazione;
    private String cognome;
    private String nome;
    private String codFiscale;
    private Calendar dataDiNascita;
    private String cittaDiNascita;
    private String indirizzo;
    private String cittaResidenza;
    private String cap;
    private String telefono;
    private String email;
    private String username;
    private String password;
    private Calendar dataAttiv;
    private Calendar dataScad;
    private boolean role;
    

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id_user")
	public long getId() {
		return id;
	}
	
	
	
	public void setId(long id) {
		this.id = id;
	}
	
	
	@Temporal(TemporalType.DATE)
	@Column(name="data_creazione",nullable=false)
	public Calendar getDataCreazione() {
		return dataCreazione;
	}



	public void setDataCreazione(Calendar dataCreazione) {
		this.dataCreazione = dataCreazione;
	}



	@Column(name="username",nullable=false)
	public String getUsername() {
		return username;
	}
	
	
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	
	
	@Column(name="password",nullable=false)
	public String getPassword() {
		return password;
	}
	
	
	public void setPassword(String password) {
		this.password = password;
	}
    
	
	@Column(name="cognome",nullable=false)
    public String getCognome() {
		return cognome;
	}



	public void setCognome(String cognome) {
		this.cognome = cognome;
	}


	@Column(name="nome",nullable=false)
	public String getNome() {
		return nome;
	}



	public void setNome(String nome) {
		this.nome = nome;
	}


	@Column(name="codice_fiscale",nullable=false)
	public String getCodFiscale() {
		return codFiscale;
	}



	public void setCodFiscale(String codFiscale) {
		this.codFiscale = codFiscale;
	}

	@Temporal(TemporalType.DATE)
	@Column(name="data_di_nascita",nullable=false)
	public Calendar getDataDiNascita() {
		return dataDiNascita;
	}



	public void setDataDiNascita(Calendar dataDiNascita) {
		this.dataDiNascita = dataDiNascita;
	}

	
	@Column(name="citta_nascita",nullable=false)
	public String getCittaDiNascita() {
		return cittaDiNascita;
	}



	public void setCittaDiNascita(String cittaDiNascita) {
		this.cittaDiNascita = cittaDiNascita;
	}


	@Column(name="indirizzo",nullable=false)
	public String getIndirizzo() {
		return indirizzo;
	}



	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}


	@Column(name="citta_residenza",nullable=false)
	public String getCittaResidenza() {
		return cittaResidenza;
	}



	public void setCittaResidenza(String cittaResidenza) {
		this.cittaResidenza = cittaResidenza;
	}


	@Column(name="cap",nullable=false)
	public String getCap() {
		return cap;
	}



	public void setCap(String cap) {
		this.cap = cap;
	}


	@Column(name="telefono",nullable=false)
	public String getTelefono() {
		return telefono;
	}



	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}


	@Column(name="email",nullable=true)
	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}

	@Temporal(TemporalType.DATE)
	@Column(name="data_attiv",nullable=false)
	public Calendar getDataAttiv() {
		return dataAttiv;
	}



	public void setDataAttiv(Calendar dataAttiv) {
		this.dataAttiv = dataAttiv;
	}

	@Temporal(TemporalType.DATE)
	@Column(name="data_scad",nullable=false)
	public Calendar getDataScad() {
		return dataScad;
	}



	public void setDataScad(Calendar dataScad) {
		this.dataScad = dataScad;
	}



	
	
	
	@Column(name="role")
	public boolean isRole() {
		return role;
	}


	
	public void setRole(boolean role) {
		this.role = role;
	}

}
