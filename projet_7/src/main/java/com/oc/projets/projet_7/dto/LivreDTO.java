package com.oc.projets.projet_7.dto;

import java.io.Serializable;

public class LivreDTO implements Serializable {

	private Long id;
	
	private String titre;

	public LivreDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}
}
