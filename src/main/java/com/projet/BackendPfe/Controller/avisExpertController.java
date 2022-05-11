package com.projet.BackendPfe.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projet.BackendPfe.Entity.AvisExpert;
import com.projet.BackendPfe.Entity.Expert;
import com.projet.BackendPfe.Entity.Generaliste;
import com.projet.BackendPfe.Entity.Patient;
import com.projet.BackendPfe.repository.AutoDetectionRepository;
import com.projet.BackendPfe.repository.AvisExpertRepository;
import com.projet.BackendPfe.repository.ExpertRepository;
import com.projet.BackendPfe.request.PatientRequest;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/avisExpert")
public class avisExpertController {
	@Autowired ExpertRepository exp;
	@Autowired AvisExpertRepository repository ;
	
	
	@PostMapping("/addAvis/{idExpert}")
	public AvisExpert Addavis(  @PathVariable("idExpert")  long idExpert){
		Expert  expert = exp.findById(idExpert).get(); 
		
		AvisExpert avis = new AvisExpert(expert);
	return repository.save(avis);
	// id resultat besh tkoun f 2 methodes l baaadha {idAvisExpert}
	}
	@PutMapping("/updateAvisD/{idAvisExpert}") // mch update juste update k yebda andi avis EXpert fergh
	public AvisExpert updateavisD(  @PathVariable("idAvisExpert")  long idAvisExpert ,@RequestBody AvisExpert avisExpert){
		  Optional<AvisExpert>  avisExp = repository.findById(idAvisExpert); 
		
		AvisExpert avis = avisExp.get();
avis.setCommentaireDroite(avisExpert.getCommentaireDroite());
avis.setGraviteDroite(avisExpert.getGraviteDroite());
avis.setMaladieDroite(avisExpert.getMaladieDroite());

	return repository.save(avis); // besh nrjaa iD khtr besh naaml put avis expert f wost autoDetection
	}
	
	@PutMapping("/updateAvisG/{idAvisExpert}") 
	public AvisExpert updateavisG(  @PathVariable("idAvisExpert")  long idAvisExpert ,@RequestBody AvisExpert avisExpert){
		  Optional<AvisExpert>  avisExp = repository.findById(idAvisExpert); 
		
		AvisExpert avis = avisExp.get();
avis.setCommentaireGauche(avisExpert.getCommentaireGauche());
avis.setGraviteGauche(avisExpert.getGraviteGauche());
avis.setMaladieGauche(avisExpert.getMaladieGauche());

	return repository.save(avis); //kif kif
	}
	
}
