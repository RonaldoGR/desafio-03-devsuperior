package com.example.desafio_03_devsuperior.controllers;

import com.example.desafio_03_devsuperior.dto.ClientDTO;
import com.example.desafio_03_devsuperior.services.ClientService;
import com.example.desafio_03_devsuperior.services.exceptions.ClientNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ClientDTO> getClient(@PathVariable Long id) {
        return ResponseEntity.ok(clientService.findById(id));
    }

    @GetMapping
    public ResponseEntity<Page<ClientDTO>> findAll(Pageable page) {
        return ResponseEntity.ok(clientService.findAll(page));
    }

    @PostMapping
    public ResponseEntity<ClientDTO> save(@Valid @RequestBody ClientDTO clientDTO) {
        return ResponseEntity.ok(clientService.save(clientDTO));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ClientDTO> update(@PathVariable Long id, @Valid @RequestBody ClientDTO clientDTO) {
        try {
            return ResponseEntity.ok(clientService.update(id, clientDTO));
        } catch (EntityNotFoundException e) {
            throw new ClientNotFoundException("Client not found with id: " + id);
        }
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        clientService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
