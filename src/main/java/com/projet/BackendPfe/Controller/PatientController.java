package com.projet.BackendPfe.Controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import com.projet.BackendPfe.Entity.AutoDetection;
import com.projet.BackendPfe.Entity.Consultation;
import com.projet.BackendPfe.Entity.Expert;
import com.projet.BackendPfe.Entity.Generaliste;
import com.projet.BackendPfe.Entity.Patient;
import com.projet.BackendPfe.repository.ConsultationRepository;
import com.projet.BackendPfe.repository.ExpertRepository;
import com.projet.BackendPfe.repository.GeneralisteRepository;
import com.projet.BackendPfe.repository.PatientRepository;
import com.projet.BackendPfe.request.PatientRequest;
import com.projet.BackendPfe.request.RegisterRequestExpert;
import com.projet.BackendPfe.services.IGestionPatient;
import com.projet.BackendPfe.services.PatientService;


@CrossOrigin("*")
@RestController
@RequestMapping("/patient")
public class PatientController {
	
	@Autowired GeneralisteRepository medecinRepository;

	@Autowired
	IGestionPatient gestionPatient;
	@Autowired
	PatientRepository pr;
	@Autowired
	GeneralisteRepository pre;
	@Autowired 
	PatientService ps;
	@Autowired 
	ExpertRepository  repository;
	@Autowired 
	ConsultationRepository  repositoryC;

	@GetMapping("/patiente/{id}")
	public List<Patient> getAll(@PathVariable("id")  long id){
        List<Patient> liste =pr.findAll();
        List<Patient> res = new ArrayList<Patient>();
        for(Patient p : liste) {
        	if(p.getGeneraliste().getId()==id) {
        		res.add(p) ; 
        	}
        }
		
		    return   res;
	} 
	

	
	@PostMapping("/addpatient/{id}")
	public void AddProduct(@RequestBody PatientRequest patient , @PathVariable("id") @ModelAttribute("id") long id ){
		Generaliste  generaliste = medecinRepository.findById(id).get(); 
		Patient p = new Patient(patient.getCin(),patient.getUsername(),patient.getEmail(),patient.getTelephone(),patient.getGender(),patient.getDateNaiss(),patient.getAntecedant(),generaliste , LocalDate.now());
		pr.save(p);
	}
	
	
	@GetMapping("/patient/{id}/{idPatient}")
	public Patient productById(@PathVariable("idPatient") long idPatient, @PathVariable("id") @ModelAttribute("id") long id ){
		return pr.findById(idPatient).get();
	}
	@DeleteMapping("/deletepatient/{idPatient}")
	public void deleteProduct(@PathVariable("idPatient") long idPatient){

		pr.deleteById(idPatient);
	} 
	@GetMapping("/patient/chercher/{username}")
	public List<Patient> patientByName(@PathVariable("username") String username ){
		return pr.findByUsernameContains(username);
	}
	 @PutMapping("/updatePatient/{id}/{cin}")  // check it 
	  public ResponseEntity<Patient> updateGeneraliste(@PathVariable("cin") long cin, @RequestBody Patient Utilisateur) {
	    System.out.println("Update Utilisateur with ID = " + cin + "...");
	 
	    Optional<Patient> UtilisateurInfo = pr.findById(cin);

	    	Patient utilisateur = UtilisateurInfo.get();
	       	utilisateur.setCin(Utilisateur.getCin());
	       	utilisateur.setUsername(Utilisateur.getUsername());
	       	utilisateur.setEmail(Utilisateur.getEmail());
	       	utilisateur.setDateNaiss(Utilisateur.getDateNaiss());
			
	      	utilisateur.setAntecedant(Utilisateur.getAntecedant());
	    	utilisateur.setTelephone(Utilisateur.getTelephone());
	    	utilisateur.setGender(Utilisateur.getGender());
	    	//utilisateur.setImage(Utilisateur.getImage());       //  utilisateur.getEmail();
	        // utilisateur.getUsername();
	    	
	      return new ResponseEntity<>(pr.save(utilisateur), HttpStatus.OK);
	    } 	
	 @GetMapping("/get/{id}")
		public Patient  getPatientById(@PathVariable("id") long id){
		Patient patient = 	pr.findById(id).get();
			    return   patient;
		} 
	 @GetMapping("/all")
		public List<Patient>  getAllPatients(){
			    return   pr.findAll();
		} 
 
