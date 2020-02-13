package com.oc.projets.projet_7.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	
	
	@GetMapping("/allLivres")
	public List<Livre> getAll(){
		return this.livreService.getAllLivres();
	}
	
	@GetMapping("/livre/{id}")
	public Livre getById(@PathVariable(value = "id") Long livreId) {
		return this.livreService.findById(livreId);
	}
	
	@PostMapping("/livre")
	public Livre createLivre(@RequestBody Livre livre) {
		return this.livreService.createLivre(livre);
	}
	
	@PostMapping("/auteur")
	public Auteur createAuteur(@RequestBody Auteur auteur) {
		return this.auteurService.createAuteur(auteur);
	}
}
