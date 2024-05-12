package com.integration.pokemon.api.persistence.services.impl;

import com.integration.pokemon.api.persistence.entity.SoapServicesHistoryEntity;
import com.integration.pokemon.api.persistence.repository.SoapServicesHistoryRepository;
import com.integration.pokemon.api.persistence.services.SoapServicesHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultSoapServicesHistoryService implements SoapServicesHistoryService {

    @Autowired
    private SoapServicesHistoryRepository repository;

    @Override
    public Boolean save(SoapServicesHistoryEntity soapServicesHistory) {
        var soapHistory = repository.save(soapServicesHistory).orElseThrow(() ->
                new RuntimeException("Error to create Soap History"));

        return soapHistory.getId() > 0;
    }
}
