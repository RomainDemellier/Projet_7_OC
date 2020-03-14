package com.oc.projets.projet_7.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oc.projets.projet_7.conversion.ConversionEmprunt;
import com.oc.projets.projet_7.dto.EmpruntDTO;
import com.oc.projets.projet_7.entity.Emprunt;
import com.oc.projets.projet_7.entity.Livre;
import com.oc.projets.projet_7.entity.Usager;
import com.oc.projets.projet_7.exception.EmpruntException;
import com.oc.projets.projet_7.exception.ResourceNotFoundException;
import com.oc.projets.projet_7.repository.EmpruntRepository;

@Service
public class EmpruntService {

	@Autowired
	private EmpruntRepository empruntRepository;
	
	@Autowired
	private UsagerService usagerService;
	
	@Autowired
	private ConversionEmprunt conversionEmprunt;
	
	@Autowired
	private UsagerConnecteService usagerConnecteService;
	
	public Emprunt findById(Long id) {
		return this.empruntRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Emprunt", "id", id));
	}
	
	public EmpruntDTO getById(Long empruntId) {
		return this.conversionEmprunt.convertToDto(this.findById(empruntId));
	}
	
	public List<EmpruntDTO> getListEmprunts(){
		List<Emprunt> emprunts = this.empruntRepository.findAll();
		return emprunts.stream().map(emprunt -> this.conversionEmprunt.convertToDto(emprunt)).collect(Collectors.toList());
		//return emprunts.stream().map(emprunt -> this.conversionEmprunt.convertToDto(emprunt)).collect(Collectors.toList());
	}
	
	public EmpruntDTO create(EmpruntDTO empruntDTO){
		Emprunt emprunt = this.conversionEmprunt.convertToEntity(empruntDTO);
		Usager usager = this.usagerConnecteService.authentification();
		emprunt.setUsager(usager);
		emprunt.setDateEmprunt(new Date());
		emprunt.setActif(true);
		this.empruntRepository.save(emprunt);
		empruntDTO = this.conversionEmprunt.convertToDto(emprunt);
		return empruntDTO;
		
//		Livre livre = emprunt.getLivre();
//		int nbreExemplaires = livre.getNbreExemplaires();
//		System.out.println("nbreExemplaires : " + nbreExemplaires);
//		if(nbreExemplaires <= 0) {
//			throw new EmpruntException("Ce livre n'est pas disponible pour le moment.");
//		}
//		
//		Usager usager = emprunt.getUsager();
//		List<Emprunt> emprunts = this.empruntRepository.findByUsager(usager);
//		for(int i = 0;i < emprunts.size();i++) {
//			if(emprunt.getLivre().getId().equals(emprunts.get(i).getLivre().getId())) {
//				throw new EmpruntException("Vous avez déjà emprunter ce livre.");
//			}
//		}
//		
//		livre.setNbreExemplaires(nbreExemplaires - 1);
	}

	public void delete(Emprunt emprunt) {
		emprunt.setActif(false);
		this.empruntRepository.save(emprunt);
	}
	
	public List<EmpruntDTO> getEmpruntsUsagerConnecte() {
		Usager usager = this.usagerConnecteService.authentification();
		List<Emprunt> emprunts = this.empruntRepository.findByUsagerAndActif(usager, true);
		return emprunts.stream().map(emprunt -> this.conversionEmprunt.convertToDto(emprunt)).collect(Collectors.toList());
	}
	
	public void dejaEnPossession(EmpruntDTO empruntDTO) throws EmpruntException {
		Usager usager = this.usagerConnecteService.authentification();
		List<Emprunt> emprunts = this.empruntRepository.findByUsager(usager);
		for(int i = 0;i < emprunts.size();i++) {
			if(emprunts.get(i).getLivre().getId().equals(empruntDTO.getLivre().getId())) {
				throw new EmpruntException("Vous êtes déjà en possession de ce livre.");
			}
		}
	}
	
//	public List<EmpruntDTO> getEmprunts(Usager usager){
//		List<Emprunt> emprunts = this.empruntRepository.findByUsager(usager);
//		return emprunts.stream().map(emprunt -> this.conversionEmprunt.convertToDto(emprunt)).collect(Collectors.toList());
//	}
	
//	public void dejaEnPossession(EmpruntDTO empruntDTO) throws EmpruntException {
//		Emprunt emprunt = this.conversionEmprunt.convertToEntity(empruntDTO);
//		Usager usager = emprunt.getUsager();
//		List<Emprunt> emprunts = this.empruntRepository.findByUsager(usager);
//		for(int i = 0;i < emprunts.size();i++) {
//			if(emprunt.getLivre().getId().equals(emprunts.get(i).getLivre().getId())) {
//				throw new EmpruntException("Vous avez déjà emprunté ce livre.");
//			}
//		}
//	}
}
