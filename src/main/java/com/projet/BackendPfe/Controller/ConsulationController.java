package com.projet.BackendPfe.Controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.hibernate.validator.constraints.ParameterScriptAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.projet.BackendPfe.Entity.AutoDetection;
import com.projet.BackendPfe.Entity.AvisExpert;
import com.projet.BackendPfe.Entity.Consultation;
import com.projet.BackendPfe.Entity.Expert;
import com.projet.BackendPfe.Entity.Generaliste;
import com.projet.BackendPfe.Entity.Patient;
import com.projet.BackendPfe.repository.AutoDetectionRepository;
import com.projet.BackendPfe.repository.AvisExpertRepository;
import com.projet.BackendPfe.repository.ConsultationRepository;
import com.projet.BackendPfe.repository.ExpertRepository;
import com.projet.BackendPfe.repository.GeneralisteRepository;
import com.projet.BackendPfe.repository.PatientRepository;
import com.projet.BackendPfe.services.ConsultationService;
import com.projet.BackendPfe.services.PatientService;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/consultation")
public class ConsulationController {
	@Autowired ConsultationRepository repository ;
	@Autowired GeneralisteRepository medecinRepository;
	@Autowired PatientRepository patientRepository;
	@Autowired ExpertRepository expertRepository;
	@Autowired ConsultationService service ; 
	@Autowired AutoDetectionRepository pr ; 
    @Autowired AvisExpertRepository avis; 
    @Autowired PatientService  patientService; 
    
    
    
    @GetMapping(path="image/{id}" , produces= MediaType.IMAGE_JPEG_VALUE)
    public  byte[] getImage(@PathVariable("id") long id) throws Exception{
    	return service.getImage(id);
    }
    
    
    
    
    
    
    
    
    
    
    @GetMapping("/DemandeAvisEnvoyes")
    public int getAllDemnadesAujourdhui() {
    	
    	List<Consultation> liste = repository.findByDateDemandeAvis(LocalDate.now());
    	int total = liste.size() ; 
    	return total ; } 
   
    @GetMapping("historiques/{idExpert}")
    public List<Consultation> getHistoriquesExpertX(@PathVariable("idExpert") long idExpert){
    	Expert expert = expertRepository.findById(idExpert).get();
    	List<Consultation> liste = repository.findAll();
    	List<Consultation> resultat= new ArrayList<>() ; 
    	for(Consultation consult :liste) {
    		if((consult.getAutoDetection().getAvisExpert().getExpert().getId())==(expert.getId())) {
    			resultat.add(consult);
  
    		}
    	}
    	return resultat ; 
    }
    
    @GetMapping("/historiquesss")
    public List<Consultation> getHistoriquesExpertXcc(){
    	List<Consultation> liste = repository.findAll();
    	List<Consultation> resultat= new ArrayList<>() ; 	
    	return liste ; 
    }
    
