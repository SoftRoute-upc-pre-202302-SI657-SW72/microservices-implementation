package com.softroute.agencymicroservice.service;

import com.softroute.agencymicroservice.entity.AgencyEntity;
import com.softroute.agencymicroservice.repository.AgencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgencyService {

    @Autowired
    AgencyRepository agencyRepository;

    public AgencyEntity getAgencyById(Long idAgency) {
        return agencyRepository.findAgencyById(idAgency);
    }

    public AgencyEntity getAgencyByName(String agencyName) {
        return agencyRepository.findByAgencyName(agencyName);
    }

    public AgencyEntity getAgencyByAddress(String agencyAddress) {
        return agencyRepository.findByAgencyAddress(agencyAddress);
    }

    public List<AgencyEntity> getCityAgencies(String agencyCity) {
        return agencyRepository.findAllByAgencyCity(agencyCity);
    }

    public List<AgencyEntity> getAgenciesByEnterpriseId(Long enterpriseId) {
        return agencyRepository.findByEnterpriseId(enterpriseId);
    }
    public List<AgencyEntity> getAll() {
        return agencyRepository.findAll();
    }

    public AgencyEntity save(AgencyEntity agency) {
        return agencyRepository.save(agency);
    }

    public void delete(Long id) {
        agencyRepository.deleteById(id);
    }




}
