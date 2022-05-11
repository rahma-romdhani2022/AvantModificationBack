package com.projet.BackendPfe.services;

import java.util.List;

import com.projet.BackendPfe.Entity.Patient;


public interface IGestionPatient {
	
	public void addPatient(Patient p);

	public List<Patient> getAllPatient();
	public List<Patient> getPatientPMC(String mc);
	public List<Patient> getPatient(int mc);
	

	public void delete(int id);
	public void ajouterPatient(Patient p);


}
