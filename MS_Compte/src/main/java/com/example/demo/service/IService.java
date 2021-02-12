package com.example.demo.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.example.demo.entities.Compte;
import com.example.demo.entities.Operation;

public interface IService {

	Compte addCompte(Compte c);
	Operation versement(double montant,Long code);
	Operation retrait(double montant,Long code) throws Exception;
	Map<String,Operation> virement(double montant, Long codeS, Long codeD) throws Exception;
	List<Operation> consulter(Long code,int page,int size);
	Compte consulterCompte(Long code);
	void changeEtat(Long code,String etat);
	
}
