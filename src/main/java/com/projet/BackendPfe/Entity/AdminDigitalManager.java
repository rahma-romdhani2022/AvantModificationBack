package com.projet.BackendPfe.Entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@DiscriminatorValue(value="AdminDigitalManager")
public class AdminDigitalManager extends User {
	@OneToMany(mappedBy = "adminD" , cascade = CascadeType.ALL)
	@JsonProperty(access =Access.WRITE_ONLY)
	private List<IAModel>liste_IaModel=new ArrayList<IAModel>();

	public AdminDigitalManager(String username, String email,String nomPrenom, String password  , byte[] image  , LocalDate date_inscription , String role) {
		super( username,  email,nomPrenom,  password ,  image , date_inscription , role);
	}
	public AdminDigitalManager(byte[] image) {
		this.image = image ;
	}
	public AdminDigitalManager() {
		super();
	}
	public LocalDate getDate_inscription() {
		return date_inscription;
	}

	public String getUsername(){
		return super.getUsername();
	}
	

	public void setUsername(){
		 super.setUsername(super.getUsername());
		 
	}
	public String getEmail(){
		return super.getEmail();
	}
	

	public void setEmail(){
		 super.setEmail(super.getEmail());
	}
	
	
	public String getPassword(){
		return super.getPassword();
	}
	

	public void setPassword(){
		 super.setPassword(super.getPassword());
	}
	
	
	

	public void setImage(){
		 super.setImage(super.getImage());
	}
	public byte[] getImage(){
		return super.getImage();
	}
	public long getId(){
		return super.getId();
	}

}