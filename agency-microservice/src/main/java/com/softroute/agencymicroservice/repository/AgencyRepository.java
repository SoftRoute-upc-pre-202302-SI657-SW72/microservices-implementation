package com.softroute.agencymicroservice.repository;

import com.softroute.agencymicroservice.entity.AgencyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgencyRepository extends JpaRepository<AgencyEntity, Long>{
    AgencyEntity findAgencyById(Long idAgency);
    AgencyEntity findByAgencyName (String agencyName);
    AgencyEntity findByAgencyAddress (String agencyAddress);

    List<AgencyEntity> findByEnterpriseId(Long enterpriseId);
    List<AgencyEntity> findAllByAgencyCity(String agencyCity);
}
