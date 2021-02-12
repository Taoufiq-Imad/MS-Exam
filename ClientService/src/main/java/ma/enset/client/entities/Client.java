package ma.enset.client.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Client {
	@Id @GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long code; 
	private String nom; 
	private String mail;
	
	public Client() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Client(Long code, String nom, String mail) {
		super();
		this.code = code;
		this.nom = nom;
		this.mail = mail;
	}

	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	} 
	

}
