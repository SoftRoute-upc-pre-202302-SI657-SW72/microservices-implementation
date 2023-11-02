package com.softroute.enterprisemicroservice.service;

import com.softroute.enterprisemicroservice.entity.EnterpriseEntity;
import com.softroute.enterprisemicroservice.feignclients.AgencyFeignClient;
import com.softroute.enterprisemicroservice.model.AgencyEntity;
import com.softroute.enterprisemicroservice.repository.EnterpriseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnterpriseService {

    @Autowired
    EnterpriseRepository enterpriseRepository;

    @Autowired
    AgencyFeignClient agencyFeignClient;

    public EnterpriseEntity getEnterpriseById(Long idEnterprise) {
        return enterpriseRepository.findEnterpriseById(idEnterprise);
    }

    public EnterpriseEntity getByNameEnterprise(String name) {
        return enterpriseRepository.findByName(name);
    }

    public EnterpriseEntity getEnterpriseByEmail(String email) {
        return enterpriseRepository.findEnterpriseByEmail(email);
    }

    public EnterpriseEntity getEnterpriseByRuc(String ruc) {
        return enterpriseRepository.findByRuc(ruc);
    }

    public List<EnterpriseEntity> getAll() {
        return enterpriseRepository.findAll();
    }

    public EnterpriseEntity save(EnterpriseEntity enterprise) {
        return enterpriseRepository.save(enterprise);
    }

    public void delete(Long id) {
        enterpriseRepository.deleteById(id);
    }

    public AgencyEntity saveAgency(AgencyEntity agency) {
        return agencyFeignClient.save(agency);
    }

    public AgencyEntity getAgencyById(Long agencyId) {
        return agencyFeignClient.getById(agencyId);
    }

    public AgencyEntity getAgencyByName(String agency_name) {
        return agencyFeignClient.getAgencyByName(agency_name);
    }

    public AgencyEntity getAgencyByAddress(String agency_address) {
        return agencyFeignClient.getAgencyByAddress(agency_address);
    }

    public List<AgencyEntity> getCityAgencies(String agency_city) {
        return agencyFeignClient.findAllByAgencyCity(agency_city);
    }

    public List<AgencyEntity> getAgenciesByEnterpriseId(Long enterprise_id) {
        return agencyFeignClient.findByEnterpriseId(enterprise_id);
    }
}
