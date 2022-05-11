package com.projet.BackendPfe.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.projet.BackendPfe.Entity.Consultation;
import com.projet.BackendPfe.Entity.Patient;


@Repository
public interface ConsultationRepository extends JpaRepository<Consultation,Long> {
	public List<Consultation> findByGeneraliste_id(@ModelAttribute("id") long id);
    public List<Consultation> findByPatient_cinAndGeneraliste_id(long id, long cin);
    public Consultation findByIdAndGeneraliste_id(long id, long id1);
    public List<Consultation> findByDemandeAvisD(int num) ; 
    public List<Consultation> findByDemandeAvisG(int num) ; 
    public List<Consultation> findByDateDemandeAvis(LocalDate date) ; 

}