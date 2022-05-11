package com.projet.BackendPfe.Entity;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;



@Entity
public class Consultation {
protected LocalDate dateDemandeAvis ;  
protected LocalDateTime DemandeAvisTime;
protected String decalageHoraire;
protected int rating;
private String nomImage ; 

public String getNomImage() {
	return nomImage;
}

public void setNomImage(String nomImage) {
	this.nomImage = nomImage;
}

public Consultation(int rating) {
	super();
	this.rating = rating;
}

public int getRating() {
	return rating;
}

public void setRating(int rating) {
	this.rating = rating;
}

public String getDecalageHoraire() {
	return decalageHoraire;
}

public void setDecalageHoraire(String decalageHoraire) {
	this.decalageHoraire = decalageHoraire;
}

public LocalDateTime getDemandeAvisTime() {
	return DemandeAvisTime;
}

public void setDemandeAvisTime(LocalDateTime demandeAvisTime) {
	DemandeAvisTime = demandeAvisTime;
}

public LocalDate getDateDemandeAvis() {
		return dateDemandeAvis;
	}

	public void setDateDemandeAvis(LocalDate dateDemandeAvis) {
		this.dateDemandeAvis = dateDemandeAvis;
	}

/*public Consultation(LocalDate dateConsult, Generaliste generaliste, Patient patient, byte[] image1_Droite,
			
			byte[] image1_Gauche) {
		super();
		this.dateConsult = dateConsult;
		this.generaliste = generaliste;
		this.patient = patient;
		this.image1_Droite = image1_Droite;
		
		this.image1_Gauche = image1_Gauche;
		
	}*/
private int demandeAvisD;
 private int demandeAvisG;
 private long sizeConsultaion ; 
 
	public Consultation( int demandeAvisD,int demandeAvisG,LocalDate dateConsult, Generaliste generaliste, Patient patient,
			AutoDetection autoDetection, byte[] image1_Droite, byte[] image1_Gauche,LocalDateTime DemandeAvisTime,String DecalageHoraire , long sizeConsultation) {
		super();
		this.dateConsult = dateConsult;
		this.generaliste = generaliste;
		this.patient = patient;
		this.autoDetection = autoDetection;
		this.image1_Droite = image1_Droite;
		this.demandeAvisD=demandeAvisD;
		this.demandeAvisG=demandeAvisG;
		this.DemandeAvisTime=DemandeAvisTime;
		this.decalageHoraire=DecalageHoraire;
		this.sizeConsultaion =sizeConsultation ; 
	}

	public int getDemandeAvisD() {
		return demandeAvisD;
	}

	public void setDemandeAvisD(int demandeAvisD) {
		this.demandeAvisD = demandeAvisD;
	}

	public int getDemandeAvisG() {
		return demandeAvisG;
	}

	public void setDemandeAvisG(int demandeAvisG) {
		this.demandeAvisG = demandeAvisG;
	}

	public Generaliste getGeneraliste() {
		return generaliste;
	}

/*public Consultation(Generaliste generaliste, Patient patient) {
		super();
		this.generaliste = generaliste;
		this.patient = patient;
	}*/
private LocalDate dateConsult;
	//private  SimpleDateFormat dateFormat;
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	public Consultation(Generaliste generaliste, AutoDetection autoDetection) {
		super();
		this.generaliste = generaliste;
		this.autoDetection = autoDetection;
	}
	@ManyToOne()
	protected Generaliste generaliste;
	public AutoDetection getAutoDetection() {
		return autoDetection;
	}
	public void setAutoDetection(AutoDetection autoDetection) {
		this.autoDetection = autoDetection;
	}

	@ManyToOne()
	
	protected Patient patient;
	@OneToOne
	
	protected AutoDetection autoDetection;
	
	@Column(name = "image1Droite", length = 1000000)
	  protected byte[] image1_Droite;
	  @Column(name = "image2Droite", length = 1000000)
	  protected byte[] image2_Droite;
	@Column(name = "image3Droite", length = 1000000)
	  protected byte[] image3_Droite;
	@Column(name = "image4Droite", length = 1000000)
	  protected byte[] image4_Droite;
	@Column(name = "image5Droite", length = 1000000)
	  protected byte[] image5_Droite;
	@Column(name = "image1Gauche", length = 1000000)
	  protected byte[] image1_Gauche;
	@Column(name = "image2Gauche", length = 1000000)
	  protected byte[] image2_Gauche;
  @Column(name = "image3Gauche", length = 1000000)
		  protected byte[] image3_Gauche;
	@Column(name = "image4Gauche", length = 1000000)
		  protected byte[] image4_Gauche;
	@Column(name = "image5Gauche", length = 1000000)
		  protected byte[] image5_Gauche;
	 
