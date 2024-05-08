package com.integration.pokemon.api.domain.dtos.impl;

import com.integration.pokemon.api.domain.dtos.PokemonDTO;
import com.integration.pokemon.api.domain.services.PokemonApiService;
import com.integration.pokemon.api.mappers.PokemonMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;

@Service
public class DefaultPokemonApiService implements PokemonApiService {

    private final Logger LOG = LoggerFactory.getLogger(DefaultPokemonApiService.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private PokemonMapper mapper;

    @Override
    public PokemonDTO getPokemon(String name) {
        try{
            var response = restTemplate.getForObject("https://pokeapi.co/api/v2/pokemon/" + name, HashMap.class);
            return mapper.toPokemonDTO(response);
        } catch (RestClientException re){
            LOG.error("Error with Pokemon API message:{}, cause:{}, stacktrace:{}",
                    re.getMessage(), re.getCause(), Arrays.toString(re.getStackTrace()));
            return null;
        }
    }
}
