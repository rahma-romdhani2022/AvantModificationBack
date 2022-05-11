package com.projet.BackendPfe.Entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;



@Entity


public class Patient {

	public Patient(long cin, @NotBlank @Size(max = 100) String username, @NotBlank @Size(max = 100) @Email String email,
			long telephone, String gender, String dateNaiss, String antecedant,
			Generaliste generaliste) {
		super();
		this.cin = cin;
		this.username = username;
		this.email = email;
		this.telephone = telephone;
		this.gender = gender;
		this.dateNaiss = dateNaiss;
		this.antecedant = antecedant;
	
		this.generaliste = generaliste;
	}
	public void setTelephone(long telephone) {
		this.telephone = telephone;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected long id;
	  @NotBlank
		@Size(max = 100)
	  protected String username;
	  @NotBlank
	  @Size(max = 100)
	  @Email
	  protected long cin;
	  protected String email;
	  protected long telephone;
	  protected String gender;
	  protected String dateNaiss;
      protected LocalDate dateInscription  ; 
	  protected String antecedant ;
	  
	  
	  @OneToMany(targetEntity=Consultation.class, mappedBy = "patient",cascade=CascadeType.ALL)
		private List<Consultation>liste1=new ArrayList<Consultation>();
	  
	 
	public Generaliste getGeneraliste() {
		return generaliste;
	}
	public void setGeneraliste(Generaliste generaliste) {
		this.generaliste = generaliste;
	}
	@ManyToOne()
		protected Generaliste generaliste;
	public Patient(@NotBlank @Size(max = 100) String username, @NotBlank @Size(max = 100) @Email String email,
			long telephone, String gender, String dateNaiss, String antecedant, Generaliste generaliste , LocalDate dateInscription) {
		super();
		this.username = username;
		this.email = email;
		this.telephone = telephone;
		this.gender = gender;
		this.dateNaiss = dateNaiss;
		this.antecedant = antecedant;
		this.generaliste = generaliste;
		this.dateInscription = dateInscription ; 
	}
	public Patient(long cin, @NotBlank @Size(max = 100) String username, @NotBlank @Size(max = 100) @Email String email,
			Long telephone, String gender, String dateNaiss,String antecedant,Generaliste generaliste , LocalDate dateInscription) {
		super();
		this.cin = cin;
		this.username = username;
		this.email = email;
		this.telephone = telephone;
		this.gender = gender;
		this.dateNaiss = dateNaiss;
		this.antecedant = antecedant;
		this.generaliste=generaliste;
		this.dateInscription = dateInscription ;
	}
	public long getCin() {
		return cin;
	}
	public void setCin(long cin) {
		this.cin = cin;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Patient() {
		super();
	}
	public Long getTelephone() {
		return telephone;
	}
	public void setTelephone(Long telephone) {
		this.telephone = telephone;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getDateNaiss() {
		return dateNaiss;
	}
	public void setDateNaiss(String dateNaiss) {
		this.dateNaiss = dateNaiss;
	}
	public Patient(long cin, @NotBlank @Size(max = 100) String username, @NotBlank @Size(max = 100) @Email String email,
			Long telephone, String gender,String antecedant, String dateNaiss) {
		super();
		this.cin = cin;
		this.username = username;
		this.email = email;
		this.telephone = telephone;
		this.gender = gender;
		this.dateNaiss = dateNaiss;
		this.antecedant = antecedant;

	}
	public  String getAntecedant() {
		return antecedant;
	}
	public void setAntecedant(String antecedant) {
		this.antecedant = antecedant;
	}
	public LocalDate getDateInscription() {
		return dateInscription;
	}
	public void setDateInscription(LocalDate dateInscription) {
		this.dateInscription = dateInscription;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
/*	public List<Consultation> getListe1() {
		return liste1;
	}
	public void setListe1(List<Consultation> liste1) {
		this.liste1 = liste1;
	}*/
	  
}

	
