package com.softroute.clientmicroservice.repository;

import com.softroute.clientmicroservice.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IClientRepository extends JpaRepository<ClientEntity, Long> {
    ClientEntity getByDni(String dni);
    ClientEntity getByEmail(String email);
}
