package com.projet.BackendPfe.Controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.projet.BackendPfe.Entity.AutoDetection;
import com.projet.BackendPfe.Entity.AvisExpert;
import com.projet.BackendPfe.Entity.Consultation;
import com.projet.BackendPfe.Entity.Expert;
import com.projet.BackendPfe.Entity.Generaliste;
import com.projet.BackendPfe.Entity.Patient;
import com.projet.BackendPfe.repository.AutoDetectionRepository;
import com.projet.BackendPfe.repository.AvisExpertRepository;
import com.projet.BackendPfe.repository.ConsultationRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/Auto")
public class AutoDetectionController {
	@Autowired AutoDetectionRepository repository ;
	@Autowired ConsultationRepository pr;
    @Autowired AvisExpertRepository avis; 
	@PostMapping("/auto/{idGeneraliste}/{idConsultation}") // lorsque généraliste clique sur AI MODEL autoDetection va etre créer (2)
	public AutoDetection addConsultation(@PathVariable("idGeneraliste") long idGeneraliste , 
			                                                                 @PathVariable("idConsultation") long idConsultation  )
	{
		Consultation consultation = pr.findById(idConsultation).get(); 
	
	String maladieGauche=null;
	String maladieDroite=null;
	int graviteDroite=0;
	int graviteGauche=0;
		
		

		AutoDetection autoDetection = new AutoDetection(maladieDroite,maladieGauche,graviteDroite,graviteGauche , LocalDate.now());
		repository.save(autoDetection) ;
		return autoDetection;


	}
	
	@PutMapping("ajouterReponseAvis/{idAutoDetection}/{idConsultation}/{idAvisExpert}") //updateIDAvis expert f classe mtaa autodetection wa9teh cad f entite autodetection avisExpert maadsh ?
	//wa9teli yjeweb expert al demande avis  ;) ekher etape
	public void updateIdAvisExpert(@PathVariable("idAutoDetection") long idAutoDetection, @PathVariable("idConsultation") long idConsultation ,@PathVariable ("idAvisExpert") long idAvisExpert){
		 Consultation consult =pr.findById(idConsultation).get();
	AutoDetection autoDetection = repository.findById(idAutoDetection).get(); 
	AvisExpert avisExpert=avis.findById(idAvisExpert).get();
		 autoDetection.setAvisExpert(avisExpert);
		 repository.save(autoDetection);

	}

/*** Statistiques ********/
	@GetMapping("/autoToDay")
	public int nbrAutoDetectionToDay() {
		List<AutoDetection> liste = repository.findAll() ; 
		List<AutoDetection> res = new ArrayList<AutoDetection>() ; 

		for(AutoDetection  auto  : liste) {
			if(auto.getDate_autodetection().equals(LocalDate.now())) {
				res.add(auto) ; 
			}
		}
		return res.size() ; 
	}
	
/**** statiques ****/
	@GetMapping("/getAIEgaleAvis")
	public int getAvisEgaleAI (@RequestParam("month") Integer month){
	 List<Consultation> liste =  pr.findAll() ; 
	 
	 List<Consultation> liste1 = new ArrayList<Consultation>();// Empty


		for(Consultation consult :liste) {
			Integer monthInscription = new Integer(consult.getDateConsult().getMonthValue());
			Integer yearInscription = new Integer(consult.getDateConsult().getYear());

			if(consult.getAutoDetection()!= null) {
			if(consult.getAutoDetection().getAvisExpert()!= null) {
				if(consult.getAutoDetection().getMaladieDroite()!= null ||consult.getAutoDetection().getGraviteDroite()!= 0) {

			if((consult.getAutoDetection().getAvisExpert().getMaladieDroite().equals(consult.getAutoDetection().getMaladieDroite()))&&(consult.getAutoDetection().getAvisExpert().getGraviteDroite()==consult.getAutoDetection().getGraviteDroite())) {
				//liste1.add(consult)  ; 
				 if(month.equals(monthInscription)&& (yearInscription.equals(LocalDate.now().getYear()))) {
					 liste1.add(consult) ; 
				 }
			
			if((consult.getAutoDetection().getAvisExpert().getMaladieGauche().equals(consult.getAutoDetection().getMaladieGauche())&&(consult.getAutoDetection().getAvisExpert().getGraviteGauche()==consult.getAutoDetection().getGraviteGauche()))) {
			if(!(liste1.contains(consult))){
				if(month.equals(monthInscription)&& (yearInscription.equals(LocalDate.now().getYear()))){
					 liste1.add(consult) ; 
				 } 
			}
			}
				}
			}
		
			}	
	}}

		int nbr =liste1.size();

		return nbr ; 
	}
	@GetMapping("/getAIdiffAvis")

	public int getAvisDiffAI (@RequestParam("month") Integer month){
	 List<Consultation> liste =  pr.findAll() ; 
	 
	 List<Consultation> liste1 = new ArrayList<Consultation>();// Empty
		
		for(Consultation consult :liste) {
			Integer monthInscription = new Integer(consult.getDateConsult().getMonthValue());
			Integer yearInscription = new Integer(consult.getDateConsult().getYear());
			if(consult.getAutoDetection()!= null) {
			if(consult.getAutoDetection().getAvisExpert()!= null) {
			if(consult.getAutoDetection().getMaladieDroite()!= null ||consult.getAutoDetection().getGraviteDroite()!= 0) {
			if((consult.getAutoDetection().getAvisExpert().getMaladieDroite()!=consult.getAutoDetection().getMaladieDroite())&&(consult.getAutoDetection().getAvisExpert().getGraviteDroite()!=consult.getAutoDetection().getGraviteDroite())) {
				if(month.equals(monthInscription) && (yearInscription.equals(LocalDate.now().getYear()))) {
					 liste1.add(consult) ; 
				 
			}
			if((consult.getAutoDetection().getAvisExpert().getMaladieGauche()!=consult.getAutoDetection().getMaladieGauche())&&(consult.getAutoDetection().getAvisExpert().getGraviteGauche()!=consult.getAutoDetection().getGraviteGauche())) {
			if(!(liste1.contains(consult))){
				if(month.equals(monthInscription)&& (yearInscription.equals(LocalDate.now().getYear()))) {
					 liste1.add(consult) ; 
				 } 
			}
			}
			
			}
		
			}	
			}}
	}
		int nbr =liste1.size();

		return nbr ; 
	}

}
	
	

