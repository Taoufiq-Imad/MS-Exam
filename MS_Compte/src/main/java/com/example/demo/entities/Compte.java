package com.example.demo.entities;

import java.util.Collection;
import java.util.Date;
import java.util.Optional;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity @Data @NoArgsConstructor @AllArgsConstructor @ToString
public class Compte {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long code;
	private double solde;
	private Date dateCreation;
	private String type;
	private String etat;

	@OneToMany(mappedBy = "compte")
	private Collection<Operation> operations;
	@Transient
	private Client client;
	private long clientID;
}
