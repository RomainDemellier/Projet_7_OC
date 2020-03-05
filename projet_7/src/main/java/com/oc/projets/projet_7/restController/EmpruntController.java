package com.oc.projets.projet_7.restController;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.oc.projets.projet_7.dto.EmpruntDTO;
import com.oc.projets.projet_7.dto.LivreDTO;
import com.oc.projets.projet_7.dto.UsagerGetDTO;
import com.oc.projets.projet_7.entity.Emprunt;
import com.oc.projets.projet_7.entity.Livre;
import com.oc.projets.projet_7.entity.Usager;
import com.oc.projets.projet_7.service.EmpruntService;
import com.oc.projets.projet_7.service.LivreService;
import com.oc.projets.projet_7.service.UsagerService;

import net.bytebuddy.asm.Advice.This;

@RestController
@RequestMapping("/api")
public class EmpruntController {

	@Autowired
	private UsagerService usagerService;
	
	@Autowired
	private LivreService livreService;
	
	@Autowired
	private EmpruntService empruntService;
	
	/* Action d'emprunter un livre pour un usager avec l'id de l'usager et l'id du livre 
	 * On décrémente le nombre d'exemplaires du livre et on save le livre
	 * */
	@PostMapping("/emprunt/create")
	public EmpruntDTO emprunter(@RequestBody EmpruntDTO empruntDTO) {
		
		//System.out.println(this.empruntService.create(empruntDTO).toString());
		try {
			this.empruntService.dejaEnPossession(empruntDTO);
			this.livreService.estDisponible(empruntDTO.getLivre().getId());
			return this.empruntService.create(empruntDTO);
			//return this.empruntService.create(empruntDTO);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	
	@DeleteMapping("/emprunt/delete/{id}")
	public void rendre(@PathVariable(value = "id") Long empruntId) {
		Emprunt emprunt = this.empruntService.findById(empruntId);
		this.livreService.rendre(emprunt.getLivre());
		this.empruntService.delete(empruntId);
	}
	/* Action de rendre un livre pour un usager avec l'id de l'usager et l'id du livre
	 */
//	@DeleteMapping("/emprunt/usager/{usagerId}/livre/{livreId}")
//	public Usager rendre(@PathVariable(value = "usagerId") Long usagerId, @PathVariable(value = "livreId") Long livreId) {
//		Usager usager = this.usagerService.findById(usagerId);
//		
//		Livre livre = this.livreService.findById(livreId);
//		System.out.println("avant : " + livre.getNbreExemplaires());
//		
//		usager = this.usagerService.rendre(usager, livre);
//		System.out.println("après : " + livre.getNbreExemplaires());
//		this.livreService.editLivre(livre);
//		return usager;
//	}
}