	 @GetMapping("/age/{idPatient}")
     public int getAgePatient(@PathVariable("idPatient") long idPatient) {
	 
		 Patient patient = pr.findById(idPatient).get() ; 
		 // get date de NAissance de patient --> de type string 
		 String  dateNaiss= patient.getDateNaiss() ; 
		 // regle pour transformer string to ---> localDate 
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		 // date de nasiisance de type local date 
		 LocalDate dateNaissance = LocalDate.parse(dateNaiss, formatter);
		 // date d'aujourd'hui
	     LocalDate date = LocalDate.now() ; 
	     int yearDateNaissance  =dateNaissance.getYear()  ;
	     int yearDateToDay = date.getYear()  ;
	     int agePatient = yearDateToDay-yearDateNaissance ; 
	  
   	return agePatient ; } 
	 // pour faire stat sur tout annee
	 @GetMapping("/patientParMonth")
     public int getPatients(@RequestParam("month") Integer month) {
	     List<Patient> resultat = new ArrayList<Patient>() ; 
		 List<Patient> liste = pr.findAll() ; 
		 for(Patient patient :liste) {
		Integer monthInscription = new Integer(patient.getDateInscription().getMonthValue());
		Integer yearInscription = new Integer(patient.getDateInscription().getYear());
			 if(month.equals(monthInscription) && (yearInscription.equals(LocalDate.now().getYear())) ) {
				 resultat.add(patient) ; 
			 }
			 
			 
		 }
		 int nbrPatients = resultat.size() ; 
		 
	  return nbrPatients ;
	  } 
	 // pour faire stat par date aujourdhui
	 @GetMapping("/allParDate")
		public int  getAllPatientsByDateInscription(){
		 List<Patient> liste = pr.findAll(); 
		 List<Patient> res = new ArrayList<Patient>() ; 
		 for(Patient patient : liste) {
			 if(patient.getDateInscription().equals(LocalDate.now())) {
				 res.add(patient) ;
			 }
		 }      
		 return res.size() ; 
		}
	// pour faire stat par cette semaine
		 @GetMapping("/allParSemaine")
			public int  getAllPatientsBySemaine(){
			 List<Patient> liste = pr.findAll(); 
			 List<Patient> res = new ArrayList<Patient>() ; 
			 Integer dateYearActuel = LocalDate.now().getYear() ; 
			 Integer dateMonthActuel = LocalDate.now().getMonthValue() ; 
			 Integer dateDayActuel = LocalDate.now().getDayOfMonth() ;
			 Integer testSemaine = dateDayActuel / 4 ;
			 Integer nbrSemaineS =0  ;
			 
			 if(testSemaine <= 1.75) {
				 nbrSemaineS =1 ; 
			 }
			 else if(testSemaine <= 3.5) {
				 nbrSemaineS =2 ; 
			 }else if(testSemaine <= 5.25) {
				 nbrSemaineS =3 ; 
			 }
			 else if(testSemaine <= 7.75) {
				  nbrSemaineS =4 ; 
			 }
			 
			 for(Patient patient : liste) {
				 
				 Integer dateInscripYearActuel = patient.getDateInscription().getYear() ; 
				 Integer dateInscripMonthActuel = patient.getDateInscription().getMonthValue() ; 
				 Integer dateInscripDayActuel = patient.getDateInscription().getDayOfMonth() ; 
				 Integer nbrSemaineP=0 ;
				 Integer testSemaineP = dateInscripDayActuel / 4 ;
				 if(testSemaineP <= 1.75) {
					 nbrSemaineP =1 ; 
				 }
				 else if(testSemaineP <= 3.5) {
					 nbrSemaineP =2 ; 
				 }else if(testSemaineP <= 5.25) {
					 nbrSemaineP =3 ; 
				 }
				 else if(testSemaineP <= 7.75) {
					 nbrSemaineP =4 ; 
				 }
		if(dateYearActuel.equals(dateInscripYearActuel)) {
		    if(dateMonthActuel.equals(dateInscripMonthActuel)) {
			    if(nbrSemaineP.equals(nbrSemaineS) ) {		  
			 		 res.add(patient) ;
				 }
			 }  
		   }    
			 
			}
			 return res.size() ; 
		 } 
	 //pour faire stat sur moins actuel
		@GetMapping("/parMonth")
		public int nbrPAtientsMois() {
			List<Patient> liste = pr.findAll() ; 
			List<Patient> res = new ArrayList<Patient>() ; 
	         
			Integer monthActuel = LocalDate.now().getMonthValue();
			Integer yearActuel = LocalDate.now().getYear();
			for(Patient  patient  : liste) {
				Integer dateInscrptionMonth = patient.getDateInscription().getMonthValue() ; 
				Integer dateInscrptionYear = patient.getDateInscription().getYear();

				if((dateInscrptionMonth.equals(monthActuel)) && (dateInscrptionYear.equals(yearActuel))) {
					res.add(patient) ; 
				}
			}
			return res.size() ; 
		}
		 //pour faire stat sur annee actuel
		@GetMapping("/parYear")
		public int nbrPatientsPArAnnee() {
			List<Patient> liste = pr.findAll() ; 
			List<Patient> res = new ArrayList<Patient>() ; 
	         
			Integer yearActuel = LocalDate.now().getYear();
			for(Patient  patient  : liste) {
				Integer dateInscrptionYear = patient.getDateInscription().getYear();

				if((dateInscrptionYear.equals(yearActuel))) {
					res.add(patient) ; 
				}
			}
			return res.size() ; 
		}
	 @GetMapping("/ageS")
	  public int getAgePatientSupp() {
		 
			List <Patient> patients = pr.findAll() ; 
		     List<Patient> resultat = new ArrayList<Patient>() ; 

			 // get date de NAissance de patient --> de type string 
			for(Patient patient :patients) {
			 String  dateNaiss= patient.getDateNaiss() ; 

			 // regle pour transformer string to ---> localDate 
			 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			 // date de nasiisance de type local date 
			 LocalDate dateNaissance = LocalDate.parse(dateNaiss, formatter);
			 // date d'aujourd'hui
		     LocalDate date = LocalDate.now() ; 
		  int yearDateNaissance  =dateNaissance.getYear()  ;
		  int yearDateToDay = date.getYear()  ;
		  int agePatient = yearDateToDay-yearDateNaissance ; 
		  if(agePatient>50) {
		resultat.add(patient) ; 
			}
		  
			}
			int nbrPatient=resultat.size();
			return nbrPatient;
		 }
		 
