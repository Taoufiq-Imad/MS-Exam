package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoCompte {
    private double solde;
    private Date dateCreation;
    private String type;
    private String etat;
    private long client;
}
