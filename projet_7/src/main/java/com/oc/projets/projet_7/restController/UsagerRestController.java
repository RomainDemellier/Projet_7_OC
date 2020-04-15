package com.oc.projets.projet_7.restController;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.method.P;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.oc.projets.projet_7.conversion.ConversionEmprunt;
import com.oc.projets.projet_7.conversion.ConversionUsager;
import com.oc.projets.projet_7.dto.EmpruntDTO;
import com.oc.projets.projet_7.dto.UsagerDTO;
import com.oc.projets.projet_7.dto.UsagerGetDTO;
import com.oc.projets.projet_7.entity.Emprunt;
import com.oc.projets.projet_7.entity.Usager;
import com.oc.projets.projet_7.service.EmpruntService;
import com.oc.projets.projet_7.service.UsagerConnecteService;
import com.oc.projets.projet_7.service.UsagerDetails;
import com.oc.projets.projet_7.service.UsagerService;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class UsagerRestController {

	@Autowired
	private UsagerService usagerService;
	
	@Autowired
	private UsagerConnecteService usagerConnecteService;
	
	@Autowired
	private EmpruntService empruntService;
	
	@Autowired
	private ConversionUsager conversionUsager;
	
	@Autowired
	private ConversionEmprunt conversionEmprunt;
	
	/* Retourne la liste de tous les usagers */
//	@GetMapping("/usager")
//	public List<UsagerGetDTO> getAllUsagers(){
//		return this.usagerService.getAll();
//	}
	
	@GetMapping("/usager")
	@PreAuthorize("hasAuthority('ADMIN')")
	public List<UsagerGetDTO> getAllUsagers(){
		return this.usagerService.getAllIdNot(this.usagerConnecteService.getUsagerConnecte().getId());
	}
	
	/* Retourne un usager en fonction de son id */
	@GetMapping("/usager/{id}")
	public UsagerGetDTO findById(@PathVariable(value = "id") Long usagerId) {
//		Usager usager = this.usagerService.findById(usagerId);
//		return this.conversionUsager.convertToGetDTO(usager);
		return this.usagerService.getUsager(usagerId);
	}
	
	/* Créer un usager */
	@PostMapping("/usager/create")
	public ResponseEntity<UsagerDTO> createUsager(@RequestBody UsagerDTO usagerDTO) {
		//Usager usager = this.conversionUsager.convertToEntity(usagerDTO);
		try{
			usagerDTO = this.usagerService.createUsager(usagerDTO);
			return ResponseEntity.ok(usagerDTO);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		//return this.conversionUsager.convertToDto(usager);
		return ResponseEntity.status(HttpStatus.CONFLICT).body(usagerDTO);
	}
	
	@PostMapping("/usager/createAdmin")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<UsagerDTO> createAdmin(@RequestBody UsagerDTO usagerDTO) {
		//Usager usager = this.conversionUsager.convertToEntity(usagerDTO);
		try{
			usagerDTO = this.usagerService.createAdmin(usagerDTO);
			return ResponseEntity.ok(usagerDTO);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		//return this.conversionUsager.convertToDto(usager);
		return ResponseEntity.status(HttpStatus.CONFLICT).body(usagerDTO);
	}
	
	@PutMapping("/usager/update/role")
	@PreAuthorize("hasAuthority('ADMIN')")
	public UsagerGetDTO editUsager(@RequestBody UsagerGetDTO usagerGetDTO) {
		return this.usagerService.editRoleUsager(usagerGetDTO);
	}
	
	@PutMapping("/usager/update/profil")
	@PreAuthorize("isAuthenticated()")
	public UsagerGetDTO editProfilUsager(@RequestBody UsagerGetDTO usagerGetDTO) {
		return this.usagerService.editProfilUsager(usagerGetDTO);
	}
	
	@GetMapping("/usager/emprunts")
	@PreAuthorize("isAuthenticated()")
	public List<EmpruntDTO> getEmprunts(){
		return this.empruntService.getEmpruntsUsagerConnecte();
	}
	
	@GetMapping("/usager/connecte")
	public UsagerGetDTO getUsagerConnecte() {
		return this.usagerConnecteService.getUsagerConnecte();
	}
}
