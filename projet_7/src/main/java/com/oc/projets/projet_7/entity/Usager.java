package com.oc.projets.projet_7.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "usager")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updateAt"},
		allowGetters = true)
public class Usager implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Veuillez saisr un nom.")
	@Size(min = 1, max = 20, message = "Le nom doit être compris entre 1 et 20 caractères.")
	private String nom;
	
	@NotBlank(message = "Veuillez saisr un prénom.")
	@Size(min = 1, max = 20, message = "Le prénom doit être compris entre 1 et 20 caractères.")
	private String prenom;
	
	@NotBlank
	@Column(unique = true)
	@Email(message = "Cette adresse mail n'est pas valide.")
	private String email;
	
	@NotBlank
	//@Size(min = 8, max = 16, message = "Le password doit être compris entre 8 et 16 caractères.")
	private String password;
	
	@NotBlank
	private String role = "USER";
	
//	@OneToMany(mappedBy = "usager", cascade = CascadeType.ALL, orphanRemoval = true)
//	private List<Emprunt> listEmprunts = new ArrayList<Emprunt>();

	public Usager() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Usager(String email, String password) {
		// TODO Auto-generated constructor stub
		this.email = email;
		this.password = password;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "Usager [id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", password="
				 + ", role=" + role + "]";
	}
	
//	public List<Emprunt> getListEmprunts() {
//		return listEmprunts;
//	}
//
//	public void setListEmprunts(List<Emprunt> listEmprunts) {
//		this.listEmprunts = listEmprunts;
//	}
//	
//	public void addEmprunt(Emprunt emprunt) {
//		this.listEmprunts.add(emprunt);
//	}
//	
//	public void deleteEmprunt(Emprunt emprunt) {
//		this.listEmprunts.remove(emprunt);
//	}
	
}
