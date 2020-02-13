package com.oc.projets.projet_7.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oc.projets.projet_7.entity.Livre;
import com.oc.projets.projet_7.exception.ResourceNotFoundException;
import com.oc.projets.projet_7.repository.LivreRepository;

@Service
public class LivreService {

	@Autowired
	LivreRepository livreRepository;
	
	public Livre createLivre(Livre livre) {
		return this.livreRepository.save(livre);
	}
	
	public Livre editLivre(Livre livre) {
		return this.livreRepository.save(livre);
	}
	
	public List<Livre> getAllLivres(){
		return this.livreRepository.findAll();
	}
	
	public Livre findById(Long livreId) {
		return this.livreRepository.findById(livreId).orElseThrow(() -> new ResourceNotFoundException("Livre", "id", livreId));
				
	}
}
