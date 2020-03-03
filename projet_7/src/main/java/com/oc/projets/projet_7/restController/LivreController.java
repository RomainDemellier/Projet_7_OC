package com.oc.projets.projet_7.restController;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oc.projets.projet_7.conversion.ConversionLivre;
import com.oc.projets.projet_7.dto.LivreDTO;
import com.oc.projets.projet_7.entity.Auteur;
import com.oc.projets.projet_7.entity.Livre;
import com.oc.projets.projet_7.service.AuteurService;
import com.oc.projets.projet_7.service.LivreService;

@RestController
@RequestMapping("/api")
public class LivreController {

	@Autowired
	private LivreService livreService;
	
	@Autowired
	private AuteurService auteurService;
	
	@Autowired
	private ConversionLivre conversionLivre;
	
	/* Retourne la liste de tous les livres */
	@GetMapping("/livre")
	public List<LivreDTO> getAll(){
		return this.livreService.getAllLivres();
	}
	
	/* Retourne un livre en fonction de son id */
	@GetMapping("/livre/{id}")
	public LivreDTO getById(@PathVariable(value = "id") Long livreId) {
//		Livre livre = this.livreService.findById(livreId);
//		return this.conversionLivre.convertToDto(livre);
		return this.livreService.getLivre(livreId);
	}
	
	/* Créer un livre */
	@PostMapping("/livre/create")
	public Livre createLivre(@RequestBody Livre livre) {
		return this.livreService.createLivre(livre);
	}
	
	/* Créer un auteur */
	@PostMapping("/auteur/create")
	public Auteur createAuteur(@RequestBody Auteur auteur) {
		return this.auteurService.createAuteur(auteur);
	}
}
