package com.example.demo.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @Data @NoArgsConstructor @AllArgsConstructor
public class Operation {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long numero;
	private Date dateOperation;
	private double montant;
	private String type;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@ManyToOne
	private Compte compte;
}