		 @GetMapping("/ageI")
		  public int getAgePatientSup() {
			 
				List <Patient> patients = pr.findAll() ; 
			     List<Patient> resultat = new ArrayList<Patient>() ; 

				 // get date de NAissance de patient --> de type string 
				for(Patient patient :patients) {
				 String  dateNaiss= patient.getDateNaiss() ; 

				 // regle pour transformer string to ---> localDate 
				 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				 // date de nasiisance de type local date 
				 LocalDate dateNaissance = LocalDate.parse(dateNaiss, formatter);
				 // date d'aujourd'hui
			     LocalDate date = LocalDate.now() ; 
			  int yearDateNaissance  =dateNaissance.getYear()  ;
			  int yearDateToDay = date.getYear()  ;
			  int agePatient = yearDateToDay-yearDateNaissance ; 
			  if(agePatient<50) {
			resultat.add(patient) ; 
				}
			  
				}
				int nbrPatient=resultat.size();
				return nbrPatient;
			 }
		 // Gender des patients
		 @GetMapping("/homme")
		  public int getPatientsHomme() {
			 
				 List <Patient> patients = pr.findAll() ; 
			     List<Patient> resultat = new ArrayList<Patient>() ; 

				for(Patient patient :patients) {
			       if(patient.getGender().equals("homme")) {
		          	resultat.add(patient) ; 	}

				}
				int nbrPatient=resultat.size();
				return nbrPatient;
			 }
		 @GetMapping("/femme")
		  public int getPatientsFemme() {
			 
				 List <Patient> patients = pr.findAll() ; 
			     List<Patient> resultat = new ArrayList<Patient>() ; 

				for(Patient patient :patients) {
			       if(patient.getGender().equals("femme")) {
		          	resultat.add(patient) ; 	}

				}
				int nbrPatient=resultat.size();
				return nbrPatient;
			 }
		 
