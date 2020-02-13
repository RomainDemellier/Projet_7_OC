package com.oc.projets.projet_7.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.oc.projets.projet_7.entity.Emprunt;
import com.oc.projets.projet_7.entity.Livre;
import com.oc.projets.projet_7.entity.Usager;
import com.oc.projets.projet_7.exception.EmpruntException;
import com.oc.projets.projet_7.exception.PasswordException;
import com.oc.projets.projet_7.exception.ResourceNotFoundException;
import com.oc.projets.projet_7.repository.UsagerRepository;


@Service
public class UsagerService {

	@Autowired
	private UsagerRepository usagerRepository;
	
	public Usager createUsager(Usager usager) throws PasswordException {
		
		String password = usager.getPassword();
		
		if(password.length() < 8 || password.length() > 16) {
			throw new PasswordException("Le mot de passe doit comporter entre 8 et 16 caractères.");
		}
		
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		String encodePwd = bCryptPasswordEncoder.encode(usager.getPassword());
		System.out.println("encodePwd : " + encodePwd);
		usager.setPassword(encodePwd);
		
		return this.usagerRepository.save(usager);
	}
	
	public Usager editUsager(Usager usager) {
		return this.usagerRepository.save(usager);
	}
	
	public Usager findById(Long id) {
		return this.usagerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usager","id",id));
	}
	
	public List<Usager> getAll(){
		return this.usagerRepository.findAll();
	}
	
	public Usager emprunter(Usager usager, Livre livre) throws EmpruntException {
		
		List<Emprunt> emprunts = usager.getListEmprunts();
		
		for(int i = 0;i < emprunts.size();i++) {
			if(emprunts.get(i).getLivre().getId().equals(livre.getId())) {
				throw new EmpruntException("Vous avez déjà emprunter ce livre.");
			}
		}
		
		livre.setNbreExemplaires(livre.getNbreExemplaires() - 1);
		
		Emprunt emprunt = new Emprunt();
		emprunt.setLivre(livre);
		
		Date date = new Date();
		emprunt.setDateEmprunt(date);
		
		usager.addEmprunt(emprunt);
		
		this.editUsager(usager);
		
		return usager;
	}
	
	public Usager rendre(Usager usager, Livre livre) {
		
		List<Emprunt> emprunts = usager.getListEmprunts();
		
		for(int i = 0;i < emprunts.size();i++) {
			if(emprunts.get(i).getLivre().getId().equals(livre.getId())) {
				usager.deleteEmprunt(emprunts.get(i));
				livre.setNbreExemplaires(livre.getNbreExemplaires() + 1);
				break;
			}
		}
		
		this.editUsager(usager);
		
		return usager;
	}
}
