package com.oc.projets.projet_7.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.oc.projets.projet_7.conversion.ConversionEmprunt;
import com.oc.projets.projet_7.conversion.ConversionLivre;
import com.oc.projets.projet_7.conversion.ConversionUsager;
import com.oc.projets.projet_7.dto.EmpruntDTO;
import com.oc.projets.projet_7.dto.LivreDTO;
import com.oc.projets.projet_7.dto.UsagerDTO;
import com.oc.projets.projet_7.dto.UsagerGetDTO;
import com.oc.projets.projet_7.entity.Emprunt;
import com.oc.projets.projet_7.entity.Livre;
import com.oc.projets.projet_7.entity.Usager;
import com.oc.projets.projet_7.exception.EmpruntException;
import com.oc.projets.projet_7.exception.PasswordException;
import com.oc.projets.projet_7.exception.ResourceNotFoundException;
import com.oc.projets.projet_7.repository.EmpruntRepository;
import com.oc.projets.projet_7.repository.UsagerRepository;


@Service
public class UsagerService {

	@Autowired
	private UsagerRepository usagerRepository;
	
	@Autowired
	private EmpruntRepository empruntRepository;
	
	@Autowired
	private ConversionUsager conversionUsager;
	
	@Autowired
	private ConversionLivre conversionLivre;
	
	@Autowired
	private ConversionEmprunt conversionEmprunt;
	
	public UsagerDTO createUsager(UsagerDTO usagerDTO) throws PasswordException {
		
		String password = usagerDTO.getPassword();
		String confirmPassword = usagerDTO.getConfirmPassword();
		
		if(password.length() < 8 || password.length() > 16) {
			throw new PasswordException("Le mot de passe doit comporter entre 8 et 16 caractères.");
		}
		
		if(!password.equals(confirmPassword)) {
			throw new PasswordException("Les deux mots de passe doivent être identiques.");
		}
		
		Usager usager = this.conversionUsager.convertToEntity(usagerDTO);
		
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		String encodePwd = bCryptPasswordEncoder.encode(usager.getPassword());
		System.out.println("encodePwd : " + encodePwd);
		usager.setPassword(encodePwd);
		
		usager.setRole("USER");
		
		this.usagerRepository.save(usager);
		
		return this.conversionUsager.convertToDto(usager);
	}
	
	public Usager editUsager(Usager usager) {
		return this.usagerRepository.save(usager);
	}
	
	public Usager findById(Long id) {
		return this.usagerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usager","id",id));
	}
	
	public Usager findByEmail(String email) {
		return this.usagerRepository.findByEmail(email);
	}
	
	public UsagerGetDTO getUsager(Long id) {
		Usager usager = this.findById(id);
		return this.conversionUsager.convertToGetDTO(usager);
	}
	
	public List<UsagerGetDTO> getAll(){
		List<Usager> usagers = this.usagerRepository.findAll();
		return usagers.stream().map(usager -> this.conversionUsager.convertToGetDTO(usager)).collect(Collectors.toList());
	}
	
	public UsagerDTO getUsagerDTOConnecte() {
		return this.conversionUsager.convertToDto(this.authentification());
	}
	
//	public List<EmpruntDTO> getEmprunts(){
//		Usager usager = this.authentification();
//		List<Emprunt> emprunts = usager.getListEmprunts();
//		return emprunts.stream().map(emprunt -> this.conversionEmprunt.convertToDto(emprunt)).collect(Collectors.toList());
//	}
	
//	public EmpruntDTO emprunter(EmpruntDTO empruntDTO) {
//		Usager usager = this.authentification();
//		Emprunt emprunt = this.conversionEmprunt.convertToEntity(empruntDTO);
//		Date date = new Date();
//		emprunt.setDateEmprunt(date);
//		usager.addEmprunt(emprunt);
//		this.editUsager(usager);
//		return this.conversionEmprunt.convertToDto(emprunt);
//	}
	
//	public void dejaEnpossession(EmpruntDTO empruntDTO) throws EmpruntException {
//		Usager usager = this.authentification();
//		Long livreId = empruntDTO.getLivre().getId();
//		List<Emprunt> emprunts = usager.getListEmprunts();
//		
//		for(int i = 0;i < emprunts.size();i++) {
//			if(emprunts.get(i).getLivre().getId().equals(livreId)) {
//				throw new EmpruntException("Vous êtes déjà en possession de ce livre.");
//			}
//		}
//	}
	
    public Usager authentification() {
    	
		UserDetails user =  (UserDetails) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		
		Usager usager = this.findByEmail(user.getUsername());
		return usager;
    }
	
//	public List<EmpruntDTO> getEmprunts(Long id){
//		Usager usager = this.findById(id);
//	}
	
//	public EmpruntDTO emprunter(Usager usager, Livre livre) throws EmpruntException {
//		
//		
//		if(livre.getNbreExemplaires() <= 0) {
//			throw new EmpruntException("Ce livre n'est pas disponible pour le moment.");
//		}
//		
//		List<Emprunt> emprunts = usager.getListEmprunts();
//		
//		for(int i = 0;i < emprunts.size();i++) {
//			if(emprunts.get(i).getLivre().getId().equals(livre.getId())) {
//				throw new EmpruntException("Vous avez déjà emprunter ce livre.");
//			}
//		}
//		
//		livre.setNbreExemplaires(livre.getNbreExemplaires() - 1);
//		
//		Emprunt emprunt = new Emprunt();
//		emprunt.setLivre(livre);
//		
//		Date date = new Date();
//		emprunt.setDateEmprunt(date);
//		
//		usager.addEmprunt(emprunt);
//		
//		this.editUsager(usager);
//		System.out.println("id emprunt : " + emprunt.getId());
//		
//		return this.conversionEmprunt.convertToDto(emprunt);
//	}
	
//	public Usager rendre(Usager usager, Livre livre) {
//		
//		List<Emprunt> emprunts = usager.getListEmprunts();
//		
//		for(int i = 0;i < emprunts.size();i++) {
//			if(emprunts.get(i).getLivre().getId().equals(livre.getId())) {
//				usager.deleteEmprunt(emprunts.get(i));
//				livre.setNbreExemplaires(livre.getNbreExemplaires() + 1);
//				break;
//			}
//		}
//		
//		this.editUsager(usager);
//		
//		return usager;
//	}
}
