package com.oc.projets.projet_7.service;

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
	private ConversionEmprunt conversionEmprunt;
	
	public Emprunt findById(Long id) {
		return this.empruntRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Emprunt", "id", id));
	}
	
	public EmpruntDTO create(EmpruntDTO empruntDTO){
		Emprunt emprunt = this.conversionEmprunt.convertToEntity(empruntDTO);
		
		Livre livre = emprunt.getLivre();
//		int nbreExemplaires = livre.getNbreExemplaires();
//		System.out.println("nbreExemplaires : " + nbreExemplaires);
//		if(nbreExemplaires <= 0) {
//			throw new EmpruntException("Ce livre n'est pas disponible pour le moment.");
//		}
		
//		Usager usager = emprunt.getUsager();
//		List<Emprunt> emprunts = this.empruntRepository.findByUsager(usager);
//		for(int i = 0;i < emprunts.size();i++) {
//			if(emprunt.getLivre().getId().equals(emprunts.get(i).getLivre().getId())) {
//				throw new EmpruntException("Vous avez déjà emprunter ce livre.");
//			}
//		}
		
//		livre.setNbreExemplaires(nbreExemplaires - 1);
		
		this.empruntRepository.save(emprunt);
		empruntDTO = this.conversionEmprunt.convertToDto(emprunt);
		return empruntDTO;
	}

	public void delete(Long empruntId) {
		this.empruntRepository.deleteById(empruntId);
	}
	
	public List<EmpruntDTO> getEmprunts(Usager usager){
		List<Emprunt> emprunts = this.empruntRepository.findByUsager(usager);
		return emprunts.stream().map(emprunt -> this.conversionEmprunt.convertToDto(emprunt)).collect(Collectors.toList());
	}
	
	public void dejaEnPossession(EmpruntDTO empruntDTO) throws EmpruntException {
		Emprunt emprunt = this.conversionEmprunt.convertToEntity(empruntDTO);
		Usager usager = emprunt.getUsager();
		List<Emprunt> emprunts = this.empruntRepository.findByUsager(usager);
		for(int i = 0;i < emprunts.size();i++) {
			if(emprunt.getLivre().getId().equals(emprunts.get(i).getLivre().getId())) {
				throw new EmpruntException("Vous avez déjà emprunté ce livre.");
			}
		}
	}
}
