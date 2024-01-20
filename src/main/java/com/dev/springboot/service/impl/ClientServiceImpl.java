package com.dev.springboot.service.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dev.springboot.exception.ClientNotFoundException; // Adjust the exception class
import com.dev.springboot.model.Client;
import com.dev.springboot.repo.ClientRepository;
import com.dev.springboot.service.IClientService;

@Service
public class ClientServiceImpl implements IClientService {

    @Autowired
    private ClientRepository repo;

    @Override
    public Client saveClient(Client client) {
        return repo.save(client);
    }

    @Override
    public List<Client> getAllClients() {
        return repo.findAll();
    }

    @Override
    public Client getClientById(Long num_client) {
        Optional<Client> opt = repo.findById(num_client);
        if(opt.isPresent()) {
            return opt.get();
        } else {
            throw new ClientNotFoundException("Client with Id : " + num_client + " Not Found");
        }
    }

    @Override
    public void deleteClientById(Long num_client) {
        repo.delete(getClientById(num_client));
    }

    @Override
    public void updateClient(Client client) {
        repo.save(client);
    }

    // Additional methods specific to the Client service can be added here
}
