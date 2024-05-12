package com.integration.pokemon.api.persistence.services;

import com.integration.pokemon.api.domain.dtos.ability.AbilityDTO;
import com.integration.pokemon.api.domain.dtos.heldItem.HeldItemDTO;
import com.integration.pokemon.api.domain.dtos.locationAreasEncounter.LocationAreaEncounterDTO;
import com.integration.pokemon.api.domain.dtos.pokemon.PokemonDTO;

import java.util.List;

public interface PokemonApiService {
    /**
     * Get information about a Pokemon by name
     * @param name String, Pokemon name
     * @return PokemonDTO, attributes about pokemon
     */
    PokemonDTO getPokemon(String name);

    /**
     * Get information about a Pokemon ability
     * @param url String, URL to get information about a Pokemon ability (URL inside Pokemon abilities)
     * @return AbilityDTO, attributes about a Pokemon ability
     */
    AbilityDTO getAbility(String url);

    /**
     * Get information about a Held item
     * @param url String, URL to get information about Held item (URL inside Pokemon held items)
     * @return HeldItemDTO, attributes about a Held item
     */
    HeldItemDTO getHeldItem(String url);

    /**
     * Get information about a Pokemon location areas encounter
     * @param url String, URL to get information (URL inside Pokemon information)
     * @return LocationAreasEncounterDTO, attributes about location areas encounter
     */
    List<LocationAreaEncounterDTO> getLocationAreas(String url);

}
