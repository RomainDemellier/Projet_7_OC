package com.oc.projets.projet_7.restController;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
import com.oc.projets.projet_7.repository.EmpruntRepository;
import com.oc.projets.projet_7.service.EmpruntService;
import com.oc.projets.projet_7.service.LivreService;
import com.oc.projets.projet_7.service.UsagerService;


@RestController
@CrossOrigin
@RequestMapping("/api")
public class EmpruntController {

	@Autowired
	private UsagerService usagerService;
	
	@Autowired
	private LivreService livreService;
	
	@Autowired
	private EmpruntService empruntService;
	
	@Autowired
	private EmpruntRepository empruntRepository;
	
	/* Action d'emprunter un livre pour un usager avec l'id de l'usager et l'id du livre 
	 * On décrémente le nombre d'exemplaires du livre et on save le livre
	 * */
	
	@GetMapping("/emprunt/{id}")
	public EmpruntDTO getEmprunt(@PathVariable(value = "id") Long empruntId) {
		return this.empruntService.getById(empruntId);
	}
	
	@GetMapping("/emprunt")
	public List<EmpruntDTO> getListEmprunts(){
		return this.empruntService.getListEmprunts();
	}
	
	@PostMapping("/emprunt/create")
	public ResponseEntity<EmpruntDTO> emprunter(@RequestBody EmpruntDTO empruntDTO) {
		
		//System.out.println(this.empruntService.create(empruntDTO).toString());
		try {
			this.empruntService.dejaEnPossession(empruntDTO);
			this.livreService.estDisponible(empruntDTO.getLivre().getId());
			return ResponseEntity.ok(this.empruntService.create(empruntDTO));
			//return this.empruntService.create(empruntDTO);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.CONFLICT).body(empruntDTO);
	}
	
	@PutMapping("/emprunt/prolonger/{id}")
	public ResponseEntity<EmpruntDTO> prolonger(@PathVariable(value = "id") Long empruntId) {
		try{
			return ResponseEntity.ok(this.empruntService.prolonger(empruntId));
		} catch(Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
	}
	
	@DeleteMapping("/emprunt/delete/{id}")
	public ResponseEntity<EmpruntDTO> rendre(@PathVariable(value = "id") Long empruntId) {
		Emprunt emprunt = this.empruntService.findById(empruntId);
		this.livreService.rendre(emprunt.getLivre());
		return ResponseEntity.ok(this.empruntService.delete(emprunt));
	}

	@GetMapping("/testDate")
	public void test() {
		LocalDate date = LocalDate.now();
		date = date.plusDays(-3);
		System.out.println(date);
//		List<Emprunt> emprunts = this.empruntRepository.findAll();
//		for(int i = 0;i < emprunts.size();i++) {
//			//System.out.println(emprunts.get(i).getDateEmprunt());
//			Emprunt emprunt  = emprunts.get(i);
//			date = emprunt.getDateEmprunt();
//			emprunt.setDateEmprunt(date);
//			this.empruntRepository.save(emprunt);
//		}
	}
}
