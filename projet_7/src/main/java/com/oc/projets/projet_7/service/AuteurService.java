package com.oc.projets.projet_7.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oc.projets.projet_7.entity.Auteur;
import com.oc.projets.projet_7.repository.AuteurRepository;

@Service
public class AuteurService {

	@Autowired
	private AuteurRepository auteurRepository;
	
	public Auteur createAuteur(Auteur auteur) {
		return this.auteurRepository.save(auteur);
	}
}
