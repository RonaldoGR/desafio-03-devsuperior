package com.example.desafio_03_devsuperior.services;

import com.example.desafio_03_devsuperior.dto.ClientDTO;
import com.example.desafio_03_devsuperior.entities.Client;
import com.example.desafio_03_devsuperior.repository.ClientRepository;
import com.example.desafio_03_devsuperior.services.exceptions.ClientNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Transactional(readOnly = true)
    public ClientDTO findById(Long id) {
        Client client = clientRepository.findById(id).
                orElseThrow(() -> new ClientNotFoundException("Client not found"));
        return new ClientDTO(client);
    }

    @Transactional(readOnly = true)
    public Page<ClientDTO> findAll(Pageable page) {
        Page<Client> clients = clientRepository.findAll(page);
        if (!clients.isEmpty()) {
            return clients.map(ClientDTO::new);
        }
        return null;
    }

    @Transactional
    public ClientDTO save(ClientDTO clientDTO) {
        Client client = new Client();
        copyDtoToEntity(client, clientDTO);
        clientRepository.save(client);
        return new ClientDTO(client);
    }

    @Transactional
    public ClientDTO update(Long id, ClientDTO clientUpdated) {
        Client clientReference = clientRepository.getReferenceById(id);
        copyDtoToEntity(clientReference, clientUpdated);
        clientRepository.save(clientReference);
        return new ClientDTO(clientReference);
    }

    @Transactional
    public void delete(Long id) {
        if (!clientRepository.existsById(id)) {
            throw new ClientNotFoundException("Client not found with id " + id);
        }

        clientRepository.deleteById(id);
    }

    private void copyDtoToEntity(Client clientReference, ClientDTO clientUpdated) {
        clientReference.setName(clientUpdated.getName());
        clientReference.setCpf(clientUpdated.getCpf());
        clientReference.setIncome(clientUpdated.getIncome());
        clientReference.setBirthDate(clientUpdated.getBirthDate());
        clientReference.setChildren(clientUpdated.getChildren());
    }


}
