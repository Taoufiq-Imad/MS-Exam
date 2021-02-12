package com.example.demo.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;
@Data @AllArgsConstructor @NoArgsConstructor
public class KafkaOperation {
    private Long numero;
    private Date dateOperation;
    private double montant;
    private String type;
    private long compte;
}
