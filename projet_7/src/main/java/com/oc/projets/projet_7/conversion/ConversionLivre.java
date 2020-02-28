package com.oc.projets.projet_7.conversion;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.oc.projets.projet_7.dto.LivreDTO;
import com.oc.projets.projet_7.entity.Livre;

@Component
public class ConversionLivre {

	@Autowired
	private ModelMapper modelMapper;
	
	public ConversionLivre() {
		
	}
	
	public LivreDTO convertToDto(Livre livre) {
		LivreDTO livreDTO = modelMapper.map(livre, LivreDTO.class);
		return livreDTO;
	}
	
	public Livre convertToEntity(LivreDTO livreDTO) {
		Livre livre = modelMapper.map(livreDTO, Livre.class);
		return livre;
	}
}
