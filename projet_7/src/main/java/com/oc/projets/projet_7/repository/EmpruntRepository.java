package com.oc.projets.projet_7.repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.oc.projets.projet_7.entity.Emprunt;
import com.oc.projets.projet_7.entity.Usager;

@Repository
public interface EmpruntRepository extends JpaRepository<Emprunt, Long> {

	List<Emprunt> findByUsager(Usager usager);
	
	List<Emprunt> findByActif(Boolean actif);
	
	List<Emprunt> findByUsagerAndActif(Usager usager, Boolean actif);
	
	List<Emprunt> findByDateEmpruntAndActif(LocalDate date, Boolean actif);
	
	@Query(value = "SELECT * FROM emprunt WHERE actif = true AND "
			+ "EXTRACT(YEAR FROM (NOW()-interval '2 day')) = EXTRACT(YEAR FROM date_emprunt) AND "
			+ "EXTRACT(MONTH FROM (NOW()-interval '2 day')) = EXTRACT(MONTH FROM date_emprunt) AND "
			+ "EXTRACT(DAY FROM (NOW()-interval '2 day')) = EXTRACT(DAY FROM date_emprunt)", nativeQuery = true)
	List<Emprunt> listForBatch();
}
