package com.projet.BackendPfe.Controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.projet.BackendPfe.Entity.AdminDigitalManager;
import com.projet.BackendPfe.Entity.Expert;
import com.projet.BackendPfe.Entity.Generaliste;
import com.projet.BackendPfe.Entity.User;
import com.projet.BackendPfe.config.JwtTokenUtil;
import com.projet.BackendPfe.domaine.JwtResponse;
import com.projet.BackendPfe.domaine.Message;
import com.projet.BackendPfe.exception.ResourceNotFoundException;
import com.projet.BackendPfe.repository.AdminDigitalMangerRepository;
import com.projet.BackendPfe.repository.ExpertRepository;
import com.projet.BackendPfe.repository.GeneralisteRepository;
import com.projet.BackendPfe.repository.UserRepository;
import com.projet.BackendPfe.request.LoginRequest;
import com.projet.BackendPfe.request.RegisterRequest;
import com.projet.BackendPfe.request.RegisterRequestAdmin;
import com.projet.BackendPfe.request.RegisterRequestExpert;
import com.projet.BackendPfe.request.RegisterRequestGeneraliste;
import com.projet.BackendPfe.services.ExpertService;
import com.projet.BackendPfe.services.GeneralisteService;
import com.projet.BackendPfe.services.UserDetailsImpl;
import com.projet.BackendPfe.services.UserDetailsServiceImpl;
import com.fasterxml.jackson.databind.JsonMappingException;

import org.springframework.boot.json.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class UserController {
	@Autowired 	UserRepository repository;
	@Autowired 	AuthenticationManager authenticationManager;
	@Autowired	UserRepository userRepository;
	@Autowired	ExpertRepository expertRepository;
	@Autowired	GeneralisteRepository genRepository;
	@Autowired	AdminDigitalMangerRepository adminRepository;
	@Autowired ExpertService service ; 

		@Autowired private ExpertService expertService ;
	
	@Autowired
	private GeneralisteService generalisteService ;
	
	@Autowired	PasswordEncoder encoder;
	@Autowired	JwtTokenUtil jwtUtils;
	
	
	
	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest data) {
		System.out.println("aaaa");
		System.out.println(data.getPassword());
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						data.getUsername(),
						data.getPassword())
			
				);
		  System.out.println("bbbbb");
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
		

		return ResponseEntity.ok(new JwtResponse(jwt, 
												 userDetails.getId(), 
												 userDetails.getUsername(), 
												 userDetails.getNomPrenom(), 
												 userDetails.getEmail() , 
												 userDetails.getRole()
											));
	}
	
	
	@GetMapping("/user/{id}")
	public User getUser(@PathVariable("id") long id) {
		User user = userRepository.findById(id).get() ; 
		if(user.getImage()==null) {
			return user;
		}
		else {
			user.setImage(expertService.compressZLib(user.getImage()));
			return user ; 
		}
		
	}

	    /****************** Test put ************/
	    @PutMapping( "/updateEx/{id}")
		 public ResponseEntity<Message> saveUser (@PathVariable ("id") long id , @RequestParam("file") MultipartFile file,
				 @RequestParam("expert") String expert) throws JsonParseException , JsonMappingException , Exception
		 {
	    	
	       Expert expert1 = expertRepository.findById(id).get();
	        expert1 =new ObjectMapper().readValue(expert, Expert.class); 
	        expert1.setImage(expertService.compressZLib(file.getBytes()));
	        Expert  resultat  = repository.save(expert1);
	        	return new ResponseEntity<Message>(new Message ("Expert  savrd "),HttpStatus.OK);	
		 }

		  
		  
}