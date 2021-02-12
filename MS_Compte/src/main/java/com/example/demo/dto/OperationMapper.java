package com.example.demo.dto;

import com.example.demo.entities.Operation;
import org.springframework.stereotype.Service;

@Service
public class OperationMapper {

    public KafkaOperation toKafka(Operation operation) {
        KafkaOperation kafkaOperation = new KafkaOperation();
        kafkaOperation.setCompte(operation.getCompte().getCode());
        kafkaOperation.setDateOperation(operation.getDateOperation());
        kafkaOperation.setNumero(operation.getNumero());
        kafkaOperation.setMontant(operation.getMontant());
        kafkaOperation.setType(operation.getType());
        return kafkaOperation;
    }
}