    // les 2 attributes hrthom tabin generaliste 
    @PutMapping("/demandeAvisD/{idConsultation}")
public Consultation updateVariableDemandeAvisDroite(@PathVariable("idConsultation") long idConsultation )
{
    Consultation consult = repository.findById(idConsultation).get()	;
    consult.setDemandeAvisD(1);
    return consult ; 
}
    @PutMapping("/demandeAvisG/{idConsultation}")
    public Consultation updateVariableDemandeAvisGauche(@PathVariable("idConsultation") long idConsultation )
    {
        Consultation consult = repository.findById(idConsultation).get()	;
        consult.setDemandeAvisG(1);
        return consult ; 
    }
    @GetMapping("/{idConsultation}")
    public Consultation getConsultByID(@PathVariable("idConsultation") long idConsultation ) {
    	Consultation conster = repository.findById(idConsultation).get();
 if( conster.getImage1_Droite()== null) {
	  return conster;
  }
  else {
conster.setImage1_Droite(service.decompressZLib(conster.getImage1_Droite()));}	

if( conster.getImage2_Droite()== null) {
	  return conster;
  }
  else {
conster.setImage2_Droite(service.decompressZLib(conster.getImage2_Droite()));	}

if( conster.getImage3_Droite()== null) {
	  return conster;
  }
  else {
	    conster.setImage3_Droite(service.decompressZLib(conster.getImage3_Droite()));	}

	    if( conster.getImage4_Droite()== null) {
			  return conster;
		  }
  else {
	    conster.setImage4_Droite(service.decompressZLib(conster.getImage4_Droite()));	}
	    
	    if( conster.getImage5_Droite()== null) {
			  return conster;
		  }
  else {
	    conster.setImage5_Droite(service.decompressZLib(conster.getImage5_Droite()));}
								    
if( conster.getImage1_Gauche()== null) {
	  return conster;
  }
  else {
conster.setImage1_Gauche(service.decompressZLib(conster.getImage1_Gauche()));}

if( conster.getImage2_Gauche()== null) {
	  return conster;
  }
  else {
conster.setImage2_Gauche(service.decompressZLib(conster.getImage2_Gauche()));}

if( conster.getImage3_Gauche()== null) {
	  return conster;
  }
  else {
conster.setImage3_Gauche(service.decompressZLib(conster.getImage3_Gauche()));	}

if( conster.getImage4_Gauche()== null) {
	  return conster;
  }
  else {
conster.setImage4_Gauche(service.decompressZLib(conster.getImage4_Gauche()));	}

if( conster.getImage5_Gauche()== null) {
	  return conster;
  }
  else {
	    conster.setImage5_Gauche(service.decompressZLib(conster.getImage5_Gauche()));	}
																
return conster; }
		  
		
   
    @GetMapping("/Consultations") // hdhy st79itha f expert avis => besh yjiw kol ama f html mtaa angular besh naamlu condiition ala id AvisExpert f autotDetection eli huwa meloul besh ykoun null khater ma7tinch avis 
 	public List<Consultation> getAllConsultation(){
         //pr.findById(id);
	    return  repository.findAll();

	} 
    //add consultation
	@PostMapping("/Consultations/{idGeneraliste}/{idPatient}")
	public Consultation addConsultation(@PathVariable("idGeneraliste") long idGeneraliste , 
			                                                                 @PathVariable("idPatient") long idPatient   ){
		Generaliste  generaliste = medecinRepository.findById(idGeneraliste).get(); 
		Patient patient = patientRepository.findById(idPatient).get() ; 
		byte[] image1 = null ; 
		byte[] image2 = null ; 
		byte[] image3 = null ; 
		byte[] image4 = null ; 
		byte[] image5 = null ; 
		byte[] image6 = null ; 
		byte[] image7 = null ; 
		byte[] image8 = null ; 
		byte[] image9 = null ; 
		byte[] image10 = null ; 
		
		
		

		Consultation consultation = new Consultation(generaliste, patient,LocalDate.now(),image1,image2,image3,image4,image5,image6,image7,image8,image9,image10 ,null ,null ,0);
		repository.save(consultation) ;
		return consultation ; 

	}
	
	

	
	
	
	
	//deleteAll Pictures 
	@PutMapping("consultation/picturesD/{id}/{idConsultation}")
	public void deleteConsult(@PathVariable("id") long id, @PathVariable("idConsultation") long idConsultation){
		 Consultation consult =repository.findById(idConsultation).get();
		 consult.setImage1_Droite(null);
		 consult.setImage2_Droite(null);
		 consult.setImage3_Droite(null);
		 consult.setImage4_Droite(null);
		 consult.setImage5_Droite(null);
		 consult.setSizeConsultaion(0);
			consult.setSizeConsultaion(0);
		 repository.save(consult);

	}
	@PutMapping("consultation/picturesG/{id}/{idConsultation}")
	public void deleteConsultG(@PathVariable("id") long id, @PathVariable("idConsultation") long idConsultation){
		 Consultation consult =repository.findById(idConsultation).get();
		 consult.setSizeConsultaion(0);
		 consult.setImage1_Gauche(null);
		 consult.setImage2_Gauche(null);
		 consult.setImage3_Gauche(null);
		 consult.setImage4_Gauche(null);
		 consult.setImage5_Gauche(null);
		
		 repository.save(consult);

	}

	// put for expert baed f avis demander 
	
