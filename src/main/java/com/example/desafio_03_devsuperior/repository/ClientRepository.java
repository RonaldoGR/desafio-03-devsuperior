package com.example.desafio_03_devsuperior.repository;

import com.example.desafio_03_devsuperior.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
}
