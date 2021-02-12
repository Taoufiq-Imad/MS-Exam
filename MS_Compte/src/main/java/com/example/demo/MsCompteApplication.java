package com.example.demo;

import com.example.demo.entities.Compte;
import com.example.demo.entities.Operation;
import com.example.demo.repository.CompteRepository;
import com.example.demo.repository.OperationRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
@EnableFeignClients
public class MsCompteApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsCompteApplication.class, args);
	}
	@Bean
	CommandLineRunner start(CompteRepository compte, OperationRepository operation) {
		return args ->{
			compte.save(new Compte(null,10000, new Date(),"COURANT","ACTIVE",null,null,1));
			compte.save(new Compte(null,20000, new Date(),"EPARGNE","SUSPENDED",null,null,2));
			compte.save(new Compte(null,30000, new Date(),"EPARGNE","ACTIVE",null,null,3));
			compte.findAll().forEach(c->{
				for (int i=0;i<6;i++){
					operation.save(new Operation(null,new Date(),i*400,Math.random()>0.5?"DEBIT":"CREDIT",c));
				}
			});
		};
	}
}
