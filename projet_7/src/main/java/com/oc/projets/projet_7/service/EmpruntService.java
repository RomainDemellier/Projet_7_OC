package com.oc.projets.projet_7.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oc.projets.projet_7.entity.Emprunt;
import com.oc.projets.projet_7.exception.ResourceNotFoundException;
import com.oc.projets.projet_7.repository.EmpruntRepository;

@Service
public class EmpruntService {

	@Autowired
	private EmpruntRepository empruntRepository;
	
	public Emprunt findById(Long id) {
		return this.empruntRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Emprunt", "id", id));
	}
}
