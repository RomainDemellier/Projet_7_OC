package com.oc.projets.projet_7.restController;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oc.projets.projet_7.conversion.ConversionEmprunt;
import com.oc.projets.projet_7.conversion.ConversionUsager;
import com.oc.projets.projet_7.dto.EmpruntDTO;
import com.oc.projets.projet_7.dto.UsagerDTO;
import com.oc.projets.projet_7.dto.UsagerGetDTO;
import com.oc.projets.projet_7.entity.Emprunt;
import com.oc.projets.projet_7.entity.Usager;
import com.oc.projets.projet_7.service.EmpruntService;
import com.oc.projets.projet_7.service.UsagerService;

@RestController
@RequestMapping("/api")
public class UsagerRestController {

	@Autowired
	private UsagerService usagerService;
	
	@Autowired
	private EmpruntService empruntService;
	
	@Autowired
	private ConversionUsager conversionUsager;
	
	@Autowired
	private ConversionEmprunt conversionEmprunt;
	
	/* Retourne la liste de tous les usagers */
	@GetMapping("/allUsagers")
	public List<UsagerGetDTO> getAllUsagers(){
		List<Usager> usagers = this.usagerService.getAll();
		return usagers.stream().map(usager -> this.conversionUsager.convertToGetDTO(usager)).collect(Collectors.toList());
	}
	
	/* Retourne un usager en fonction de son id */
	@GetMapping("/usager/{id}")
	public UsagerGetDTO findById(@PathVariable(value = "id") Long usagerId) {
		Usager usager = this.usagerService.findById(usagerId);
		return this.conversionUsager.convertToGetDTO(usager);
	}
	
	/* Créer un usager */
	@PostMapping("/usager")
	public UsagerDTO createUsager(@RequestBody UsagerDTO usagerDTO) {
		Usager usager = this.conversionUsager.convertToEntity(usagerDTO);
		try{
			usager = this.usagerService.createUsager(usager);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return this.conversionUsager.convertToDto(usager);
	}
	
	/* Retourne la liste des emprunts d'un usager avec l'id de l'usager */
	@GetMapping("/emprunts/{id}")
	public List<EmpruntDTO> getEmprunts(@PathVariable(value = "id") Long usagerId){
		Usager usager = this.usagerService.findById(usagerId);
		return usager.getListEmprunts().stream().map(emprunt -> this.conversionEmprunt.convertToDto(emprunt)).collect(Collectors.toList());
	}
	
	/* Retourne un emprunt en fonction de son id */
	@GetMapping("/emprunt/{id}")
	public EmpruntDTO getEmprunt(@PathVariable(value = "id") Long empruntId) {
		
		Emprunt emprunt = this.empruntService.findById(empruntId);
		return this.conversionEmprunt.convertToDto(emprunt);
		//return this.empruntService.findById(empruntId);
	}
	
	@GetMapping("/authenticated/hello")
	public String authenticatedHello() {
		return "Authenticated Hello World !";
	}
	
	@GetMapping("/hello")
	public String hello() {
		return "Non Authenticated Hello World !";
	}
}