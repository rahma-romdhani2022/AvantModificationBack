package com.projet.BackendPfe.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.projet.BackendPfe.Entity.Expert;
import com.projet.BackendPfe.Entity.Generaliste;

@Repository
public interface GeneralisteRepository extends JpaRepository<Generaliste, Long>{
	
	Optional<Generaliste> findByUsername(String username);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);
	
	Optional<Generaliste> findByImage(String image);
	List<Generaliste> findByRole(String role);
	List<Generaliste> findByGender(String gender);
}
