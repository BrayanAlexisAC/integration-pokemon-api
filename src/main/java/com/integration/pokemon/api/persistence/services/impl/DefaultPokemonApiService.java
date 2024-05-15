package com.integration.pokemon.api.persistence.services.impl;

import com.integration.pokemon.api.Constants;
import com.integration.pokemon.api.Constants.PokemonAPI;
import com.integration.pokemon.api.domain.dtos.ability.AbilityDTO;
import com.integration.pokemon.api.domain.dtos.heldItem.HeldItemDTO;
import com.integration.pokemon.api.domain.dtos.locationAreasEncounter.LocationAreaEncounterDTO;
import com.integration.pokemon.api.domain.dtos.pokemon.PokemonDTO;
import com.integration.pokemon.api.persistence.services.PokemonApiService;
import io.micrometer.common.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class DefaultPokemonApiService implements PokemonApiService {

    private final Logger LOG = LoggerFactory.getLogger(DefaultPokemonApiService.class);

    @Autowired
    private Environment environment;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public PokemonDTO getPokemon(String name) {
        if (StringUtils.isNotBlank(name)){
            try{
                return restTemplate.getForObject(environment.getProperty(PokemonAPI.URL, Constants.EMPTY_URL) + name.toLowerCase(), PokemonDTO.class);
            } catch (RestClientException re){
                LOG.error("Error with Pokemon API message:{}, cause:{}, stacktrace:{}",
                        re.getMessage(), re.getCause(), Arrays.toString(re.getStackTrace()));
                return null;
            }
        } else {
            LOG.warn("Pokemon name cannot be null or empty");
            return null;
        }
    }

    @Override
    public AbilityDTO getAbility(String url) {
        if (StringUtils.isNotBlank(url)){
            try{
                return restTemplate.getForObject(url, AbilityDTO.class);
            } catch (RestClientException re){
                LOG.error("Error with Pokemon API ability message:{}, cause:{}, stacktrace:{}",
                        re.getMessage(), re.getCause(), Arrays.toString(re.getStackTrace()));
                return null;
            }
        } else {
            return null;
        }
    }

    @Override
    public HeldItemDTO getHeldItem(String url) {
        if (StringUtils.isNotBlank(url)){
            try{
                return restTemplate.getForObject(url, HeldItemDTO.class);
            } catch (RestClientException re){
                LOG.error("Error with Pokemon API held item message:{}, cause:{}, stacktrace:{}",
                        re.getMessage(), re.getCause(), Arrays.toString(re.getStackTrace()));
                return null;
            }
        } else {
            return null;
        }
    }

    @Override
    public List<LocationAreaEncounterDTO> getLocationAreas(String url) {
        if (StringUtils.isNotBlank(url)){
            try{
                return Arrays.asList(
                        Objects.requireNonNull(restTemplate.getForObject(url, LocationAreaEncounterDTO[].class))
                );
            } catch (RestClientException re){
                LOG.error("Error with Pokemon API Location Areas Encounter message:{}, cause:{}, stacktrace:{}",
                        re.getMessage(), re.getCause(), Arrays.toString(re.getStackTrace()));
                return null;
            }
        } else {
            return null;
        }
    }
}
