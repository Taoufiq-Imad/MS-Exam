package com.example.demo.controller;

import com.example.demo.dto.CompteMapper;
import com.example.demo.dto.DtoCompte;
import com.example.demo.dto.RetraitVersement;
import com.example.demo.dto.Virement;
import com.example.demo.entities.Operation;
import com.example.demo.service.IService;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entities.Compte;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;


@RestController
public class CompteController {
	private IService service;
	private CompteMapper compteMapper;

	public CompteController(IService service, CompteMapper compteMapper) {
		this.service = service;
		this.compteMapper = compteMapper;
	}
	@PostMapping("/comptes")
	public Compte saveCompte(@RequestBody @Valid DtoCompte c){
		return service.addCompte(compteMapper.toEntity(c));
	}
	@PostMapping("/comtes/versement")
	public Operation versement(@RequestBody RetraitVersement versement){
		return service.versement(versement.getMontant(),versement.getCode());
	}
	@PostMapping("/comtes/retrait")
	public Operation retrait(@RequestBody RetraitVersement retrait) throws Exception {
		return service.retrait(retrait.getMontant(),retrait.getCode());
	}
	@PostMapping("/comtes/virement")
	public Map<String,Operation> versement(@RequestBody Virement virement) throws Exception {
		return service.virement(virement.getM(),virement.getCodeS(),virement.getCodeD());
	}
	@GetMapping("/comptes/{id}/operations")
	public List<Operation> getOperations(@PathVariable(name = "id") Long id,@RequestParam(name = "page", defaultValue = "0") int p,
										 @RequestParam(name = "size", defaultValue = "4") int s){
		return service.consulter(id,p,s);
	}
	@GetMapping("/comptes/{id}")
	public Compte getCompte(@PathVariable(name="id") Long id) {
		return service.consulterCompte(id);
	}
	@PostMapping("/comptes/etat/{id}")
	public void changeEtat(@PathVariable(name = "id") Long id,@RequestBody String etat){
		service.changeEtat(id,etat);
	}
}
