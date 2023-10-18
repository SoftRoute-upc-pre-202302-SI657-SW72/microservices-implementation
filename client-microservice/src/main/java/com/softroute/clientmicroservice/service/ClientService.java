package com.softroute.clientmicroservice.service;

import com.softroute.clientmicroservice.entity.ClientEntity;
import com.softroute.clientmicroservice.repository.IClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    IClientRepository clientRepository;

    public List<ClientEntity> getAll() throws Exception {
        try {
            return clientRepository.findAll();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public ClientEntity save(ClientEntity clientEntity) throws Exception {
        try {
            return clientRepository.save(clientEntity);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public ClientEntity getById(Long id) throws Exception {
        try {
            return clientRepository.findById(id).get();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public ClientEntity update(ClientEntity clientEntity) throws Exception {
        try {
            return clientRepository.save(clientEntity);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
