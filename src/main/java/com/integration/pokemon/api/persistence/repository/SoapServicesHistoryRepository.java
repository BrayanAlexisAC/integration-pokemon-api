package com.integration.pokemon.api.persistence.repository;

import com.integration.pokemon.api.persistence.entity.SoapServicesHistoryEntity;

import java.util.Optional;

public interface SoapServicesHistoryRepository {

    Optional<SoapServicesHistoryEntity> save(SoapServicesHistoryEntity soapServicesHistory);

}