	/*@PutMapping("SendConsultation/{idConsultation}/{idExpert}")
	public Consultation EnvoyerConultationAunExpert(@PathVariable("idConsultation") long idConsultation , 
			                                                                                               @PathVariable("idExpert") long idExpert) {
		Consultation consultation = repository.findById(idConsultation).get() ;
		Expert expert = expertRepository.findById(idExpert).get() ;
	   consultation.setExpert(expert);
	   repository.save(consultation) ;
	   return consultation ; 
	}/*
	/***********Oeil Droite *************/
	
	@PutMapping("/addimage1D/{idConsultation}")
	public String updateImage1D(@PathVariable("idConsultation") long idConsultation  , @RequestParam("image1") MultipartFile image1) throws IOException {
		service.updateImage1Droite(idConsultation , image1);
		return "Done pour image1 Droite !!!!" ; 
	}

	@PutMapping("/addimage2D/{idConsultation}")
	public String updateImage2D(@PathVariable("idConsultation") long idConsultation  , @RequestParam("image2") MultipartFile image2 ) throws IOException {
			service.updateImage2Droite(idConsultation,  image2);
		return "Done pour image2  Droite!!!!" ; 
	}
	@PutMapping("/addimage3D/{idConsultation}")
	public String updateImage3D(@PathVariable("idConsultation") long idConsultation  , @RequestParam("image3") MultipartFile image3) throws IOException {
		service.updateImage3Droite(idConsultation , image3);
		return "Done pour image3 Droite !!!!" ; 
	}
	@PutMapping("/addimage4D/{idConsultation}")
	public String updateImage4D(@PathVariable("idConsultation") long idConsultation  , @RequestParam("image4") MultipartFile image4 ) throws IOException {
		service.updateImage4Droite(idConsultation , image4);
		return "Done pour image4  Droite!!!!" ; 
	}
	@PutMapping("/addimage5D/{idConsultation}")
	public String updateImage5D(@PathVariable("idConsultation") long idConsultation  , @RequestParam("image5") MultipartFile image5        ) throws IOException {

		service.updateImage5Droite(idConsultation, image5);
		return "Done pour image5 Droite  !!!!" ; 
	}
	
	
	/***********Oeil Gauche *************/
	
	@PutMapping("/addimage1G/{idConsultation}")
	public String updateImage1G(@PathVariable("idConsultation") long idConsultation  , @RequestParam("image1") MultipartFile image1  ) throws IOException {
		Consultation consultation = repository.findById(idConsultation).get();
		service.updateImage1Gauche(idConsultation , image1);
		return "Done pour image1 Gauche !!!!" ; 
	}
	@PutMapping("/addimage2G/{idConsultation}")
	public String updateImage2G(@PathVariable("idConsultation") long idConsultation  , @RequestParam("image2") MultipartFile image2 ) throws IOException {
		Consultation consultation = repository.findById(idConsultation).get();
		service.updateImage2Gauche(idConsultation, image2);
		return "Done pour image2  Gauche!!!!" ; 
	}
	@PutMapping("/addimage3G/{idConsultation}")
	public String updateImage3G(@PathVariable("idConsultation") long idConsultation  , @RequestParam("image3") MultipartFile image3 ) throws IOException {
		Consultation consultation = repository.findById(idConsultation).get();
		service.updateImage3Gauche(idConsultation, image3);
		return "Done pour image3 Gauche !!!!" ; 
	}
	@PutMapping("/addimage4G/{idConsultation}")
	public String updateImage4G(@PathVariable("idConsultation") long idConsultation  , @RequestParam("image4") MultipartFile image4 ) throws IOException {
		Consultation consultation = repository.findById(idConsultation).get();
		service.updateImage4Gauche(idConsultation,  image4);
		return "Done pour image4  Gauche!!!!" ; 
	}
	@PutMapping("/addimage5G/{idConsultation}")
	public String updateImage5G(@PathVariable("idConsultation") long idConsultation  , @RequestParam("image5") MultipartFile image5        ) throws IOException {
		Consultation consultation = repository.findById(idConsultation).get();
		service.updateImage5Gauche(idConsultation , image5);
		return "Done pour image5 Gauche  !!!!" ; 
	}
	
	
	@DeleteMapping("/deleteConsult/{id}/{idConsultation}")
	public void deleteProduct(@PathVariable("idConsultation") long idConsultation){
		
	
		repository.deleteById(idConsultation);
	} 
	@GetMapping("/Consultations/{id}")
	public List<Consultation> getAllProducts(@PathVariable("id") @ModelAttribute("id") long id){
         //pr.findById(id);
	    return  repository.findByGeneraliste_id(id);

	} 
	@GetMapping("/Consultation/{id}/{idPatient}") // hehdy pour lien de consultation pour chaque patient
	public List<Consultation> getConsultationsByPatient (@PathVariable("id") long id ,@PathVariable ("idPatient") long idPatient){
	
    return repository.findByPatient_cinAndGeneraliste_id(idPatient,id);		 
		
	}
	
	
	
