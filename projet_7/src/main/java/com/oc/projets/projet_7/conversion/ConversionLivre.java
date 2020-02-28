package com.oc.projets.projet_7.conversion;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.oc.projets.projet_7.dto.LivreDTO;
import com.oc.projets.projet_7.dto.LivreEmpruntDTO;
import com.oc.projets.projet_7.entity.Livre;

@Component
public class ConversionLivre {

	@Autowired
	private ModelMapper modelMapper;
	
	public ConversionLivre() {
		
	}
	
	public LivreEmpruntDTO convertToEmpruntDto(Livre livre) {
		LivreEmpruntDTO livreDTO = modelMapper.map(livre, LivreEmpruntDTO.class);
		return livreDTO;
	}
	
	public Livre convertToEntity(LivreEmpruntDTO livreDTO) {
		Livre livre = modelMapper.map(livreDTO, Livre.class);
		return livre;
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
