package com.example.demo.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.demo.dto.KafkaOperation;
import com.example.demo.dto.OperationMapper;
import com.example.demo.exception.SoldeInsuffisantException;
import org.springframework.data.domain.Page;

import com.example.demo.entities.Compte;
import com.example.demo.entities.Operation;
import com.example.demo.repository.CompteRepository;
import com.example.demo.repository.OperationRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class IServiceImplementation implements IService{

	private CompteRepository compteRepository;
	private OperationRepository operationRepository;
	private ClientService clientService;
	private KafkaTemplate<Long, KafkaOperation> kafkaTemplate;
	private OperationMapper operationMapper;

	public IServiceImplementation(CompteRepository compteRepository, OperationRepository operationRepository, ClientService clientService, KafkaTemplate<Long, KafkaOperation> kafkaTemplate, OperationMapper operationMapper) {
		this.compteRepository = compteRepository;
		this.operationRepository = operationRepository;
		this.clientService = clientService;
		this.kafkaTemplate=kafkaTemplate;
		this.operationMapper = operationMapper;
	}

	@Override
	public Compte addCompte(Compte c) {
		return compteRepository.save(c);
	}

	@Override
	public Operation versement(double montant, Long code) {
		Compte c=compteRepository.findById(code).get();
		Operation o=creatOperation(new Date(),montant,"CREDIT",c);
		c.setSolde(c.getSolde()+montant);
		compteRepository.save(c);
		return o;
	}

	@Override
	public Operation retrait(double montant, Long code) throws Exception {
		Compte c=compteRepository.findById(code).get();
		if (c.getSolde()<montant) throw new SoldeInsuffisantException("solde insuffisant");
		Operation o=creatOperation(new Date(),montant,"DEBIT",c);
		c.setSolde(c.getSolde()-montant);
		compteRepository.save(c);
		return o;
	}

	@Override
	public Map<String,Operation> virement(double montant, Long codeS, Long codeD) throws Exception {
		Map<String,Operation> map=new HashMap<>();
		Operation o1,o2;
		o1=retrait(montant,codeS);
		o2=versement(montant,codeD);
		map.put("retrait",o1);
		map.put("versement",o2);
		return map;
	}

	@Override
	public List<Operation> consulter(Long code, int page, int size) {
		Page<Operation> operations=operationRepository.findByCompte_Code(code, PageRequest.of(page, size));
		return operations.getContent();
	}

	@Override
	public Compte consulterCompte(Long code) {
		Compte c=compteRepository.findById(code).get();
		c.setClient(clientService.findClientById(c.getClientID()));
		return c;
	}

	@Override
	public void changeEtat(Long code, String etat) {
		Compte c=compteRepository.findById(code).get();
		c.setEtat(etat);
		compteRepository.save(c);
	}
	private Operation creatOperation(Date dateOperation, double montant, String type,Compte compte){
		Operation o=new Operation();
		o.setCompte(compte);
		o.setDateOperation(dateOperation);
		o.setType(type);
		o.setMontant(montant);
		operationRepository.save(o);
		kafkaTemplate.send("comptes",o.getNumero(),operationMapper.toKafka(o));
		return o;
	}

}
