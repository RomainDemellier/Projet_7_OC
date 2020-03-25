package com.oc.projets.projet_7.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oc.projets.projet_7.dto.AuteurDTO;
import com.oc.projets.projet_7.entity.Auteur;
import com.oc.projets.projet_7.service.AuteurService;


@RestController
@CrossOrigin
@RequestMapping("api")
public class AuteurController {

	@Autowired
	private AuteurService auteurService;
	
	/* Créer un auteur */
	@PostMapping("/auteur/create")
	public Auteur createAuteur(@RequestBody Auteur auteur) {
		return this.auteurService.createAuteur(auteur);
	}
	
	@GetMapping("/auteur")
	public List<AuteurDTO> getAll(){
		return this.auteurService.findAll();
	}
	
	@GetMapping("/auteur/{id}")
	public AuteurDTO getById(@PathVariable(value = "id") Long auteurId) {
		return this.auteurService.findById(auteurId);
	}
}