	@GetMapping("/Consultation/{id}/{idConsultation}/{idPatient}")
	public Consultation getAllProductsbyid(@PathVariable("id") long id,@PathVariable("idConsultation") long idConsultation,@PathVariable("idPatient") long idPatient){
		Consultation conster = repository.findById(idConsultation).get();
		 if( conster.getImage1_Droite()== null) {
			  return conster;
		  }
		  else {
			    conster.setImage1_Droite(service.decompressZLib(conster.getImage1_Droite()));}	
			    
			    if( conster.getImage2_Droite()== null) {
					  return conster;
				  }
				  else {
					    conster.setImage2_Droite(service.decompressZLib(conster.getImage2_Droite()));	}
		
					    if( conster.getImage3_Droite()== null) {
							  return conster;
						  }
						  else {
							    conster.setImage3_Droite(service.decompressZLib(conster.getImage3_Droite()));	}
				
							    if( conster.getImage4_Droite()== null) {
									  return conster;
								  }
								  else {
									    conster.setImage4_Droite(service.decompressZLib(conster.getImage4_Droite()));	}
									    
									    if( conster.getImage5_Droite()== null) {
											  return conster;
										  }
										  else {
											    conster.setImage5_Droite(service.decompressZLib(conster.getImage5_Droite()));}
									    
									    
									    
									    
									    
											    
											    if( conster.getImage1_Gauche()== null) {
													  return conster;
												  }
												  else {
													    conster.setImage1_Gauche(service.decompressZLib(conster.getImage1_Gauche()));}
													    
													    if( conster.getImage2_Gauche()== null) {
															  return conster;
														  }
														  else {
															    conster.setImage2_Gauche(service.decompressZLib(conster.getImage2_Gauche()));}
															    
															    if( conster.getImage3_Gauche()== null) {
																	  return conster;
																  }
																  else {
																	    conster.setImage3_Gauche(service.decompressZLib(conster.getImage3_Gauche()));	}
														
																	    if( conster.getImage4_Gauche()== null) {
																			  return conster;
																		  }
																		  else {
																			    conster.setImage4_Gauche(service.decompressZLib(conster.getImage4_Gauche()));	}
																			    
																			    if( conster.getImage5_Gauche()== null) {
																					  return conster;
																				  }
																				  else {
																					    conster.setImage5_Gauche(service.decompressZLib(conster.getImage5_Gauche()));	}
																		
																
												
										
						
		return conster;
		  
		
	}
	// input id de Auto detection dans consultation
	
	
	@PutMapping("/editAuto/{idGeneraliste}/{idConsult}/{idAutoDetection}")//(2) scénarion besh ysir directement maa ajout autoDetectio(cad k nenzlu al AI model 2 fonction bsh ysiru whda huni whda post mtaa autodetection)  
	public Consultation updateID(@PathVariable("idGeneraliste") long idGeneraliste,@PathVariable("idConsult") long idConsult,  @PathVariable("idAutoDetection") long idAutoDetection) {
	Consultation consultation = repository.findById(idConsult).get();
		AutoDetection autp =pr.findById(idAutoDetection).get();
           consultation.setAutoDetection(autp);
           LocalDate date = LocalDate.now() ; 
       
         consultation.setDemandeAvisTime(LocalDateTime.now());
           
           consultation.setDateDemandeAvis(date);
	return	repository.save(consultation);
		 
	}
	
