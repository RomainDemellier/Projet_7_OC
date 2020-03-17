package com.oc.projets.projet_7.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oc.projets.projet_7.conversion.ConversionLivre;
import com.oc.projets.projet_7.dto.LivreDTO;
import com.oc.projets.projet_7.entity.Livre;
import com.oc.projets.projet_7.exception.EmpruntException;
import com.oc.projets.projet_7.exception.ResourceNotFoundException;
import com.oc.projets.projet_7.repository.LivreRepository;

@Service
public class LivreService {

	@Autowired
	private LivreRepository livreRepository;
	
	@Autowired
	private ConversionLivre conversionLivre;
	
	public Livre createLivre(Livre livre) {
		return this.livreRepository.save(livre);
	}
	
	public Livre editLivre(Livre livre) {
		return this.livreRepository.save(livre);
	}
	
	public LivreDTO getLivre(Long id) {
		Livre livre = this.findById(id);
		return this.conversionLivre.convertToDto(livre);
	}
	
	public List<LivreDTO> getAllLivres(){
		List<Livre> livres = this.livreRepository.findAll();
		return livres.stream().map(livre -> this.conversionLivre.convertToDto(livre)).collect(Collectors.toList());
	}
	
	public Livre findById(Long livreId) {
		return this.livreRepository.findById(livreId).orElseThrow(() -> new ResourceNotFoundException("Livre", "id", livreId));
				
	}
	
	public void estDisponible(Long livreId) throws EmpruntException {
		Livre livre = this.findById(livreId);
		int nbreExemplaires = livre.getNbreExemplaires();
		System.out.println("nbreExemplaires : " + nbreExemplaires);
		if(nbreExemplaires <= 0) {
			throw new EmpruntException("Ce livre n'est pas disponible pour le moment.");
		} 
//		else {
//			livre.setNbreExemplaires(nbreExemplaires - 1);
//			this.editLivre(livre);
//		}
	}
	
	public void rendre(Livre livre) {
		//Livre livre = this.findById(livreId);
		int nbreExemplaires = livre.getNbreExemplaires();
		livre.setNbreExemplaires(nbreExemplaires + 1);
		this.editLivre(livre);
	}
}
