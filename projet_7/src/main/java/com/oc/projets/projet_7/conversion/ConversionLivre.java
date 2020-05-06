package com.oc.projets.projet_7.conversion;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.oc.projets.projet_7.dto.LivreCreationDTO;
import com.oc.projets.projet_7.dto.LivreDTO;
import com.oc.projets.projet_7.dto.LivreEmpruntDTO;
import com.oc.projets.projet_7.entity.Exemplaire;
import com.oc.projets.projet_7.entity.Livre;

@Component
public class ConversionLivre {

	@Autowired
	private ModelMapper modelMapper;
	
	public ConversionLivre() {
		
	}
	
	public LivreDTO convertToDTO(Livre livre) {
		LivreDTO livreDTO = modelMapper.map(livre, LivreDTO.class);
		return livreDTO;
	}
	
	public Livre convertToEntity(LivreDTO livreDTO) {
		Livre livre = modelMapper.map(livreDTO, Livre.class);
		return livre;
	}
	
//	public LivreDTO convertToDto(Exemplaire livre) {
//		LivreDTO livreDTO = modelMapper.map(livre, LivreDTO.class);
//		return livreDTO;
//	}
//	
//	public Exemplaire convertToEntity(LivreDTO livreDTO) {
//		Exemplaire livre = modelMapper.map(livreDTO, Exemplaire.class);
//		return livre;
//	}
//	
//	public Exemplaire convertToEntity(LivreCreationDTO livreCreationDTO) {
//		Exemplaire livre = modelMapper.map(livreCreationDTO, Exemplaire.class);
//		return livre;
//	}
}
