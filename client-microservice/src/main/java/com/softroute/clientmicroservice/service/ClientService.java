package com.softroute.clientmicroservice.service;

import com.softroute.clientmicroservice.entity.ClientEntity;
import com.softroute.clientmicroservice.model.ShipmentEntity;
import com.softroute.clientmicroservice.repository.IClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    IClientRepository clientRepository;

    @Autowired
    RestTemplate restTemplate;

    public List<ClientEntity> getAll() {

        return clientRepository.findAll();
    }

    public ClientEntity save(ClientEntity clientEntity) {

        return clientRepository.save(clientEntity);
    }

    public ClientEntity getById(Long id) {

        return clientRepository.findById(id).orElse(null);
    }

    public ClientEntity update(ClientEntity clientEntity) {

        return clientRepository.save(clientEntity);
    }

    public void delete(Long id) {

        clientRepository.deleteById(id);
    }

        public ClientEntity getByDni(String dni) {

        return clientRepository.getByDni(dni);
    }

    public ClientEntity getByEmail(String email) {
        return clientRepository.getByEmail(email);
    }

    public ShipmentEntity getShipmentById(Long shipmentId) {
        String url = "http://localhost:8080/api/v1/shipment/getById/" + shipmentId;
        //Shipment shipment = responseEntity.getBody();
        return restTemplate.getForObject(url, ShipmentEntity.class);
    }


}
