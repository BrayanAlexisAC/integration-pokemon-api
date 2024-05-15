package com.integration.pokemon.api.persistence.services.impl;

import com.integration.pokemon.api.Constants;
import com.integration.pokemon.api.Constants.PokemonAPI;
import com.integration.pokemon.api.domain.dtos.ability.AbilityDTO;
import com.integration.pokemon.api.domain.dtos.heldItem.HeldItemDTO;
import com.integration.pokemon.api.domain.dtos.locationAreasEncounter.LocationAreaEncounterDTO;
import com.integration.pokemon.api.domain.dtos.pokemon.PokemonDTO;
import com.integration.pokemon.api.persistence.services.PokemonApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class DefaultPokemonApiService implements PokemonApiService {

    @Autowired
    private Environment environment;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public PokemonDTO getPokemon(String name) {
        var pokemon = pokemonApiConnection(restTemplate, environment.getProperty(PokemonAPI.URL, Constants.EMPTY_URL) + name.toLowerCase(), PokemonDTO.class);
        Objects.requireNonNull(pokemon, "Error with API, pokemon is null");
        return pokemon;
    }

    @Override
    public AbilityDTO getAbility(String url) {
        var ability = pokemonApiConnection(restTemplate, url, AbilityDTO.class);
        Objects.requireNonNull(ability, "Error with API, ability is null");
        return ability;
    }

    @Override
    public HeldItemDTO getHeldItem(String url) {
        var heldItem = pokemonApiConnection(restTemplate, url, HeldItemDTO.class);
        Objects.requireNonNull(heldItem, "Error with API, ability is null");
        return heldItem;
    }

    @Override
    public List<LocationAreaEncounterDTO> getLocationAreas(String url) {
        return Arrays.asList(
                Objects.requireNonNull(
                        pokemonApiConnection(restTemplate, url, LocationAreaEncounterDTO[].class),
                        "Error with API, locationAreas is null")
        );
    }
}
