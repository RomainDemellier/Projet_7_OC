package com.oc.projets.projet_7.restController;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
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
import com.oc.projets.projet_7.service.LivreService;
import com.oc.projets.projet_7.service.UsagerService;
import com.oc.projets.projet_7.wrapper.RequestEmprunterWrapper;

@RestController
@RequestMapping("/api")
public class EmpruntController {

	@Autowired
	private UsagerService usagerService;
	
	@Autowired
	private LivreService livreService;
	
	/* Action d'emprunter un livre pour un usager avec l'id de l'usager et l'id du livre 
	 * On décrémente le nombre d'exemplaires du livre et on save le livre
	 * */
	@PostMapping("/emprunt/create")
	public EmpruntDTO emprunter(@RequestBody RequestEmprunterWrapper req) {
		
		Long usagerId = req.getUsagerId();
		Long livreId = req.getLivreId();
		
		Usager usager = this.usagerService.findById(usagerId);
		Livre livre = this.livreService.findById(livreId);
//		Usager usager = this.usagerService.findById(usagerId);
//		
//		Livre livre = this.livreService.findById(livreId);
//		
		EmpruntDTO empruntDTO = null;
		
		try{
			empruntDTO = this.usagerService.emprunter(usager, livre);
			this.livreService.editLivre(livre);
			return empruntDTO;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return empruntDTO;
//				
//		return usager;
		
//		UsagerGetDTO usagerGetDTO = usagerLivreWrapper.getUsagerGetDTO();
//		LivreDTO livreDTO = usagerLivreWrapper.getLivreDTO();
//		
//		System.out.println(usagerGetDTO.getNom());
//		System.out.println(livreDTO.getTitre());
//		
//		try{
//			EmpruntDTO empruntDTO = this.usagerService.emprunter(usagerGetDTO, livreDTO);
//			return empruntDTO;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		return null;
	}
	
	/* Action de rendre un livre pour un usager avec l'id de l'usager et l'id du livre
	 */
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