		 @GetMapping("/nbrAll")
		  public int getAllNbr() {
			 
				 List <Patient> patients = pr.findAll() ; 

				int nbrPatient=patients.size();
				return nbrPatient;
			 }
		 @GetMapping("/getYear")
		  public int getYaer() {
			 
				return LocalDate.now().getYear();
			 }
		 
	
	@GetMapping("/getMoyenneAgeParMonth")
		  public int methode2 (@RequestParam("month") Integer month) {
		 int  ages_patients = 0  ;
		 int  vari = 0  ;
		 List<Patient> resultat = new ArrayList<Patient>() ; 
		 List<Patient> liste = pr.findAll() ; 
		 for(Patient patient :liste) {
		 Integer monthInscription = new Integer(patient.getDateInscription().getMonthValue());
		 Integer yearInscription = new Integer(patient.getDateInscription().getYear());
		 LocalDate dateNaissance = LocalDate.parse(patient.getDateNaiss());
		 Integer yearDateNaissance = dateNaissance.getYear();
		 LocalDate dateActuelle = LocalDate.now();
		 Integer yearDateActuel = dateActuelle.getYear() ;
		 
			 if(month.equals(monthInscription) && (yearInscription.equals(LocalDate.now().getYear())) ) {
				 resultat.add(patient) ; 
				 vari ++ ;
				 ages_patients = ages_patients+ (yearDateActuel - yearDateNaissance) ; 
			 }
			 }
		
			if(vari == 0) {
				return 0 ; 
			}
			else {
				return ages_patients /vari ;
			}
				
			 
	}	
	
	@GetMapping("/getAllConsultionsDePatient/{idExpert}/{idPatient}")
	public List<Consultation>getAllConsultationDePatientX(@PathVariable("idPatient") long idPatient,
			@PathVariable("idExpert") long idExpert) {
		
	 	Expert expert = repository.findById(idExpert).get();
		long  iddpatient = pr.findById(idPatient).get().getId();	
    	List<Consultation> liste = repositoryC.findAll();
    	List<Consultation> res= new ArrayList<>() ; 
    	for(Consultation consult :liste) {
    		if(consult.getAutoDetection()!= null) {
    			if(consult.getAutoDetection().getAvisExpert()!= null) {
    		if((consult.getAutoDetection().getAvisExpert().getExpert().getId())==(expert.getId())) {
    			if((consult.getPatient().getId()== iddpatient)) {
    			res.add(consult);
    			
    		}
    	}
    	}
    	}
	}return res ; 
	}
	// selon avis expert 
	@GetMapping("/getAllPatientSains")
	public long gaetAllPatientsSains() {
		List<Consultation> liste = repositoryC.findAll() ; 
		List<Patient> res = new ArrayList<Patient>() ; 
		
		for (Consultation consultation : liste) {
		if(consultation.getAutoDetection() != null) {
			// deux yeux
		  if(consultation.getAutoDetection().getAvisExpert() != null) {
			  if(consultation.getAutoDetection().getAvisExpert().getMaladieDroite()!= null &&(consultation.getAutoDetection().getAvisExpert().getMaladieGauche()!= null ) )
		    if(consultation.getAutoDetection().getAvisExpert().getMaladieDroite().equals("Sain")
		    		&& consultation.getAutoDetection().getAvisExpert().getMaladieGauche().equals("Sain")) {
		    	if(!(res.contains(consultation.getPatient())))	{	
			    	res.add(consultation.getPatient()) ;
					}
					}
				
		  // oeil droite 
		
			  if(consultation.getAutoDetection().getAvisExpert().getMaladieDroite()!= null &&(consultation.getAutoDetection().getAvisExpert().getMaladieGauche()== null ) )
		    if(consultation.getAutoDetection().getAvisExpert().getMaladieDroite().equals("Sain")) {
		    	if(!(res.contains(consultation.getPatient())))	{	
			    	res.add(consultation.getPatient()) ;
					}
					}
				
		  // oeil gauche
		
			  if(consultation.getAutoDetection().getAvisExpert().getMaladieDroite()== null &&(consultation.getAutoDetection().getAvisExpert().getMaladieGauche()!= null ) )
		    if( consultation.getAutoDetection().getAvisExpert().getMaladieGauche().equals("Sain")) {
				if(!(res.contains(consultation.getPatient())))	{	
		    	res.add(consultation.getPatient()) ;
				}
					}
				}
			}
		}
	return res.size() ; 
}
	@GetMapping("/getAllPatientMAlades")
	public long gaetAllPatientsMalades() {
		List<Consultation> liste = repositoryC.findAll() ; 
		List<Patient> res = new ArrayList<Patient>() ; 
		
		for (Consultation consultation : liste) {
		if(consultation.getAutoDetection() != null) {
			// deux yeux
		  if(consultation.getAutoDetection().getAvisExpert() != null) {
			  if(consultation.getAutoDetection().getAvisExpert().getMaladieDroite()!= null &&(consultation.getAutoDetection().getAvisExpert().getMaladieGauche()!= null ) )
		    if(!(consultation.getAutoDetection().getAvisExpert().getMaladieDroite().equals("Sain"))
		    		&& !(consultation.getAutoDetection().getAvisExpert().getMaladieGauche().equals("Sain"))) {
		    	if(!(res.contains(consultation.getPatient())))	{	
			    	res.add(consultation.getPatient()) ;
					}
					}
				
		  // oeil droite 
		
			  if(consultation.getAutoDetection().getAvisExpert().getMaladieDroite()!= null &&(consultation.getAutoDetection().getAvisExpert().getMaladieGauche()== null ) )
		    if(!(consultation.getAutoDetection().getAvisExpert().getMaladieDroite().equals("Sain"))) {
		    	if(!(res.contains(consultation.getPatient())))	{	
			    	res.add(consultation.getPatient()) ;
					}
					}
				
		  // oeil gauche
		
			  if(consultation.getAutoDetection().getAvisExpert().getMaladieDroite()== null &&(consultation.getAutoDetection().getAvisExpert().getMaladieGauche()!= null ) )
		    if( !(consultation.getAutoDetection().getAvisExpert().getMaladieGauche().equals("Sain"))) {
				if(!(res.contains(consultation.getPatient())))	{	
		    	res.add(consultation.getPatient()) ;
				}
					}
				}
			}
		}
	return res.size() ; 
}
	
