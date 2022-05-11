package com.projet.BackendPfe.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projet.BackendPfe.Entity.Expert;
import com.projet.BackendPfe.Entity.Generaliste;

@Repository
public interface ExpertRepository extends JpaRepository<Expert, Long>{

	Expert findByUsername(String username);
	Expert findByEmail(String email);
	Boolean existsByUsername(String username);
    Optional<Expert> findByImage(String image);
	Boolean existsByEmail(String email);
	List<Expert> findByRole(String role);
	List<Expert> findByGender(String gender);


	
}
