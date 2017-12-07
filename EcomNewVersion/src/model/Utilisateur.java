package model;

 
import java.io.Serializable;
import java.lang.String;
import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Utilisateur
 *
 */
@Entity
@Table(name="Utilisateur")
public class Utilisateur implements Serializable {

	   
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	@Column(name="IDUS")
	private int idus;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="FNAME")
	private String fname;
	
	@Column(name="EMAIL")
	private String email;
	
	@Column(name="PHONE")
	private int phone;
	
	@Column(name="SUSCRIBDATE")
	private Date subscribdate;
	
	@Column(name="STATE")
	private int state;
	
	@Column(name="PASSWORD")
	private String password;
	

	public Utilisateur(String nom, String prenom, String email, int phone,Date date,String password){
		this.name = nom;
		this.fname = prenom;
		this.email = email;
		this.phone = phone;
		this.subscribdate = date;
		this.state = 1;
		this.password = password;
	}
	
	public Utilisateur() {
		super();
	}
	
	public int getIdus() {
		return this.idus;
	}

	public void setIdus(int idus) {
		this.idus = idus;
	}
	
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getFname() {
		return this.fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}
	
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public int getPhone() {
		return this.phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}
	
	public Date getSubscribdate() {
		return this.subscribdate;
	}

	public void setSavedate(Date savedate) {
		this.subscribdate = savedate;
	}
	
	public int getState() {
		return this.state;
	}

	public void setState(int state) {
		this.state = state;
	}
	
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
   
}
