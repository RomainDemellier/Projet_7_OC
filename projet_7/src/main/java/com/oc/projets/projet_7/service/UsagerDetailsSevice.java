package com.oc.projets.projet_7.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.oc.projets.projet_7.entity.Usager;
import com.oc.projets.projet_7.repository.UsagerRepository;

@Service
public class UsagerDetailsSevice implements UserDetailsService {

	@Autowired
	private UsagerRepository usagerRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Usager usager = this.usagerRepository.findByEmail(email);
		
		GrantedAuthority authority = null;
		
		UserDetails userDetails = (UserDetails) new Usager(usager.getEmail(),usager.getPassword());
		
		return userDetails;
	}

}
