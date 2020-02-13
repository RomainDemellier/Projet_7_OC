package com.oc.projets.projet_7.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oc.projets.projet_7.entity.Livre;

@Repository
public interface LivreRepository extends JpaRepository<Livre, Long> {

}
