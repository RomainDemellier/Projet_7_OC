package com.oc.projets.projet_7.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oc.projets.projet_7.conversion.ConversionAuteur;
import com.oc.projets.projet_7.dto.AuteurDTO;
import com.oc.projets.projet_7.entity.Auteur;
import com.oc.projets.projet_7.exception.AuteurException;
import com.oc.projets.projet_7.exception.ResourceNotFoundException;
import com.oc.projets.projet_7.repository.AuteurRepository;

@Service
public class AuteurService {

	@Autowired
	private AuteurRepository auteurRepository;
	
	@Autowired
	private ConversionAuteur conversionAuteur;
	
	public Auteur createAuteur(Auteur auteur) {
		return this.auteurRepository.save(auteur);
	}
	
	public AuteurDTO findById(Long id) {
		Auteur auteur = this.auteurRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Auteur", "id", id));
		return this.conversionAuteur.convertToDTO(auteur);
	}
	
	public List<AuteurDTO> findAll(){
		List<Auteur> auteurs = this.auteurRepository.findAll();
		return auteurs.stream().map(auteur -> this.conversionAuteur.convertToDTO(auteur)).collect(Collectors.toList());
	}
}
