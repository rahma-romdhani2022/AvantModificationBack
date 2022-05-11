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
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class AutoDetection {
	public AutoDetection(String maladieDroite, String maladieGauche, int graviteDroite, int graviteGauche , LocalDate date_autodetection) {
		
		super();
		this.maladieDroite = maladieDroite;
		this.maladieGauche = maladieGauche;
		this.graviteDroite = graviteDroite;
		this.graviteGauche = graviteGauche;
		this.date_autodetection = date_autodetection ; 
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String maladieDroite;
	private String maladieGauche;
	private int graviteDroite;
	private  LocalDate date_autodetection ;
	
	
	public LocalDate getDate_autodetection() {
		return date_autodetection;
	}
	public void setDate_autodetection(LocalDate date_autodetection) {
		this.date_autodetection = date_autodetection;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public AutoDetection( String maladieDroite, String maladieGauche, int graviteDroite, int graviteGauche,AvisExpert avisExpert) {
		super();
	
		this.maladieDroite = maladieDroite;
		this.maladieGauche = maladieGauche;
		this.graviteDroite = graviteDroite;
		this.graviteGauche = graviteGauche;
		this.avisExpert=avisExpert;
		
	}
	public AutoDetection() {
		super();
	}
	private int graviteGauche;
	public String getMaladieDroite() {
		return maladieDroite;
	}
	 @OneToOne(cascade = CascadeType.ALL)
	    @JoinColumn(name = "avisExpert_id", referencedColumnName = "id")
	private AvisExpert avisExpert; 
	 /* @OneToMany(targetEntity=Consultation.class, mappedBy = "autoDetection",fetch=FetchType.LAZY)
			private List<Consultation>consultations=new ArrayList<Consultation>();*/
	 @OneToOne(mappedBy = "autoDetection" , cascade = CascadeType.ALL)
private Consultation consultation ;
	public AvisExpert getAvisExpert() {
		return avisExpert;
	}
	public void setAvisExpert(AvisExpert avisExpert) {
		this.avisExpert = avisExpert;
	}
	/*public List<Consultation> getConsultations() {
		return consultations;
	}
	public void setConsultations(List<Consultation> consultations) {
		this.consultations = consultations;
	}*/
	public void setMaladieDroite(String maladieDroite) {
		this.maladieDroite = maladieDroite;
	}
	public String getMaladieGauche() {
		return maladieGauche;
	}
	public void setMaladieGauche(String maladieGauche) {
		this.maladieGauche = maladieGauche;
	}
	public int getGraviteDroite() {
		return graviteDroite;
	}
	public void setGraviteDroite(int graviteDroite) {
		this.graviteDroite = graviteDroite;
	}
	public int getGraviteGauche() {
		return graviteGauche;
	}
	public void setGraviteGauche(int graviteGauche) {
		this.graviteGauche = graviteGauche;
	}
}
