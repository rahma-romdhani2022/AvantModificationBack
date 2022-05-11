package com.projet.BackendPfe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projet.BackendPfe.Entity.AutoDetection;
import com.projet.BackendPfe.Entity.AvisExpert;

public interface AvisExpertRepository extends JpaRepository<AvisExpert, Long> {

}
