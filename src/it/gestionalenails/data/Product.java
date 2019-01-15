package it.gestionalenails.data;

import java.beans.Transient;
import java.io.Serializable;
import java.text.SimpleDateFormat;
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
@Table(name="prodotto")
public class Product implements Serializable{
	private static final long serialVersionUID = -7435255927781193334L;
	private long id_user;
	private Long idProduct;
	private int quantity;
	private Calendar date;
	private String productName;
	private float price;

	public Product() {

	}
	

	public Product(long id_user,Calendar date,int quantity, String productName, float price ,Long idProduct) {
		this(id_user,date, quantity, productName, price);
		setIdProduct(idProduct);
	}

	public Product(long id_user,Calendar date,int quantity, String productName, float price) {
		setId_user(id_user);
		setDate(date);
		setQuantity(quantity);
		setProductName(productName);
		setPrice(price);
	}
	
	
	

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_prodotto",nullable=false)
	public Long getIdProduct() {
		return idProduct;
	}


    
	
	@Column(name="id_user",nullable= false)
	public long getId_user() {
		return id_user;
	}


	public void setId_user(long id_user) {
		this.id_user = id_user;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	@Column(name="quantita",nullable=false)
	public int getQuantity() {
		return quantity;
	}


	public void setIdProduct(Long idProduct) {
		this.idProduct = idProduct;
	}

	@Temporal(TemporalType.DATE)
	@Column(name="data_acquisto",nullable=false)
	public Calendar getDate() {
		return date;
	}



	public void setDate(Calendar date) {
		this.date = date;
	}


	@Column(name="nome_prodotto",nullable=false)
	public String getProductName() {
		return productName;
	}



	public void setProductName(String productName) {
		this.productName = productName.toUpperCase().trim();
	}


	@Column(name="prezzo", nullable=false)
	public float getPrice() {
		return price;
	}



	public void setPrice(float price) {
		this.price = price;
	}

    
	public boolean equals(Product prd) {
		SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
		String dt1 = fmt.format(this.getDate().getTime());
		String dt2 = fmt.format(prd.getDate().getTime());
		return this.getIdProduct() == prd.getIdProduct() && dt1.equals(dt2)
				&& this.getProductName().equals(prd.getProductName()) && this.getPrice() == prd.getPrice();
	}

}
