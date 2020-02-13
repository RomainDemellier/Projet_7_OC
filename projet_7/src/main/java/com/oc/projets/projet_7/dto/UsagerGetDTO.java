package com.oc.projets.projet_7.dto;

import java.io.Serializable;

public class UsagerGetDTO implements Serializable {

	private Long id;
	
	private String nom;
	
	private String prenom;
	
	private String email;

	public UsagerGetDTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}	
}
