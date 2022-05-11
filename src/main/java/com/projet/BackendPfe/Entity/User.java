package com.projet.BackendPfe.Entity;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
@Entity
@Table(name = "Utilisateur",
uniqueConstraints = { 
		@UniqueConstraint(columnNames = "username"
				+ ""),
		@UniqueConstraint(columnNames = "email") 
	})
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="roles")
public abstract class User {
	
	@Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	protected long id;
	  @NotBlank
		@Size(max = 100)
	  protected String username;
	  protected String nomPrenom ; 
	  @NotBlank
	  @Size(max = 100)
	  @Email
	  protected String email;
	  protected String password;
	  @Column(name = "image", length = 1000000)
	  protected byte[] image;
	  protected  LocalDate date_inscription  ; 
	  protected String role ; 
	
	public LocalDate getDate_inscription() {
		return date_inscription;
	}
	public void setDate_inscription(LocalDate date_inscription) {
		this.date_inscription = date_inscription;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public User(String username, String email,String nomPrenom ,  String password  , byte[] image  , LocalDate date_inscription  , String role) {
		this.username = username;
		this.email = email;
		this.password = password;
		this.image = image;
		this.date_inscription=date_inscription ; 
		this.role = role  ; 
		this.nomPrenom=nomPrenom ; 
	
	}
	
	
	public String getNomPrenom() {
		return nomPrenom;
	}
	public void setNomPrenom(String nomPrenom) {
		this.nomPrenom = nomPrenom;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", nomPrenom=" + nomPrenom + ", email=" + email
				+ ", password=" + password + ", image=" + Arrays.toString(image) + ", date_inscription="
				+ date_inscription + ", role=" + role + "]";
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	public User() {
		super();
		
	}

}