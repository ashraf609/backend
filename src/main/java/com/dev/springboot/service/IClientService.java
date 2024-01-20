package com.dev.springboot.service;

import java.util.List;
import com.dev.springboot.model.Client;

public interface IClientService {

    Client saveClient(Client client);
    List<Client> getAllClients();
    Client getClientById(Long num_client);
    void deleteClientById(Long num_client);
    void updateClient(Client client);

    // Here, you can add any additional methods specific to Client operations.
}
