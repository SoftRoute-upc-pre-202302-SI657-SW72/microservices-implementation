package com.softroute.enterprisemicroservice.service;

import com.softroute.enterprisemicroservice.entity.EnterpriseEntity;
import com.softroute.enterprisemicroservice.repository.EnterpriseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnterpriseService {

    @Autowired
    EnterpriseRepository enterpriseRepository;

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
}