	public byte[] getImage1_Droite() {
		return image1_Droite;
	}
	public void setImage1_Droite(byte[] image1_Droite) {
		this.image1_Droite = image1_Droite;
	}
	
	public byte[] getImage1_Gauche() {
		return image1_Gauche;
	}
	public void setImage1_Gauche(byte[] image1_Gauche) {
		this.image1_Gauche = image1_Gauche;
	}
	

	
public Consultation() {}


public Consultation(Generaliste generaliste,Patient patient,LocalDate dateConsult,
		byte[] image1_Gauche ,byte[] image1_Droite  ,byte[] image2_Gauche ,byte[] image2_Droite ,
		byte[] image3_Gauche ,byte[] image3_Droite ,byte[] image4_Gauche ,byte[] image4_Droite,
		byte[] image5_Gauche ,byte[] image5_Droite ,LocalDateTime DemandeAvisTime , String decalageHoraire  , long sizeConsultation) {
	super();
	this.generaliste = generaliste;
	this.patient = patient;

	this.image1_Gauche=image1_Gauche ; 
	
	this.image1_Droite=image1_Droite ; 
    this.image2_Gauche=image1_Gauche ; 
	
	this.image2_Droite=image1_Droite ; 
    this.image3_Gauche=image1_Gauche ; 
	
	this.image3_Droite=image1_Droite ; 
   this.image4_Gauche=image1_Gauche ; 
	
	this.image4_Droite=image1_Droite ; 
   this.image5_Gauche=image1_Gauche ; 
	
	this.image5_Droite=image1_Droite ; 
	this. dateConsult=dateConsult;
	this.DemandeAvisTime=DemandeAvisTime;
	this.decalageHoraire=decalageHoraire;
	this.sizeConsultaion =sizeConsultation ;
}



	public LocalDate getDateConsult() {
	return dateConsult;
}

public void setDateConsult(LocalDate dateConsult) {
	this.dateConsult = dateConsult;
}

	public void setGeneraliste(Generaliste generaliste) {
		this.generaliste = generaliste;
	}

	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public byte[] getImage2_Droite() {
		return image2_Droite;
	}

	public void setImage2_Droite(byte[] image2_Droite) {
		this.image2_Droite = image2_Droite;
	}

	public byte[] getImage3_Droite() {
		return image3_Droite;
	}

	public void setImage3_Droite(byte[] image3_Droite) {
		this.image3_Droite = image3_Droite;
	}

	public byte[] getImage4_Droite() {
		return image4_Droite;
	}

	public void setImage4_Droite(byte[] image4_Droite) {
		this.image4_Droite = image4_Droite;
	}

	public byte[] getImage5_Droite() {
		return image5_Droite;
	}

	public void setImage5_Droite(byte[] image5_Droite) {
		this.image5_Droite = image5_Droite;
	}

	public byte[] getImage2_Gauche() {
		return image2_Gauche;
	}

	public void setImage2_Gauche(byte[] image2_Gauche) {
		this.image2_Gauche = image2_Gauche;
	}

	public byte[] getImage3_Gauche() {
		return image3_Gauche;
	}

	public void setImage3_Gauche(byte[] image3_Gauche) {
		this.image3_Gauche = image3_Gauche;
	}

	public byte[] getImage4_Gauche() {
		return image4_Gauche;
	}

	public void setImage4_Gauche(byte[] image4_Gauche) {
		this.image4_Gauche = image4_Gauche;
	}

	public byte[] getImage5_Gauche() {
		return image5_Gauche;
	}

	public void setImage5_Gauche(byte[] image5_Gauche) {
		this.image5_Gauche = image5_Gauche;
	}

	public long getSizeConsultaion() {
		return sizeConsultaion;
	}

	public void setSizeConsultaion(long sizeConsultaion) {
		this.sizeConsultaion = sizeConsultaion;
	}

	
	
}