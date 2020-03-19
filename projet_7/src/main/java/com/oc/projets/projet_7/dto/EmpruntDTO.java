package com.oc.projets.projet_7.dto;

import java.io.Serializable;
import java.util.Date;

public class EmpruntDTO implements Serializable {

	private Long id;
	
	private LivreDTO livre;
	
	private UsagerGetDTO usager;
	
	private Date dateEmprunt;
	
	private Date dateRetour;
	
	private Boolean prolonge;
	
	private Boolean actif;
	
	public EmpruntDTO() {
		
	}	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LivreDTO getLivre() {
		return livre;
	}

	public void setLivre(LivreDTO livre) {
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
	

	public Boolean getActif() {
		return actif;
	}

	public void setActif(Boolean actif) {
		this.actif = actif;
	}

	public Date getDateRetour() {
		return dateRetour;
	}

	public Boolean getProlonge() {
		return prolonge;
	}

	public void setDateRetour(Date dateRetour) {
		this.dateRetour = dateRetour;
	}

	public void setProlonge(Boolean prolonge) {
		this.prolonge = prolonge;
	}

	@Override
	public String toString() {
		return "EmpruntDTO [id=" + id + ", livre=" + livre + ", dateEmprunt=" + dateEmprunt + ", actif=" + actif + "]";
	}
}
