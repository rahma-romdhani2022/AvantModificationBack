package com.projet.BackendPfe.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.projet.BackendPfe.Entity.Generaliste;
import com.projet.BackendPfe.Entity.Patient;


public interface PatientRepository  extends JpaRepository<Patient , Long>{
	public List<Patient> findByUsernameContains(String mc);
	
	public List<Patient> findByGeneraliste_id(@ModelAttribute("id") long id);
	public List<Patient> findByDateInscription (LocalDate date ) ; 
	List<Patient> findByGender(String gender);
}