	@GetMapping("test")
	public List<Consultation> getAllDemandesss (){
		List<Consultation> liste = repository.findAll();
		List<Consultation> resultat= new ArrayList<>() ; 
		for(Consultation consult :liste) {
			if((consult.getAutoDetection().getAvisExpert()==null)) {
				if((consult.getDemandeAvisD()==1 && consult.getDemandeAvisG()==0)) {
				     resultat.add(consult);}
				if((consult.getDemandeAvisD()==0 && consult.getDemandeAvisG()==1)) {
				     resultat.add(consult);}
			  
				if((consult.getDemandeAvisD()==1 || consult.getDemandeAvisG()==1)) {
					if(resultat.contains(consult)) {
						 resultat.add(consult);
				    }
					}
			
		}
		
		}
		return resultat ; 
	}
	
	@GetMapping("/getAll")
	public List<Consultation> getttt (){
		List<Consultation> liste_Droite = repository.findByDemandeAvisD(1) ; 
		List<Consultation> liste1 = new ArrayList<Consultation>();// Empty
		for(Consultation consult :liste_Droite) {
			if((consult.getAutoDetection().getAvisExpert()==null)) {
		
				liste1.add(service.getDiffTime(consult.getId() , LocalDateTime.now(), consult.getDemandeAvisTime()))  ; 
			}
			else {
				// dejaa avis mtaa gauche existe 
				if((consult.getAutoDetection().getAvisExpert().getMaladieDroite()== null)
						&&(consult.getAutoDetection().getAvisExpert().getGraviteDroite()==0 )) {
					
					 liste1.add(service.getDiffTime(consult.getId() , LocalDateTime.now(), consult.getDemandeAvisTime()))  ; 
				}
			}
			}
		List<Consultation> liste_Gauche = repository.findByDemandeAvisG(1) ; 
		List<Consultation> liste2 = new ArrayList<Consultation>();
		for(Consultation consu :liste_Gauche) {
			if((consu.getAutoDetection().getAvisExpert()==null)) {
				
				liste2.add(service.getDiffTime(consu.getId() , LocalDateTime.now(), consu.getDemandeAvisTime()))  ; 
			}
			else {
				if((consu.getAutoDetection().getAvisExpert().getMaladieGauche()==null)
						&&(consu.getAutoDetection().getAvisExpert().getGraviteGauche()==0 )) {
					
					 liste2.add(service.getDiffTime(consu.getId() , LocalDateTime.now(), consu.getDemandeAvisTime()));
				}
			}
			}
		liste1.addAll(liste2) ;
		List<Consultation> resultats =new ArrayList<Consultation>() ; 
		resultats=liste1; 
		return resultats ; 
	}
	
	@GetMapping("/getNumConsultation/{idPatient}")
	public int  getNum(@PathVariable("idPatient") long idPatient) {
	List<Consultation> consultations = repository.findAll();
	int nbr = 0 ; 
	for(Consultation consult : consultations)
	{
		if(consult.getPatient().getId()== idPatient) {
			nbr++ ; 
		}
	}
	return nbr ; 	 
	}
	 /******  partie statistiques************/
	 @GetMapping("/sansDemandeAvisExpert")
     public int getSansAvis() {
	 
	  List<Consultation> liste = repository.findAll() ; 
	  List<Consultation> res = new ArrayList<Consultation>() ; 
	  for(Consultation  consult : liste) {
		  if(consult.getDemandeAvisD()== 0 && consult.getDemandeAvisG()== 0) {
			  res.add(consult) ; 
		  }
		 }
	  int nbr = res.size() ; 
	  
   	return nbr ; 
   	} 
	 /*****
	  *     Consultations avec Demande avis mais sans avis expert ---> hiya methode getAll illy fo9
	  * @return
	  */
	 // a verfier
	 @GetMapping("/avecDemandeAvisEtAvecReponse")
     public int getAllConsultAvecDemandeAvisExpertEtSansReponse() {
	 
	  List<Consultation> liste = repository.findAll() ; 
	  List<Consultation> res = new ArrayList<Consultation>() ; 
	  for(Consultation  consult : liste) {
		  if(consult.getAutoDetection().getAvisExpert() != null) {
			  
			  if(consult.getAutoDetection().getAvisExpert().getMaladieDroite()!= null || 
				consult.getAutoDetection().getAvisExpert().getGraviteDroite() !=0 ||
				consult.getAutoDetection().getAvisExpert().getMaladieGauche()!= null || 
				consult.getAutoDetection().getAvisExpert().getGraviteGauche() !=0) {
				 
				  res.add(consult) ; }
				 
			  
		  }
		 }
	  int nbr = res.size() ; 
	  
   	return nbr ; 
   	} 
	 
