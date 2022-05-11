package com.projet.BackendPfe.request;

public class PatientRequest {
	private long cin ;
	
	private String username; 
	private String email;
	private long telephone;
	
	private String  gender;
	
	private String  dateNaiss;
	private String  antecedant;
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
	public long getTelephone() {
		return telephone;
	}
	public void setTelephone(long telephone) {
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
	public String getAntecedant() {
		return antecedant;
	}
	public void setAntecedant(String antecedant) {
		this.antecedant = antecedant;
	}
}
