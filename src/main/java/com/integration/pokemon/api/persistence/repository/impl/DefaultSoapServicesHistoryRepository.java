package com.integration.pokemon.api.persistence.repository.impl;

import com.integration.pokemon.api.persistence.crud.SoapServicesHistoryCrudRepository;
import com.integration.pokemon.api.persistence.entity.SoapServicesHistoryEntity;
import com.integration.pokemon.api.persistence.repository.SoapServicesHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class DefaultSoapServicesHistoryRepository implements SoapServicesHistoryRepository {

    @Autowired
    private SoapServicesHistoryCrudRepository crudRepository;

    @Override
    public Optional<SoapServicesHistoryEntity> save(SoapServicesHistoryEntity soapServicesHistory) {
        return Optional.of(crudRepository.save(soapServicesHistory));
    }
}
