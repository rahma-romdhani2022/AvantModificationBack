package com.projet.BackendPfe.request;

import java.time.LocalDate;
import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class RegisterRequestAdmin {
	

	private byte[] image ; 
    private String username;
    private String nomPrenom;
   // @Size(min=0 , max = 100)
    @Email
    private String email;
    private String role;
    @NotBlank
   // @Size( max = 10)
    private String password;
    private Date date_inscription ;
    private String diplome ; 
    
	public String getDiplome() {
		return diplome;
	}
	public void setDiplome(String diplome) {
		this.diplome = diplome;
	}
	public void setDate_inscription(Date date_inscription) {
		this.date_inscription = date_inscription;
	}
	public Date getDate_inscription() {
		return date_inscription;
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
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public byte[] getImage() {
		return image;
	} 
	
	public void setImage(byte[] image) {
		this.image = image;
	}
	public String getNomPrenom() {
		return nomPrenom;
	}
	public void setNomPrenom(String nomPrenom) {
		this.nomPrenom = nomPrenom;
	}
	
}




