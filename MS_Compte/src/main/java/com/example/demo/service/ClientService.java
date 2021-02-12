package com.example.demo.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.entities.Client;

@FeignClient(name="client-service")
public interface ClientService {
	@GetMapping("/clients/{id}?projection=fullClient")
	Client findClientById(@PathVariable("id") Long id);
	@GetMapping("/clients?projection=fullClient")
	PagedModel<Client> findAll();
}
