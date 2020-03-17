package com.oc.projets.projet_7.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.oc.projets.projet_7.conversion.ConversionUsager;
import com.oc.projets.projet_7.dto.UsagerGetDTO;
import com.oc.projets.projet_7.entity.Usager;
import com.oc.projets.projet_7.repository.UsagerRepository;

import net.bytebuddy.asm.Advice.This;

@Service
public class UsagerConnecteService {

	//@Autowired
	private Usager usager = null;
	
	@Autowired
	private UsagerRepository usagerRepository;
	
	@Autowired
	private ConversionUsager conversionUsager;
	
	public UsagerGetDTO getUsagerConnecte() {
		
		if(usager == null) {
			this.usager = this.authentification();
		}
		System.out.println(this.conversionUsager.convertToGetDTO(usager));
		return this.conversionUsager.convertToGetDTO(usager);
	}
	
    public Usager authentification() {
    	
		UserDetails user =  (UserDetails) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		
		Usager usager = this.usagerRepository.findByEmail(user.getUsername());
		return usager;
    }
}