	 @GetMapping("/verifierAutoDetection")
	 public  int  getRes() {
		 List<Consultation> liste = repository.findAll();
		 List<Consultation> res = new ArrayList<Consultation>();
		 for(Consultation consult : liste ) {
 if(consult.getAutoDetection().getAvisExpert() != null) {
	 if((consult.getAutoDetection().getMaladieDroite()==consult.getAutoDetection().getAvisExpert().getMaladieDroite())||
	    (consult.getAutoDetection().getMaladieGauche())==consult.getAutoDetection().getAvisExpert().getMaladieGauche()) {
	
			 res.add(consult) ;
		
	 }
 }
		 }
		 int nbr = res.size();
		 return nbr ; }
	 
	

	 @GetMapping("/getEgaleAvis")
		public int getAvisEgaleAI (){
		 List<Consultation> liste =  repository.findAll() ; 
		 
		 List<Consultation> liste1 = new ArrayList<Consultation>();// Empty
			
			for(Consultation consult :liste) {
				if(consult.getAutoDetection()!= null) {
				if(consult.getAutoDetection().getAvisExpert()!= null) {
				if(consult.getAutoDetection().getMaladieDroite()!= null ||consult.getAutoDetection().getGraviteDroite()!= 0 ) {
				if((consult.getAutoDetection().getAvisExpert().getMaladieDroite().equals(consult.getAutoDetection().getMaladieDroite()))&&(consult.getAutoDetection().getAvisExpert().getGraviteDroite()==consult.getAutoDetection().getGraviteDroite())) {
					liste1.add(consult)  ; 
				}
				if((consult.getAutoDetection().getAvisExpert().getMaladieGauche()==consult.getAutoDetection().getMaladieGauche())&&(consult.getAutoDetection().getAvisExpert().getGraviteGauche()==consult.getAutoDetection().getGraviteGauche())) {
				if(!(liste1.contains(consult))){
					liste1.add(consult)  ; 
				}
				}
				
				}
			
				}	
	 }
	}
			int nbr =liste1.size();

			return nbr ; 
}
	
	 
	 /**** get All Demandes *****/
	 @GetMapping("/getTime")
		public String  gettttAvecDateTikji (){
			
		 LocalDateTime date = LocalDateTime.now() ; 
		  DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");  
		  String formatDateTime = date.format(format);   
		  return formatDateTime ; 
		  
		}
	/* @GetMapping("/getTimeDate/{idConsultation}")
		public long  getDifTime (@PathVariable("idConsultation") long id){
			
		  Consultation consult =repository.findById(id).get();
	      LocalDateTime  dateConsult  = consult.getDemandeAvisTime() ; 
	      Duration duration = Duration.between(LocalDateTime.now(), dateConsult);
	       long difM  =Math.abs(duration.toMinutes()) ; 
	       long res = 0 ; 
	       if( difM != 0 && difM <60) {
	    	     res =difM;
	       }
	       if( difM != 0 && difM >=60) {
	    	   res = difM /60 ; 
	       }
  return res ; 
	 }	
		*/

	 @GetMapping("/getLast")
	 public List<Consultation> gettttt(){
		 
		 List<Consultation> res= new ArrayList<Consultation>() ; 
		 List<Consultation> liste = repository.findAll() ;
		 
		 for(Consultation consultation : liste) {
			 if(consultation.getAutoDetection()!= null) {
				 if(consultation.getAutoDetection().getAvisExpert()== null) {
					 
					res.add(service.getDiffTime(consultation.getId(), LocalDateTime.now(),consultation.getDemandeAvisTime()));	
				
				 
			 }
		 }
	
	 }	 return res ; 
}
}
