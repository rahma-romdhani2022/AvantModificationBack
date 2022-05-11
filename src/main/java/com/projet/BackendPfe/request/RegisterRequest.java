package com.projet.BackendPfe.request;


import java.time.LocalDate;

import javax.validation.constraints.*;
public class RegisterRequest {
	private String gender ;
	private String image ; 
	@NotBlank
    private String username;
    @NotBlank
   // @Size(min=0 , max = 100)
    @Email
    private String email;
    private LocalDate date_inscription ; 
    // @NotBlank
    // private String role;
     @NotBlank
    // @Size( max = 10)
     private String password;
     private String role ; 
    public void setDate_inscription(LocalDate date_inscription) {
		this.date_inscription = date_inscription;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
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
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getGender() {
		return gender;
	} 
	public String getImage() {
		return image;
	} 
 
	public void setGender(String gender) {
		this.gender = gender;
	}public void setImage(String image) {
		this.image = image;
	}
}
