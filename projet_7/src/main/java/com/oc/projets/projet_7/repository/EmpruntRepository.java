package com.oc.projets.projet_7.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oc.projets.projet_7.entity.Emprunt;
import com.oc.projets.projet_7.entity.Usager;

@Repository
public interface EmpruntRepository extends JpaRepository<Emprunt, Long> {

	List<Emprunt> findByUsager(Usager usager);
}
