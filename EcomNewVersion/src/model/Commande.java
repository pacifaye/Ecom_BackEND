package model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.json.simple.JSONObject;


@Entity
@Table(name="Commande") 
public class Commande {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	@Column(name="IDCM")
	private int idcm;
	
	@Column(name="IDUS")
	private int idus;
	
	@Column(name="IDPR")
	private int idpr;
	
	@Column(name="DATE")
	private Date date;
	
	@Column(name="PRICE")
	private int price;
	
	@Column(name="STATE")
	private int state;
	
	
	public Commande() {}
	
	public Commande(int idus, int idpr, Date date, int price, int state) {
		super();
		this.idus = idus;
		this.idpr = idpr;
		this.date = date;
		this.price = price;
		this.state = state;
	}

	/**
	 * @return the idcm
	 */
	public int getIdcm() {
		return idcm;
	}

	/**
	 * @param idcm the idcm to set
	 */
	public void setIdcm(int idcm) {
		this.idcm = idcm;
	}

	/**
	 * @return the idus
	 */
	public int getIdus() {
		return idus;
	}

	/**
	 * @param idus the idus to set
	 */
	public void setIdus(int idus) {
		this.idus = idus;
	}

	/**
	 * @return the idpr
	 */
	public int getIdpr() {
		return idpr;
	}

	/**
	 * @param idpr the idpr to set
	 */
	public void setIdpr(int idpr) {
		this.idpr = idpr;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return the price
	 */
	public int getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(int price) {
		this.price = price;
	}

	/**
	 * @return the state
	 */
	public int getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(int state) {
		this.state = state;
	}

	public String toJson() {
		
		JSONObject obj = new JSONObject();
		obj.put("idus", this.getIdus());
		obj.put("idpr", this.getIdpr());
		obj.put("idcm", this.getIdcm());
		obj.put("Date", this.getDate());
		obj.put("price", this.getPrice());
		obj.put("state", this.getState());
		  
		return obj.toJSONString();
	}
	
	
	
}






