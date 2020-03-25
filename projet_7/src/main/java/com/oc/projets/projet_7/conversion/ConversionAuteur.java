package com.oc.projets.projet_7.conversion;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.oc.projets.projet_7.dto.AuteurDTO;
import com.oc.projets.projet_7.entity.Auteur;

@Component
public class ConversionAuteur {

	@Autowired
	private ModelMapper modelMapper;
	
	public ConversionAuteur() {
		
	}
	
	public AuteurDTO convertToDTO(Auteur auteur) {
		return modelMapper.map(auteur, AuteurDTO.class);
	}
	
	public Auteur convertToEntity(AuteurDTO auteurDTO) {
		return modelMapper.map(auteurDTO, Auteur.class);
	}
}
