package com.example.demo.service;

import com.example.demo.dto.KafkaOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Random;
import java.util.function.Supplier;

@Service
public class SupplierService {
    private Long num=0L;

    @Bean
    public Supplier<KafkaOperation> opertionSupplier(){
        return ()-> new KafkaOperation(
                num,
                new Date(),
                Math.random()*1000,
                Math.random()>0.5?"DEBIT":"CREDIT",
                new Random().nextInt(3));
    }
}
