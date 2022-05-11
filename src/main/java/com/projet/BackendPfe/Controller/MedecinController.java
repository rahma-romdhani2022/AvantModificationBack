package com.projet.BackendPfe.Controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletContext;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.projet.BackendPfe.Entity.Expert;
import com.projet.BackendPfe.Entity.Generaliste;
import com.projet.BackendPfe.config.JwtTokenUtil;
import com.projet.BackendPfe.domaine.JwtResponse;
import com.projet.BackendPfe.domaine.Message;
import com.projet.BackendPfe.repository.GeneralisteRepository;
import com.projet.BackendPfe.repository.UserRepository;
import com.projet.BackendPfe.request.LoginRequest;
import com.projet.BackendPfe.request.RegisterRequestGeneraliste;
import com.projet.BackendPfe.services.GeneralisteService;
import com.projet.BackendPfe.services.UserDetailsImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/medecin")
public class MedecinController {
	@Autowired 	AuthenticationManager authenticationManager;
	@Autowired	GeneralisteRepository genRepository;
	@Autowired	UserRepository userRepository;
	@Autowired
	private GeneralisteService generalisteService ;
	@Autowired	PasswordEncoder encoder;
	@Autowired	JwtTokenUtil jwtUtils;
	@Autowired  ServletContext context;
	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest data) {
		System.out.println("aaaa");
		System.out.println(data.getPassword());
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						data.getUsername(),
						data.getPassword()));
		
		  System.out.println("bbbbb");
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
		

		return ResponseEntity.ok(new JwtResponse(jwt, 
												 userDetails.getId(), 
												 userDetails.getUsername(), 
												 userDetails.getNomPrenom(), 
												 userDetails.getEmail(),
												 userDetails.getRole()
											));
	}
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequestGeneraliste signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new Message("Error: Username is already taken!"));
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new Message("Error: Email is already in use!"));
		}

		// Create new user's account
		Generaliste user = new Generaliste(signUpRequest.getUsername(), 
							 signUpRequest.getEmail(),
							 signUpRequest.getNomPrenom(),
							 encoder.encode(signUpRequest.getPassword()),
									 signUpRequest.getGender(),
									 signUpRequest.getTelephone(),
									 signUpRequest.getImage() , LocalDate.now() , "generaliste", signUpRequest.getSpecialite());
		genRepository.save(user);

		return ResponseEntity.ok(new Message("User registered successfully!"));
	}	  
	  @PutMapping("/update/{id}")
	  public ResponseEntity<Generaliste> updateGeneraliste(@PathVariable("id") long id, @RequestBody Generaliste Utilisateur) {
	    System.out.println("Update Utilisateur with ID = " + id + "...");
	 
	    Optional<Generaliste> UtilisateurInfo = genRepository.findById(id);

	    	Generaliste utilisateur = UtilisateurInfo.get();
	    	utilisateur.setTelephone(Utilisateur.getTelephone());
	    	utilisateur.setGender(Utilisateur.getGender());
	    	//utilisateur.setImage(Utilisateur.getImage());       //  utilisateur.getEmail();
	        // utilisateur.getUsername();
	    	
	      return new ResponseEntity<>(genRepository.save(utilisateur), HttpStatus.OK);
	    } 	
	/*  @PutMapping("/updateImage/{id}")
	  public String updateExpert(@PathVariable("id") long id,
				 @RequestParam("image") MultipartFile image) throws IOException
	    		  {
		  generalisteService.updateImage(id, image);
		    	return "Done  , image updated!!!";
		    }*/
	  @PutMapping("/updateImage/{id}")
	  public String updateExpert(@PathVariable("id") long id ,  @RequestParam("imageFile") MultipartFile imageFile ) throws IOException		    		  {
		  generalisteService.updateImage(id,imageFile);
		    	return "Done !!!";
		    }
	 
	  @GetMapping(path = { "/getImage/{id}" })
		public Generaliste getImage(@PathVariable("id") long id) throws IOException {

			Generaliste generaliste = genRepository.findById(id).get();
			Generaliste img = new Generaliste(generalisteService.decompressZLib(generaliste.getImage()));
			return img;
		}
  
	  @GetMapping( "/getMedecin/{id}" )
		public Generaliste getExpert(@PathVariable("id") long id) throws IOException {

		  Generaliste expert = genRepository.findById(id).get();
		  if(expert.getImage()== null) {
			  return expert;
		  }
		  else {
			    expert.setImage(generalisteService.decompressZLib(expert.getImage()));	
			    return expert;
		  }
		
		
		}
	  @GetMapping("/all")
		public List<Generaliste> getAllMg(){
			return genRepository.findByRole("generaliste");
			}

	  @DeleteMapping("/{idMedecin}")
	  public void supprimer(@PathVariable("idMedecin") long idMedecin) {
		  genRepository.deleteById(idMedecin);
	  }
	  /*** Statistiques ************/
	  @GetMapping("/getMedecinParMonth")
	  public  int  getMedecinParMonth (@RequestParam("month") Integer month) {
	  	
	  	     List<Generaliste> resultat = new ArrayList<Generaliste>() ; 
	  		 List<Generaliste> medecins = genRepository.findAll() ; 
	  		 for(Generaliste medecin :medecins) {
	  		Integer monthInscription = new Integer(medecin.getDate_inscription().getMonthValue());
	  		
	  		if(month.equals(monthInscription)) {
	  				 resultat.add(medecin) ; 
	  				 System.out.println("monthhhhhhhhhhh"+monthInscription);
	  			 }
	  			 
	  			 
	  		 } 
	  	  return resultat.size() ;
	  	  }
	  
	  @GetMapping("/getMedecinByGenderHommeByMonth")
	  public  int  getMedecinByGender ( List<Generaliste> liste) {
		   List<Generaliste> resultat = new ArrayList<Generaliste>() ; 
		  for(Generaliste medecin :liste) {
		  			 if(medecin.getGender().equals("homme")) {
		  				 resultat.add(medecin) ; 
		  			 }
		  			 
		  			 
		  		 }
		  		 int nbrGeneralistes = resultat.size() ; 
		  		 
		  	  return nbrGeneralistes ;
		  	  }
	  @GetMapping("/getMedecinByGenderFemmeByMonth")
	  public  int  getMedecinByGenderFemme ( List<Generaliste> liste) {
		   List<Generaliste> resultat = new ArrayList<Generaliste>() ; 
		  for(Generaliste medecin :liste) {
		  			 if(medecin.getGender().equals("femme")) {
		  				 resultat.add(medecin) ; 
		  			 }
		  			 
		  			 
		  		 }
		  		 int nbrGeneralistes = resultat.size() ; 
		  		 
		  	  return nbrGeneralistes ;
		  	  }
	  
	  @GetMapping("/getAllNbr")
	  public  int  getAllNbr () {
		   List<Generaliste> resultat = genRepository.findAll() ; 

		  		 int nbrGeneralistes = resultat.size() ; 
		  		 
		  	  return nbrGeneralistes ;
		  	  }
}

