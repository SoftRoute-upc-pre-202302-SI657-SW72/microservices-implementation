package com.softroute.enterprisemicroservice.repository;

import com.softroute.enterprisemicroservice.entity.EnterpriseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnterpriseRepository extends JpaRepository<EnterpriseEntity, Long> {
    EnterpriseEntity findEnterpriseById(Long idEnterprise);
    EnterpriseEntity findByName(String name);
    EnterpriseEntity findEnterpriseByEmail(String email);
    EnterpriseEntity findByRuc(String ruc);

}
