package com.integration.pokemon.api.persistence.crud;

import com.integration.pokemon.api.persistence.entity.SoapServicesHistoryEntity;
import org.springframework.data.repository.ListCrudRepository;

public interface SoapServicesHistoryCrudRepository extends ListCrudRepository<SoapServicesHistoryEntity, Integer> {
    // Query Methods
}
