package com.oc.projets.projet_7.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.oc.projets.projet_7.entity.Usager;
import com.oc.projets.projet_7.repository.UsagerRepository;

@Service
public class UsagerConnecteService {

	//@Autowired
	private Usager usager = null;
	
	@Autowired
	private UsagerRepository usagerRepository;
	
	public Usager getUsagerConnecte() {
		
		if(usager == null) {
			this.usager = this.authentification();
		}
		return usager;
	}
	
    public Usager authentification() {
    	
		UserDetails user =  (UserDetails) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		
		Usager usager = this.usagerRepository.findByEmail(user.getUsername());
		return usager;
    }
}
