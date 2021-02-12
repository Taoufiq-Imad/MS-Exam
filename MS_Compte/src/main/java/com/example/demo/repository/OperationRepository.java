package com.example.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.entities.Operation;


public interface OperationRepository extends JpaRepository<Operation, Long>{
    Page<Operation> findByCompte_Code(Long code, Pageable pageable);
}
