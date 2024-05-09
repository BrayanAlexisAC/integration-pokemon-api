package com.integration.pokemon.api.domain.services;

import com.integration.pokemon.api.domain.dtos.AbilityDTO;
import com.integration.pokemon.api.domain.dtos.PokemonDTO;

public interface PokemonApiService {

    PokemonDTO getPokemon(String name);

    AbilityDTO getAbility(String url);

}
