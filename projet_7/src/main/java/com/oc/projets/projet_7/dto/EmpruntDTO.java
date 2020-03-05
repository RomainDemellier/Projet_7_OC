package com.oc.projets.projet_7.dto;

import java.io.Serializable;
import java.util.Date;

public class EmpruntDTO implements Serializable {

	private Long id;
	
	private LivreEmpruntDTO livre;
	
	private UsagerGetDTO usager;
	
	private Date dateEmprunt;
	
	public EmpruntDTO() {
		
	}	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LivreEmpruntDTO getLivre() {
		return livre;
	}

	public void setLivre(LivreEmpruntDTO livre) {
		this.livre = livre;
	}

	public UsagerGetDTO getUsager() {
		return usager;
	}

	public void setUsager(UsagerGetDTO usagerGetDTO) {
		this.usager = usagerGetDTO;
	}

	public Date getDateEmprunt() {
		return dateEmprunt;
	}

	public void setDateEmprunt(Date dateEmprunt) {
		this.dateEmprunt = dateEmprunt;
	}

	@Override
	public String toString() {
		return "EmpruntDTO [id=" + id + ", livre=" + livre + ", usager=" + usager + ", dateEmprunt=" + dateEmprunt
				+ "]";
	}
	
}