	//selon autoDetection
	@GetMapping("/getAllPatientSainsAutoDetection")
	public long gaetAllPatientsSainsAutoDetection() {
		List<Consultation> liste = repositoryC.findAll() ; 
		List<Patient> res = new ArrayList<Patient>() ; 
		
		for (Consultation consultation : liste) {
		if(consultation.getAutoDetection() != null) {
			// deux yeux
			  if(consultation.getAutoDetection().getMaladieDroite()!= null &&(consultation.getAutoDetection().getMaladieGauche()!= null ) )
		    if(consultation.getAutoDetection().getMaladieDroite().equals("Sain")
		    		&& consultation.getAutoDetection().getMaladieGauche().equals("Sain")) {
		    	if(!(res.contains(consultation.getPatient())))	{	
			    	res.add(consultation.getPatient()) ;
					}
					}
				
		  // oeil droite 
		
			  if(consultation.getAutoDetection().getMaladieDroite()!= null &&(consultation.getAutoDetection().getMaladieGauche()== null ) )
		    if(consultation.getAutoDetection().getMaladieDroite().equals("Sain")) {
		    	if(!(res.contains(consultation.getPatient())))	{	
			    	res.add(consultation.getPatient()) ;
					}
					}
				
		  // oeil gauche
		
			  if(consultation.getAutoDetection().getMaladieDroite()== null &&(consultation.getAutoDetection().getMaladieGauche()!= null ) )
		    if( consultation.getAutoDetection().getMaladieGauche().equals("Sain")) {
				if(!(res.contains(consultation.getPatient())))	{	
		    	res.add(consultation.getPatient()) ;
				}
					}
				}
			}
		
	return res.size() ; 
}
	@GetMapping("/getAllPatientMAladesAutoDetection")
	public long gaetAllPatientsMaladesAutoDetection() {
		List<Consultation> liste = repositoryC.findAll() ; 
		List<Patient> res = new ArrayList<Patient>() ; 
		
		for (Consultation consultation : liste) {
		if(consultation.getAutoDetection() != null) {
			// deux yeux

			  if(consultation.getAutoDetection().getMaladieDroite()!= null &&(consultation.getAutoDetection().getMaladieGauche()!= null ) )
		    if(!(consultation.getAutoDetection().getMaladieDroite().equals("Sain"))
		    		&& !(consultation.getAutoDetection().getMaladieGauche().equals("Sain"))) {
		    	if(!(res.contains(consultation.getPatient())))	{	
			    	res.add(consultation.getPatient()) ;
					}
					}
				
		  // oeil droite 
		
			  if(consultation.getAutoDetection().getMaladieDroite()!= null &&(consultation.getAutoDetection().getMaladieGauche()== null ) )
		    if(!(consultation.getAutoDetection().getMaladieDroite().equals("Sain"))) {
		    	if(!(res.contains(consultation.getPatient())))	{	
			    	res.add(consultation.getPatient()) ;
					}
					}
				
		  // oeil gauche
		
			  if(consultation.getAutoDetection().getMaladieDroite()== null &&(consultation.getAutoDetection().getMaladieGauche()!= null ) )
		    if( !(consultation.getAutoDetection().getMaladieGauche().equals("Sain"))) {
				if(!(res.contains(consultation.getPatient())))	{	
		    	res.add(consultation.getPatient()) ;
				}
					}
				}
			}
	
	return res.size() ; 
}

}