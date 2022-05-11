package com.projet.BackendPfe.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projet.BackendPfe.Entity.AdminDigitalManager;
import com.projet.BackendPfe.Entity.AdminMedicalManager;
import com.projet.BackendPfe.Entity.Expert;

@Repository
public interface AdminMedicalManagerRepository  extends JpaRepository<AdminMedicalManager, Long>{
	
AdminDigitalManager findByUsername(String username);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);
	
	Optional<AdminDigitalManager> findByImage(String image);
	List<AdminMedicalManager> findByRole(String role);
	AdminMedicalManager  findByEmail(String email);
}
