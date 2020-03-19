package com.oc.projets.projet_7.service;

import java.util.Calendar;
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
	
//	@Autowired
//	private UsagerService usagerService;
	
	@Autowired
	private LivreService livreService;
	
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
		
		Date date = new Date();
		emprunt.setDateEmprunt(date);
		
		Date dateRetour = this.dateRetour(date, 28);
		emprunt.setDateRetour(dateRetour);
		
		emprunt.setProlonge(false);
		
		emprunt.setActif(true);
		Livre livre = this.livreService.findById(emprunt.getLivre().getId());
		livre.setNbreExemplaires(livre.getNbreExemplaires() - 1);
		this.livreService.editLivre(livre);
		emprunt.setLivre(livre);
		this.empruntRepository.save(emprunt);
		empruntDTO = this.conversionEmprunt.convertToDto(emprunt);
		return empruntDTO;
	}

	public EmpruntDTO delete(Emprunt emprunt) {
		emprunt.setActif(false);
		this.empruntRepository.save(emprunt);
		return this.conversionEmprunt.convertToDto(emprunt);
	}
	
	public EmpruntDTO prolonger(Long empruntId) throws EmpruntException {
		Emprunt emprunt = this.findById(empruntId);
		if(!emprunt.getProlonge()) {
			emprunt.setProlonge(true);
			emprunt.setDateRetour(this.dateRetour(emprunt.getDateEmprunt(), 56));
			this.empruntRepository.save(emprunt);
			return this.conversionEmprunt.convertToDto(emprunt);
		} else {
			throw new EmpruntException("Vous avez déjà prolongé une fois l'emprunt de ce livre.");
		}
	}
	
	public List<EmpruntDTO> getEmpruntsUsagerConnecte() {
		Usager usager = this.usagerConnecteService.authentification();
		List<Emprunt> emprunts = this.empruntRepository.findByUsagerAndActif(usager, true);
		return emprunts.stream().map(emprunt -> this.conversionEmprunt.convertToDto(emprunt)).collect(Collectors.toList());
	}
	
	public void dejaEnPossession(EmpruntDTO empruntDTO) throws EmpruntException {
		Usager usager = this.usagerConnecteService.authentification();
		List<Emprunt> emprunts = this.empruntRepository.findByUsagerAndActif(usager, true);
		for(int i = 0;i < emprunts.size();i++) {
			if(emprunts.get(i).getLivre().getId().equals(empruntDTO.getLivre().getId())) {
				throw new EmpruntException("Vous êtes déjà en possession de ce livre.");
			}
		}
	}
	
	private Date dateRetour(Date date, int nbreDays) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, nbreDays);
		
		return calendar.getTime();
	}
	
}
