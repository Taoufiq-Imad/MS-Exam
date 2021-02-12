package com.example.demo.dto;

import com.example.demo.entities.Compte;
import org.springframework.stereotype.Service;

@Service
public class CompteMapper {

    public Compte toEntity(DtoCompte dtoCompte) {
        Compte compte = new Compte();
        compte.setCode(null);
        compte.setSolde(dtoCompte.getSolde());
        compte.setEtat(dtoCompte.getEtat());
        compte.setClientID(dtoCompte.getClient());
        compte.setDateCreation(dtoCompte.getDateCreation());
        compte.setType(dtoCompte.getType());
        return compte;
    }
}
