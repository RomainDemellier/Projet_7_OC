package com.oc.projets.projet_7.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
public class Usager {
	
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
	@Email
	private String email;
	
	@NotBlank
	private String password;
}
