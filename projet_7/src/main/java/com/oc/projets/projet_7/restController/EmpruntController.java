package com.oc.projets.projet_7.restController;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oc.projets.projet_7.entity.Emprunt;
import com.oc.projets.projet_7.entity.Livre;
import com.oc.projets.projet_7.entity.Usager;
import com.oc.projets.projet_7.service.LivreService;
import com.oc.projets.projet_7.service.UsagerService;

@RestController
@RequestMapping("/api")
public class EmpruntController {

	@Autowired
	private UsagerService usagerService;
	
	@Autowired
	private LivreService livreService;
	
	@PostMapping("/emprunt/usager/{usagerId}/livre/{livreId}")
	public Usager emprunter(@PathVariable(value = "usagerId") Long usagerId, @PathVariable(value = "livreId") Long livreId) {
		Usager usager = this.usagerService.findById(usagerId);
		
		Livre livre = this.livreService.findById(livreId);
		
		try{
			usager = this.usagerService.emprunter(usager, livre);
			this.livreService.editLivre(livre);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
				
		return usager;
	}
	
	@DeleteMapping("/emprunt/usager/{usagerId}/livre/{livreId}")
	public Usager rendre(@PathVariable(value = "usagerId") Long usagerId, @PathVariable(value = "livreId") Long livreId) {
		Usager usager = this.usagerService.findById(usagerId);
		
		Livre livre = this.livreService.findById(livreId);
		System.out.println("avant : " + livre.getNbreExemplaires());
		
		usager = this.usagerService.rendre(usager, livre);
		System.out.println("après : " + livre.getNbreExemplaires());
		this.livreService.editLivre(livre);
		return usager;
	}
}
