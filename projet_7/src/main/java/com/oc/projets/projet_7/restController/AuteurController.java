package com.oc.projets.projet_7.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<Auteur> createAuteur(@RequestBody Auteur auteur) {
		return ResponseEntity.ok(this.auteurService.createAuteur(auteur));
	}
	
	@GetMapping("/auteur")
	public ResponseEntity<List<AuteurDTO>> getAll(){
		return ResponseEntity.ok(this.auteurService.findAll());
	}
	
	@GetMapping("/auteur/{id}")
	public ResponseEntity<AuteurDTO> getById(@PathVariable(value = "id") Long auteurId) {
		return ResponseEntity.ok(this.auteurService.findById(auteurId));
	}
}
