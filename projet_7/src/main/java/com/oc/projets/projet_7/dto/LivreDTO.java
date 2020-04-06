package com.oc.projets.projet_7.dto;

public class LivreDTO {

	private Long id;
	
	private String titre;
	
	private AuteurDTO auteur;
	
	private String genre;
	
	private int nbreExemplaires;
	
	private String fullNameAuteur;
	
	public LivreDTO() {
		
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

	public AuteurDTO getAuteur() {
		return auteur;
	}

	public void setAuteur(AuteurDTO auteur) {
		this.auteur = auteur;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public int getNbreExemplaires() {
		return nbreExemplaires;
	}

	public void setNbreExemplaires(int nbreExemplaires) {
		this.nbreExemplaires = nbreExemplaires;
	}

	public String getFullNameAuteur() {
		return fullNameAuteur;
	}

	public void setFullNameAuteur(String fullNameAuteur) {
		this.fullNameAuteur = fullNameAuteur;
	}
}
