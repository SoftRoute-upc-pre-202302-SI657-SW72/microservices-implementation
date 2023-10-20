package com.softroute.clientmicroservice.controller;

import com.softroute.clientmicroservice.entity.ClientEntity;
import com.softroute.clientmicroservice.service.ClientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/client")
@CrossOrigin(origins = "*")
@Api(value = "Client Microservice", description = "This API has a CRUD for Client")
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
    @ApiOperation(value = "Save a new Client", notes = "This method save a new Client")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Client created successfully"),
            @ApiResponse(code = 404, message = "Client not found"),
            @ApiResponse(code = 501, message = "Internal Server Error")
    })
    public ResponseEntity<ClientEntity> save(@Valid @RequestBody ClientEntity clientEntity) {
        try {
            // Verificar si ya existe un usuario con la misma información
            ClientEntity existingClient = clientService.getByDni(clientEntity.getDni()); // Suponiendo que el email sea un identificador único

            if (existingClient != null) {
                // El usuario ya existe en la base de datos, puedes devolver un error o tomar la acción apropiada.
                return ResponseEntity.status(HttpStatus.CONFLICT).body(existingClient); // HttpStatus.CONFLICT indica un conflicto (usuario ya registrado).
            } else {
                // El usuario no existe, así que puedes proceder a guardarlo.
                ClientEntity clientNew = clientService.save(clientEntity);
                return ResponseEntity.status(HttpStatus.CREATED).body(clientNew);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
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

        clientEntity.setId(client.getId());
        ClientEntity clientUpdated = clientService.update(clientEntity);
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
