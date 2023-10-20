package com.softroute.clientmicroservice.controller;

import com.softroute.clientmicroservice.entity.ClientEntity;
import com.softroute.clientmicroservice.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/client")
public class ClientController {
    @Autowired
    ClientService clientService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ClientEntity>> getAll() {
        List<ClientEntity> clients = clientService.getAll();
        if(clients.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(clients);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClientEntity> save(@RequestBody ClientEntity clientEntity) {
        ClientEntity client = clientService.getByDni(clientEntity.getDni());
        if(client != null) {
            return ResponseEntity.ok(client);
        }
        ClientEntity clientNew = clientService.save(clientEntity);
        return ResponseEntity.ok(clientNew);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClientEntity> getById(@PathVariable("id") Long id) {
        ClientEntity client = clientService.getById(id);
        if(client == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(client);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClientEntity> update(@PathVariable("id") Long id, @RequestBody ClientEntity clientEntity) {
        ClientEntity client = clientService.getById(id);
        if(client == null) {
            return ResponseEntity.notFound().build();
        }
        ClientEntity clientUpdated = clientService.update(clientEntity);
        clientUpdated.setId(client.getId());
        if(clientUpdated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(clientUpdated);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<ClientEntity> delete(@PathVariable("id") Long id) {
        ClientEntity client = clientService.getById(id);
        if(client == null) {
            return ResponseEntity.notFound().build();
        }
        clientService.delete(id);
        return ResponseEntity.ok(client);
    }

    @GetMapping(value = "/dni/{dni}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClientEntity> getByDni(@PathVariable("dni") String dni) {
        ClientEntity client = clientService.getByDni(dni);
        if(client == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(client);
    }
}
