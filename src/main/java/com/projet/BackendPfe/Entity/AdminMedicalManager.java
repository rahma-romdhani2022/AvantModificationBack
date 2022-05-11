package com.projet.BackendPfe.Entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@DiscriminatorValue(value="AdminMedicalManager")

public class AdminMedicalManager extends User {
	private String diplome ; 

	@OneToMany(mappedBy = "admin" , cascade = CascadeType.ALL)
	@JsonProperty(access =Access.WRITE_ONLY)
	private List<Expert>liste_experts=new ArrayList<Expert>();

	public String getDiplome() {
		return diplome;
	}
	public void setDiplome(String diplome) {
		this.diplome = diplome;
	}
	public List<Expert> getListe_experts() {
		return liste_experts;
	}
	public void setListe_experts(List<Expert> liste_experts) {
		this.liste_experts = liste_experts;
	}
	public AdminMedicalManager (String username, String email , String nomPrenom , String password  , byte[] image  , 
			LocalDate date_inscription , String role , String diplome) {
		super( username,  email,nomPrenom,  password ,  image , date_inscription , role);
		
		this.username = username; 
		this. email=email ; 
		this.password =password ; 
		this.image =image; 
		this.date_inscription = date_inscription; 
		this. role=role ; 
		this. diplome= diplome; 
	}
		public AdminMedicalManager() {}
		
		public AdminMedicalManager(byte[] image) {
			this.image = image ;
		}
		public LocalDate getDate_inscription() {
			return date_inscription;
		}
	
		public String getUsername(){
			return super.getUsername();}
		
		public void setUsername(){
			 super.setUsername(super.getUsername());	}	 
		
		public String getEmail(){
			return super.getEmail();}
		
		public void setEmail(){
			 super.setEmail(super.getEmail());}
		
		public String getPassword(){
			return super.getPassword();}
		
		public void setPassword(){
			 super.setPassword(super.getPassword());}
		
		public void setImage(){
			 super.setImage(super.getImage());}
		
		public byte[] getImage(){
			return super.getImage();}
		public long  getId(){
			return id;}
}
